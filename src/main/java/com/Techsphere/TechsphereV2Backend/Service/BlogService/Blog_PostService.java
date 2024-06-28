package com.Techsphere.TechsphereV2Backend.Service.BlogService;

import com.Techsphere.TechsphereV2Backend.entity.Blog_Post;

import java.util.Optional;

public interface Blog_PostService {
    Blog_Post createPost(Blog_Post post);
    Optional<Blog_Post> getPostById(Long postId);
    Blog_Post updatePost(Long postId, Blog_Post postDetails);
    Blog_Post deactivatePost(Long postId);
}
