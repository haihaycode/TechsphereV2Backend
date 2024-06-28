package com.Techsphere.TechsphereV2Backend.Repository;

import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface Blog_CategoryRepository extends JpaRepository<Blog_Category, Long> {
    //    SELECT * FROM blog_category
//        ORDER BY isActive DESC, categoryName ASC;
    @Query("SELECT c FROM Blog_Category c ORDER BY c.isActive DESC, c.categoryName ASC")
    List<Blog_Category> findAllCategoriesOrderedByActiveAndName();

    boolean existsByCategoryName(String category_name);
}

