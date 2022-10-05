package com.nourish1709.largestmarspicture.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("mars.api")
@Setter
@Getter
public class MarsApiParams {

    private String url;
    private String solKey;
    private String apiKey;
    private String apiValue;
    private String cameraKey;
    private String cameraDefaultValue;
    private String imgKey;
}
