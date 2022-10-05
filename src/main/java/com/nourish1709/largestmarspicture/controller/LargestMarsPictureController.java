package com.nourish1709.largestmarspicture.controller;

import com.nourish1709.largestmarspicture.service.LargestMarsPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/mars/pictures")
@RequiredArgsConstructor
public class LargestMarsPictureController {

    private final LargestMarsPictureService largestMarsPictureService;

    @GetMapping("/largest")
    public ResponseEntity<Void> getLargestPicture(@RequestParam int sol,
                                                  @RequestParam(required = false) String camera) {
        Optional<URI> largestPictureURL = largestMarsPictureService.getLargestPicture(sol, camera);
        if (largestPictureURL.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "No pictures found for parameters: sol = %d, camera = %s".formatted(sol, camera));
        }
        return ResponseEntity.status(HttpStatus.PERMANENT_REDIRECT)
                .location(largestPictureURL.get())
                .build();
    }
}
