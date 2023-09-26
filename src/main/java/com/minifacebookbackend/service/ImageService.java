package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.model.Image;

public interface ImageService {
    Image saveImage(Image image);
    Image updateImage(String imageId, Image image);
    void deleteImage(String imageId);
}
