package com.Techsphere.TechsphereV2Backend.Service.Blog;

import com.Techsphere.TechsphereV2Backend.dto.auth.Blog.Blog_PostDTO;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Post;

import java.util.List;
import java.util.Optional;

public interface Blog_PostService {
    Blog_Post createPost(Blog_PostDTO post);
    Optional<Blog_Post> getPostById(Blog_PostDTO postDTO);
    Blog_Post updatePost(Blog_PostDTO postDTO);
    Blog_Post deactivatePost(Long postId);
    List<Blog_Post> getAllPosts();

}
