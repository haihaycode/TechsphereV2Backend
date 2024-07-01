package com.Techsphere.TechsphereV2Backend.implementation.Blog;

import com.Techsphere.TechsphereV2Backend.Repository.BlogRepository.BlogPostRepository;
import com.Techsphere.TechsphereV2Backend.Repository.Blog_CategoryRepository;
import com.Techsphere.TechsphereV2Backend.Repository.UserRepository;
import com.Techsphere.TechsphereV2Backend.Service.Blog.Blog_PostService;
import com.Techsphere.TechsphereV2Backend.dto.auth.Blog.Blog_PostDTO;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Post;
import com.Techsphere.TechsphereV2Backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BlogPostServiceImpl implements Blog_PostService {
    @Autowired
    BlogPostRepository blogPostRepository;
    @Autowired
    Blog_CategoryRepository blogCategoryRepository;
    @Autowired
    UserRepository userRepository;
    @Override
    public Blog_Post createPost(Blog_PostDTO postDTO) {
        Blog_Post post = new Blog_Post();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setImage(postDTO.getImage());
        post.setActive(postDTO.isActive());
        post.setCreatedAt(LocalDateTime.now());
        post.setTags(postDTO.getTags());

        Blog_Category category = blogCategoryRepository.findById(postDTO.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        post.setCategory(category);

        User author = userRepository.findById(postDTO.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        post.setAuthor(author);
        return blogPostRepository.save(post);
    }

    @Override
    public Optional<Blog_Post> getPostById(Blog_PostDTO postDTO) {
        return blogPostRepository.findById(postDTO.getPostId());

    }




    @Override
        public Blog_Post updatePost( Blog_PostDTO postDTO) {
            Optional<Blog_Post> optionalPost = blogPostRepository.findById(postDTO.getPostId());
            if (optionalPost.isPresent()) {
                Blog_Post post = optionalPost.get();
                post.setPostId(postDTO.getPostId());
                post.setTitle(postDTO.getTitle());
                post.setContent(postDTO.getContent());
                post.setImage(postDTO.getImage()); // Update image field
                post.setUpdatedAt(LocalDateTime.now());
                post.setTags(postDTO.getTags());
//                Blog_Category category = blogCategoryRepository.findById(postDTO.getCategoryId())
//                        .orElseThrow(() -> new RuntimeException("Category not found"));
//                post.setCategory(category);
//
//                User author = userRepository.findById(postDTO.getAuthorId())
//                        .orElseThrow(() -> new RuntimeException("Author not found"));
//                post.setAuthor(author);

                return blogPostRepository.save(post);
            } else {
                throw new RuntimeException("Post not found with id " + postDTO.getPostId());
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

    @Override
    public List<Blog_Post> getAllPosts() {
        return blogPostRepository.findAllBy();
    }
}
