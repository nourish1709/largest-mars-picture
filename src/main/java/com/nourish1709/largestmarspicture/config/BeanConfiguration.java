package com.nourish1709.largestmarspicture.config;

import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfiguration {

    @Bean
    public RestTemplate redirectingRestTemplate() {
        return new RestTemplate(new HttpComponentsClientHttpRequestFactory(
                HttpClientBuilder.create()
                        .setRedirectStrategy(new LaxRedirectStrategy())
                        .build()));
    }
}
