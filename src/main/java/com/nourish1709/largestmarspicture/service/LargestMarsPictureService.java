package com.nourish1709.largestmarspicture.service;

import org.springframework.lang.Nullable;

import java.net.URI;
import java.util.Optional;

public interface LargestMarsPictureService {

    Optional<URI> getLargestPicture(int sol, @Nullable String camera);
}
