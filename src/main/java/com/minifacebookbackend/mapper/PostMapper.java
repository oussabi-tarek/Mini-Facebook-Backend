package com.minifacebookbackend.mapper;

import com.minifacebookbackend.domain.model.Post;
import com.minifacebookbackend.domain.representation.PostRepresentation;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface PostMapper {
    PostRepresentation toPostRepresentation(Post post);
    List<PostRepresentation> toPostRepresentationList(List<Post> posts);
}
