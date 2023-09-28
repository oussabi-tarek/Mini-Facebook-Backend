package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.command.ImageCommand;
import com.minifacebookbackend.domain.model.Image;
import java.util.List;

public interface ImageService {
    Image saveImage(ImageCommand imageCommand);
    Image updateImage(ImageCommand imageCommand);
    void deleteImage(String imageId);
    List<Image> saveImages(List<ImageCommand> imageCommands);
    void deleteImages(List<Image> images);
    void updateImages(List<ImageCommand> imageCommands);
}
