package com.Techsphere.TechsphereV2Backend.implementation.Blog;

import com.Techsphere.TechsphereV2Backend.Repository.PostRepository;
import com.Techsphere.TechsphereV2Backend.Service.Blog.PostService;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Post;
import jakarta.persistence.criteria.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public Page<Blog_Post> getPosts(String title, String category, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        Specification<Blog_Post> spec = Specification.where((root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("postId")));
            return null; // This null is necessary to satisfy the Specification interface
        });

        if (title != null && !title.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.like(root.get("title"), "%" + title + "%")
            );
        }

        if (category != null && !category.isEmpty()) {
            spec = spec.and((root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.join("category", JoinType.LEFT).get("categoryName"), category)
            );
        }

        spec = spec.and((root, query, criteriaBuilder) ->
                criteriaBuilder.isTrue(root.get("isActive"))
        );

        return postRepository.findAll(spec, pageable);
    }

    @Override
    public Blog_Post getPostById(Long id) {
        return null;
    }

}
