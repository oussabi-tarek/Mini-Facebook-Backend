package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.model.Image;
import com.minifacebookbackend.repository.ImageRepository;
import com.minifacebookbackend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Image saveImage(Image image) {
        if(image == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "image to save is null");
        }
        return imageRepository.save(image);
    }

    @Override
    public Image updateImage(String imageId, Image image) {
        Image imageToUpdate = imageRepository.findById(imageId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "image id is not valid"));
        imageToUpdate.setUrl(image.getUrl());
        return imageRepository.save(imageToUpdate);
    }

    @Override
    public void deleteImage(String imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "image id is not valid"));
        imageRepository.delete(image);
    }
}
