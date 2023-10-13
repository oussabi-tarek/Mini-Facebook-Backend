package com.minifacebookbackend.service.impl;

import com.minifacebookbackend.domain.command.PostCommand;
import com.minifacebookbackend.domain.command.TagCommand;
import com.minifacebookbackend.domain.criterias.PostCriteria;
import com.minifacebookbackend.domain.model.Post;
import com.minifacebookbackend.domain.representation.LikeRepresentation;
import com.minifacebookbackend.domain.representation.PostRepresentation;
import com.minifacebookbackend.domain.representation.UserRepresentation;
import com.minifacebookbackend.mapper.PostMapper;
import com.minifacebookbackend.repository.PostRepository;
import com.minifacebookbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final ImageServiceImpl imageService;
    private final TagServiceImpl tagService;
    private final CommentServiceImpl commentService;
    private final LikeServiceImpl likeService;
    private final UnLikeServiceImpl unLikeService;
    private final UserServiceImpl userService;

    @Override
    public PostRepresentation getPostById(String postId) {
        Post post= postRepository.findById(postId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "post id is not valid"));
        PostRepresentation postRepresentation = postMapper.toPostRepresentation(post);
        return getInfos(postRepresentation,post);
    }
    public PostRepresentation getInfos(PostRepresentation postRepresentation,Post post){
        System.out.println("1");
        postRepresentation.setUser(userService.getById(post.getUserId()));
        System.out.println("2");
        postRepresentation.setLikes(likeService.getLikesByPostId(post.getId()));
        System.out.println("3");
        postRepresentation.setTags(tagService.getTagsByPostId(post.getId()));
        System.out.println("4");
        postRepresentation.setImages(imageService.getImagesByPostId(post.getId()));
        System.out.println("5");
        postRepresentation.setComments(commentService.getCommentsByPostId(post.getId()));
        System.out.println("6");
        postRepresentation.setUnLikes(unLikeService.getUnLikesByPostId(post.getId()));
        System.out.println("7");
        return postRepresentation;
    }

    @Override
    public Post savePost(String userId, String content, MultipartFile file) throws IOException {
        Post post = new Post();
        post.setContent(content);
        post.setCreatedDate(LocalDateTime.now().toString());
        post.setUserId(userId);
        postRepository.save(post);
        post.setImages(imageService.saveImages(file,post.getId()));
        post.setTags(tagService.saveTags(getTagsFromContent(content,post.getId())));
        return postRepository.save(post);
    }
    public List<TagCommand> getTagsFromContent(String content,String postId){
        List<TagCommand> tags = new ArrayList<>();
        String[] words = content.split(" ");
        for(String word:words){
            if(word.startsWith("#")){
                TagCommand tag = new TagCommand();
                tag.setContent(word);
                tag.setPostId(postId);
                tags.add(tag);
            }
        }
        return tags;
    }

    @Override
    public PostRepresentation updatePost(PostCommand postCommand, String postId) {
        Post postToUpdate = postRepository.findById(postId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "post id is not valid"));
        postToUpdate.setContent(postCommand.getContent());
        postToUpdate.setUpdatedDate(LocalDateTime.now().toString());
        if(postCommand.getImages() != null &&  !postCommand.getImages().isEmpty()){
            imageService.updateImages(postCommand.getImages());
        }
        tagService.updateTags(postCommand.getTags());
        return postMapper.toPostRepresentation(postRepository.save(postToUpdate));
    }

    @Override
    public void deletePost(String postId) {
        Post postToDelete = postRepository.findById(postId).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "post id is not valid"));
        imageService.deleteImages(postToDelete.getImages());
        tagService.deleteTags(postToDelete.getTags());
        commentService.deleteComments(postToDelete.getComments());
        likeService.deleteLikes(postToDelete.getLikes());
        unLikeService.deleteUnLikes(postToDelete.getUnLikes());
        postToDelete.setComments(null);
        postToDelete.setImages(null);
        postToDelete.setTags(null);
        postToDelete.setUnLikes(null);
        postToDelete.setUserId(null);
        postRepository.save(postToDelete);
        postRepository.delete(postToDelete);
    }
    @Override
    public List<PostRepresentation> getAll(PostCriteria postCriteria) {
        System.out.println("get all posts");
        List<PostRepresentation> postRepresentationList= new ArrayList<>();
        if(postCriteria.getContent()!=null && !postCriteria.getContent().isEmpty()){
            System.out.println("get all posts by content"+postCriteria.getContent());
            postRepresentationList= postMapper.toPostRepresentationList(postRepository.findAllByContentIgnoreCase(postCriteria.getContent()));
        }
        else
         postRepresentationList= postMapper.toPostRepresentationList(postRepository.findAll().stream().toList());
        for(PostRepresentation postRepresentation:postRepresentationList){
             postRepresentation=getInfos(postRepresentation,postRepository.findById(postRepresentation.getId()).get());
        }
        return postRepresentationList;
    }

    @Override
    public List<PostRepresentation> findPostsByUserId(String userId) {
        List<PostRepresentation> postRepresentationList = new ArrayList<>();
        if(userId != null && !userId.isEmpty()){
            //List<Post> postList = postRepository.findPostsByUserId(userId);
            postRepresentationList= postMapper.toPostRepresentationList(postRepository.findPostsByUserId(userId).stream().toList());
            for(PostRepresentation postRepresentation:postRepresentationList){
                postRepresentation=getInfos(postRepresentation,postRepository.findById(postRepresentation.getId()).get());
            }
            System.out.println("postRepresentationList: "+postRepresentationList);
            return postRepresentationList;
        }
        return null;
    }
}
