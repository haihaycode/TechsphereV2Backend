package com.Techsphere.TechsphereV2Backend.implementation.Blog;

import com.Techsphere.TechsphereV2Backend.Repository.BlogRepository.BlogPostRepository;
import com.Techsphere.TechsphereV2Backend.Service.BlogService.Blog_PostService;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Post;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogPostServiceImpl  implements Blog_PostService {
    @Autowired
    BlogPostRepository blogPostRepository;
    @Override
    public Blog_Post createPost(Blog_Post post) {
        post.setCreatedAt(LocalDateTime.now());
        post.setActive(true);
        return blogPostRepository.save(post);
    }

    @Override
    public Optional<Blog_Post> getPostById(Long postId) {
        return blogPostRepository.findById(postId);

    }

    @Override
    public Blog_Post updatePost(Long postId, Blog_Post postDetails) {
        Optional<Blog_Post> optionalPost = blogPostRepository.findById(postId);

        if (optionalPost.isPresent()) {
            Blog_Post post = optionalPost.get();
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            post.setImage(postDetails.getImage()); // Update image field
            post.setUpdatedAt(LocalDateTime.now());
            return blogPostRepository.save(post);
        } else {
            throw new RuntimeException("Post not found with id " + postId);
        }
    }

    @Override
    public Blog_Post deactivatePost(Long postId) {
        Optional<Blog_Post> optionalPost = blogPostRepository.findById(postId);

        if (optionalPost.isPresent()) {
            Blog_Post post = optionalPost.get();
            post.setActive(false);
            return blogPostRepository.save(post);
        } else {
            throw new RuntimeException("Post not found with id " + postId);
        }
    }
}
