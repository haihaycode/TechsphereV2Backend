package com.Techsphere.TechsphereV2Backend.Controller.Admin.Blog;

import com.Techsphere.TechsphereV2Backend.Service.Blog.PostService;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    private PostService postService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/posts")
    public Page<Blog_Post> getPosts(
            @RequestParam(defaultValue = "") String title,
            @RequestParam(defaultValue = "") String category,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "8") int size) {
        return postService.getPosts(title, category, page, size);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/posts/{id}")
    public Blog_Post getPostById(@PathVariable Long id) {
        return postService.getPostById(id);
    }
}
