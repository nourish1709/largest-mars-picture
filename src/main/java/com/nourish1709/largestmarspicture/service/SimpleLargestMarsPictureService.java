package com.nourish1709.largestmarspicture.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.nourish1709.largestmarspicture.config.MarsApiParams;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Comparator;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
@RequiredArgsConstructor
public class SimpleLargestMarsPictureService implements LargestMarsPictureService {

    private final RestTemplate restTemplate;
    private final MarsApiParams marsApiParams;

    @Override
    @Cacheable("largestPicture")
    public Optional<byte[]> getLargestPicture(int sol, String camera) {
        if (camera == null || camera.isBlank())
            camera = marsApiParams.getCameraDefaultValue();

        return requireNonNull(getAllPhotos(sol, camera))
                .findValues(marsApiParams.getImgKey()).parallelStream()
                .max(getContentLengthComparator())
                .map(this::toUri)
                .map(imgSrcURI -> restTemplate.getForObject(imgSrcURI, byte[].class));
    }

    private JsonNode getAllPhotos(int sol, String camera) {
        return restTemplate.getForObject(UriComponentsBuilder.fromHttpUrl(marsApiParams.getUrl())
                .queryParam(marsApiParams.getSolKey(), sol)
                .queryParam(marsApiParams.getApiKey(), marsApiParams.getApiValue())
                .queryParam(marsApiParams.getCameraKey(), camera)
                .build().toUri(), JsonNode.class);
    }

    private URI toUri(JsonNode jsonNode) {
        return UriComponentsBuilder.fromHttpUrl(jsonNode.asText()).build().toUri();
    }

    private Comparator<JsonNode> getContentLengthComparator() {
        return Comparator.comparingLong(node ->
                restTemplate.headForHeaders(toUri(node))
                        .getContentLength());
    }
}
