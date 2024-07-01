package com.Techsphere.TechsphereV2Backend.Repository.BlogRepository;

import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<Blog_Post, Long> {
    List<Blog_Post> findAllBy();
}
