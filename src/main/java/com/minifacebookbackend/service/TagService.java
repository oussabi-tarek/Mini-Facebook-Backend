package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.command.TagCommand;
import com.minifacebookbackend.domain.model.Tag;
import com.minifacebookbackend.domain.representation.TagRepresentation;

import java.util.List;

public interface TagService {
    Tag saveTag(TagCommand tag);
    Tag updateTag(TagCommand tagCommand);
    void delete(String tagId);
    List<Tag> saveTags(List<TagCommand> tags);
    void deleteTags(List<Tag> tags);
    void updateTags(List<TagCommand> tags);
    List<TagRepresentation> getTagsByPostId(String postId);
}
