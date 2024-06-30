package com.Techsphere.TechsphereV2Backend.implementation.Blog;

import com.Techsphere.TechsphereV2Backend.Repository.Blog_CategoryRepository;
import com.Techsphere.TechsphereV2Backend.Service.Blog.Blog_CategoryService;
import com.Techsphere.TechsphereV2Backend.Service.Image.ImageStorageService;
import com.Techsphere.TechsphereV2Backend.Utils.OrderUtils;
import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateCategoryDTO;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    public Blog_Category createCategory(UpdateCategoryDTO categoryDTO, MultipartFile categoryImage) {
        Blog_Category category = new Blog_Category();
        System.out.println(categoryDTO.toString());
        if (blogCategoryRepository.existsByCategoryName(categoryDTO.getCategoryName())) {
            throw new RuntimeException("Category already exists");
        }

        String imagePath = null;
        if (categoryImage != null && !categoryImage.isEmpty()) {
            try {
                String imageName = OrderUtils.generateImage();
                imageStorageService.storeImageProfile(categoryImage, imageName);
                imagePath = imageName + ".png";
            } catch (IOException e) {
                throw new RuntimeException("Error saving image", e);
            }
        }

        category.setCategoryName(categoryDTO.getCategoryName());
        category.setCategoryImage(imagePath);
        category.setActive(categoryDTO.isActive());
        category.setCreatedAt(LocalDateTime.now());
        category.setUpdatedAt(LocalDateTime.now());

        return blogCategoryRepository.save(category);
    }

    @Override
    public List<Blog_Category> getAllCategoriesOrderedByActiveAndName() {
        return blogCategoryRepository.findAllCategoriesOrderedByActiveAndName();
    }


    @Override
    public List<Blog_Category> getAll() {
        return List.of();
    }

    @Override
    public boolean disableCategory(Long categoryId, boolean enabled) {
        try {
            blogCategoryRepository.disableCategory(categoryId, !enabled); // Invert the value of enabled for isActive field
            return true;
        } catch (Exception e) {
            // Handle any exceptions or return false as needed
            return false;
        }
    }

    @Override
    @Transactional
    public Blog_Category updateCategory(Long categoryId, UpdateCategoryDTO categoryDTO, MultipartFile categoryImage) {
        Blog_Category category = blogCategoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        category.setCategoryName(categoryDTO.getCategoryName());
        category.setActive(categoryDTO.isActive());
        category.setUpdatedAt(LocalDateTime.now());

        if (categoryImage != null && !categoryImage.isEmpty()) {
            try {
                String imageName = OrderUtils.generateImage();
                imageStorageService.storeImageProfile(categoryImage, imageName);
                String imagePath = imageName + ".png";
                category.setCategoryImage(imagePath);
            } catch (IOException e) {
                throw new RuntimeException("Error saving image", e);
            }
        }

        return blogCategoryRepository.save(category);
    }

}





