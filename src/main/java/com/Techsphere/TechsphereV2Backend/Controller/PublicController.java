package com.Techsphere.TechsphereV2Backend.Controller;

import com.Techsphere.TechsphereV2Backend.Service.AuthService;
import com.Techsphere.TechsphereV2Backend.Service.Blog.Blog_CategoryService;
import com.Techsphere.TechsphereV2Backend.Service.Image.ImageStorageService;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import com.Techsphere.TechsphereV2Backend.entity.User;
import com.Techsphere.TechsphereV2Backend.model.Response;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/")
public class PublicController {
    @Autowired
    private AuthService authService;
    @Autowired
    ImageStorageService imageStorageService;

    @Autowired
    Blog_CategoryService blogCategoryService;

    private static final String UPLOAD_DIR = "uploads/profile/";

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/public/client/image/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable String name) {
        try {
            byte[] imageData = imageStorageService.loadImage(name);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Hoặc MediaType.IMAGE_PNG tùy vào loại hình ảnh

            return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> helloAdmin(){
        return ResponseEntity.ok("Hello Admin");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> helloUser(){
        return ResponseEntity.ok("Hello User");
    }

    @PreAuthorize("hasRole('OTHER')")
    @GetMapping("/other")
    public ResponseEntity<String> helloOTHER(){
        return ResponseEntity.ok("Hello OTHER");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/getUsernameOrEmailActive")
    public ResponseEntity<Response<User>> getUsernameOrEmailActive(@RequestParam("usernameOrEmail") String usernameOrEmail) {
        try {
            User user = authService.getUsernameOrEmailActive(usernameOrEmail);
            Response<User> response = new Response<>(user, "Get user registered successfully", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Response<User> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/getUsernameOrEmail")
    public ResponseEntity<Response<User>> getUsernameOrEmail(@RequestParam("usernameOrEmail") String usernameOrEmail) {
        try {
            User user = authService.getUserByUsernameOrEmail(usernameOrEmail);
            Response<User> response = new Response<>(user, "Get user registered successfully", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Response<User> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/auth/user")
    public ResponseEntity<Response<List<User>>> listUser() {
        try {
            List<User> user = authService.getAll();
            Response<List<User>> response = new Response<>(user, "Đây là list user", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Response<List<User>> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("/public/client/categories")
    @ResponseBody
    public ResponseEntity<Response<List<Blog_Category>>> getAllCategoriesOrderedByActiveAndName() {
        try {
            System.out.println("1");
            List<Blog_Category> categories = blogCategoryService.getAllCategoriesOrderedByActiveAndName();
            Response<List<Blog_Category>> response = new Response<>(categories, "Categories retrieved successfully", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Response<List<Blog_Category>> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

}