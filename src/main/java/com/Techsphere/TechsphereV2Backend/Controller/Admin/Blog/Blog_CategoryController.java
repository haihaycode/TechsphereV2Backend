package com.Techsphere.TechsphereV2Backend.Controller.Admin.Blog;

import com.Techsphere.TechsphereV2Backend.Service.Blog.Blog_CategoryService;
import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateCategoryDTO;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import com.Techsphere.TechsphereV2Backend.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Controller
@CrossOrigin
@RequestMapping("/api/admin")
public class Blog_CategoryController {
    @Autowired
    private Blog_CategoryService blogCategoryService;

    @PostMapping("/upload")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Blog_Category>> uploadCategory(   @RequestParam("categoryName") String categoryName,
                                                                     @RequestParam("categoryImage") MultipartFile categoryImage,
                                                                     @RequestParam("isActive") boolean isActive) {
        UpdateCategoryDTO categoryDTO = new UpdateCategoryDTO();
        categoryDTO.setCategoryName(categoryName);
        categoryDTO.setCategoryImage(categoryImage);
        categoryDTO.setActive(isActive);
        categoryDTO.setCreatedAt(LocalDateTime.now());
        categoryDTO.setUpdatedAt(LocalDateTime.now());

        try {
            Blog_Category category = blogCategoryService.uploadCategory(categoryDTO, categoryImage);
            Response<Blog_Category> response = new Response<>(category, "Category uploaded successfully", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Response<Blog_Category> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
