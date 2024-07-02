package com.Techsphere.TechsphereV2Backend.Service.Blog_Category;

import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateCategoryDTO;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Blog_CategoryService {
    Blog_Category createCategory(UpdateCategoryDTO categoryDTO, MultipartFile file);

    List<Blog_Category> getAllCategoriesActive();

    List<Blog_Category> getAll();

    boolean disableCategory(Long categoryId, boolean enabled);
    Blog_Category updateCategory(Long categoryId, UpdateCategoryDTO categoryDTO, MultipartFile categoryImage);
    List<Blog_Category> findCategoryBySorting(String field);

}
