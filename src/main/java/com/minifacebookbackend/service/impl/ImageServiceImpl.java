package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.command.ImageCommand;
import com.minifacebookbackend.domain.model.Image;
import com.minifacebookbackend.repository.ImageRepository;
import com.minifacebookbackend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;
    @Override
    public Image saveImage(ImageCommand imageCommand) {
        if(imageCommand == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "image to save is null");
        }
        Image imageToSave = new Image();
        imageToSave.setUrl(imageCommand.getUrl());
        imageToSave.setPostId(imageCommand.getPostId());
        return imageRepository.save(imageToSave);
    }
    @Override
    public List<Image> saveImages(List<ImageCommand> imageCommands){
        List<Image> images=new ArrayList<>();
        for (ImageCommand imageCommand : imageCommands) {
            images.add(saveImage(imageCommand));
        }
        return images;
    }


    @Override
    public Image updateImage(ImageCommand imageCommand) {
        if(imageCommand.isNew()){
            return saveImage(imageCommand);
        }else {
           Image imageToUpdate = imageRepository.findById(imageCommand.getId()).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "image id is not valid"));
           imageToUpdate.setUrl(imageCommand.getUrl());
           imageToUpdate.setPostId(imageCommand.getPostId());
           return imageRepository.save(imageToUpdate);
        }

    }

    @Override
    public void deleteImage(String imageId) {
        Image image = imageRepository.findById(imageId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "image id is not valid"));
        imageRepository.delete(image);
    }
    @Override
    public void deleteImages(List<Image> images){
        imageRepository.deleteAll(images);
    }
    @Override
    public void updateImages(List<ImageCommand> imageCommands){
        for (ImageCommand imageCommand : imageCommands) {
            updateImage(imageCommand);
        }
    }
}
