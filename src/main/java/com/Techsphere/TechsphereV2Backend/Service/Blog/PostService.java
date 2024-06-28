package com.Techsphere.TechsphereV2Backend.Service.Blog;

import com.Techsphere.TechsphereV2Backend.entity.Blog_Post;
import org.springframework.data.domain.Page;

public interface PostService {
    Page<Blog_Post> getPosts(String title, String category, int page, int size);
    Blog_Post getPostById(Long id);
}
