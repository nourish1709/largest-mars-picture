package com.nourish1709.largestmarspicture.controller;

import com.nourish1709.largestmarspicture.service.LargestMarsPictureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/mars/pictures")
@RequiredArgsConstructor
public class LargestMarsPictureController {

    private final LargestMarsPictureService largestMarsPictureService;

    @GetMapping(value = "/largest", produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getLargestPicture(@RequestParam int sol,
                                                    @RequestParam(required = false) String camera) {
        byte[] largestPicture = largestMarsPictureService.getLargestPicture(sol, camera)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "No pictures found for parameters: sol = %d, camera = %s".formatted(sol, camera)));
        return ResponseEntity.ok(largestPicture);
    }
}
