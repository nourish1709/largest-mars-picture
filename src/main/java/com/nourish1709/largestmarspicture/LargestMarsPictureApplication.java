package com.nourish1709.largestmarspicture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class LargestMarsPictureApplication {

    public static void main(String[] args) {
        SpringApplication.run(LargestMarsPictureApplication.class, args);
    }

}
