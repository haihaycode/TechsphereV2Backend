package com.Techsphere.TechsphereV2Backend.Repository;

import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Blog_CategoryRepository extends JpaRepository<Blog_Category, Long> {
    //    SELECT * FROM blog_category
//        ORDER BY isActive DESC, categoryName ASC;
    //Hiển thị từ duới lên & 8 bài viết mới nhất lên đâu
    //Tìm hiểu sort không cần viết lại query nhiều lần
    @Query("SELECT c FROM Blog_Category c ORDER BY c.isActive DESC, c.categoryName ASC")
    List<Blog_Category> findAllCategoriesOrderedByActiveAndName();
    boolean existsByCategoryName(String category_name);

    @Modifying
    @Query("UPDATE Blog_Category c SET c.isActive = :disable WHERE c.categoryId = :categoryId")
    void disableCategory(@Param("categoryId") Long categoryId, @Param("disable") Boolean disable);
}