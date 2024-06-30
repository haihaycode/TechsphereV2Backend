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



@Controller
@CrossOrigin
@RequestMapping("/api/admin/blog")
public class Blog_CategoryController {
    @Autowired
    private Blog_CategoryService blogCategoryService;



    @PostMapping("/categories/create")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Blog_Category>> createCategory( @RequestPart("categoryDTO") UpdateCategoryDTO categoryDTO,
                                                                   @RequestPart("categoryImage") MultipartFile categoryImage)  {
        try {
            Blog_Category category = blogCategoryService.createCategory(categoryDTO, categoryImage);
            Response<Blog_Category> response = new Response<>(category, "Category uploaded successfully", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Response<Blog_Category> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/categories/update/{categoryId}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response<Blog_Category>> updateCategory(
            @PathVariable Long categoryId,
            @RequestPart("categoryDTO") UpdateCategoryDTO categoryDTO,
            @RequestPart("categoryImage") MultipartFile categoryImage) {
        try {
            System.out.println(categoryId);
            Blog_Category updatedCategory = blogCategoryService.updateCategory(categoryId, categoryDTO, categoryImage);
            Response<Blog_Category> response = new Response<>(updatedCategory, "Category updated successfully", HttpStatus.OK);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Response<Blog_Category> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return ResponseEntity.badRequest().body(response);
        }
    }

}
