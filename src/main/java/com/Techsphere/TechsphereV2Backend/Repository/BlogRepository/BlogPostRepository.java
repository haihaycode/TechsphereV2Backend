package com.Techsphere.TechsphereV2Backend.Repository.BlogRepository;

import com.Techsphere.TechsphereV2Backend.entity.Blog_Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<Blog_Post, Long> {
}
