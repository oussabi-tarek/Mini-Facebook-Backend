package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.command.TagCommand;
import com.minifacebookbackend.domain.model.Tag;

import java.util.List;

public interface TagService {
    Tag saveTag(TagCommand tag);
    Tag updateTag(TagCommand tagCommand);
    void delete(String tagId);
    List<Tag> saveTags(List<TagCommand> tags);
    void deleteTags(List<Tag> tags);
    void updateTags(List<TagCommand> tags);
}
