package com.minifacebookbackend.service;

import com.minifacebookbackend.domain.model.Tag;

public interface TagService {
    Tag saveTag(Tag tag);
    Tag updateTag(String tagId, Tag tag);
    void delete(String tagId);
}
