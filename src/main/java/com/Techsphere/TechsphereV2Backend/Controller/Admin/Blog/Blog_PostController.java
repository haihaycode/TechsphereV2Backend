package com.Techsphere.TechsphereV2Backend.Controller.Admin.Blog;


import com.Techsphere.TechsphereV2Backend.Service.Blog.Blog_PostService;
import com.Techsphere.TechsphereV2Backend.dto.auth.Blog.Blog_PostDTO;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin")

public class Blog_PostController {
    @Autowired
    Blog_PostService blog_PostService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/blog/create")
    public ResponseEntity<Blog_Post> createPost(@RequestBody Blog_PostDTO blogPostDTO) {
        try {
            Blog_Post createdPost = blog_PostService.createPost(blogPostDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // API cập nhật bài viết
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/blog/update/{postId}")
    public ResponseEntity<Blog_Post> updatePost( @RequestBody Blog_PostDTO postDTO) {
        try {
            Blog_Post updatedPost = blog_PostService.updatePost(postDTO);
            if (updatedPost != null) {
                return ResponseEntity.ok(updatedPost);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // API chuyển bài viết về trạng thái không hoạt động (xóa bài viết)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/blog/deactivate/{postId}")
    public ResponseEntity<Blog_Post> deactivatePost(@PathVariable Long postId) {
        try {
            Blog_Post updatedPost = blog_PostService.deactivatePost(postId);
            if (updatedPost != null) {
                return ResponseEntity.ok(updatedPost);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
