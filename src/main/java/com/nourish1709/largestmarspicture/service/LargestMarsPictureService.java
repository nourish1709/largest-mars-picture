package com.nourish1709.largestmarspicture.service;

import org.springframework.lang.Nullable;

import java.util.Optional;

public interface LargestMarsPictureService {

    Optional<byte[]> getLargestPicture(int sol, @Nullable String camera);
}
