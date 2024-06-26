package com.Techsphere.TechsphereV2Backend.Repository;

import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Blog_CategoryRepository extends JpaRepository<Blog_Category, Long> {

    boolean existsByCategoryName(String category_name);
}

