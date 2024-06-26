package com.Techsphere.TechsphereV2Backend.Service.Blog;

import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateCategoryDTO;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface Blog_CategoryService {
    Blog_Category uploadCategory(UpdateCategoryDTO categoryDTO, MultipartFile file);

    List<Blog_Category> getAll();
}
