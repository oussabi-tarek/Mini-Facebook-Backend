package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.model.Tag;
import com.minifacebookbackend.repository.TagRepository;
import com.minifacebookbackend.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;

    @Override
    public Tag saveTag(Tag tag) {
        if(tag == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Tag to save is null");
        }
        return tagRepository.save(tag);
    }

    @Override
    public Tag updateTag(String tagId, Tag tag) {
        Tag tagToUpdate = tagRepository.findById(tagId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag id is not valid"));
        tagToUpdate.setContent(tag.getContent());
        return tagRepository.save(tagToUpdate);
    }

    @Override
    public void delete(String tagId) {
        Tag tag = tagRepository.findById(tagId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag id is not valid"));
        tagRepository.delete(tag);
    }
}
