package com.Techsphere.TechsphereV2Backend.Repository;

import com.Techsphere.TechsphereV2Backend.entity.Blog_Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PostRepository extends JpaRepository<Blog_Post, Long>, JpaSpecificationExecutor<Blog_Post> {
}