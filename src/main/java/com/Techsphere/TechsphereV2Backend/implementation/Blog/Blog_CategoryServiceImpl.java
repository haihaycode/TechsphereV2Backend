package com.Techsphere.TechsphereV2Backend.implementation.Blog;

import com.Techsphere.TechsphereV2Backend.Repository.Blog_CategoryRepository;
import com.Techsphere.TechsphereV2Backend.Service.Blog.Blog_CategoryService;
import com.Techsphere.TechsphereV2Backend.Service.Image.ImageStorageService;
import com.Techsphere.TechsphereV2Backend.Utils.OrderUtils;
import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateCategoryDTO;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class Blog_CategoryServiceImpl implements Blog_CategoryService {

    private final ImageStorageService imageStorageService;
    private final Blog_CategoryRepository blogCategoryRepository; // Thêm injection

    @Autowired
    public Blog_CategoryServiceImpl(ImageStorageService imageStorageService, Blog_CategoryRepository blogCategoryRepository) {
        this.imageStorageService = imageStorageService;
        this.blogCategoryRepository = blogCategoryRepository;
    }

    @Override
    public Blog_Category uploadCategory(UpdateCategoryDTO categoryDTO, MultipartFile file){
        if (blogCategoryRepository.existsByCategoryName(categoryDTO.getCategoryName())) {
            throw new RuntimeException("Category already exists");
        }

        String imagePath = null;
        if (file != null && !file.isEmpty()) {
            try {
                String imageName = OrderUtils.generateImage();
                imageStorageService.storeImageProfile(file, imageName);
                imagePath = imageName + ".png";
            } catch (IOException e) {
                throw new RuntimeException("Error saving image", e);
            }
        }

        Blog_Category category = new Blog_Category();
        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryImage(imagePath);
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());
        category.setActive(categoryDTO.isActive());

        return blogCategoryRepository.save(category);
    }


    @Override
    public List<Blog_Category> getAll() {
        return List.of();
    }
}



