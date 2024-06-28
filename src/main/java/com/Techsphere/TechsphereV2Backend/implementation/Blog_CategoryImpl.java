package com.Techsphere.TechsphereV2Backend.implementation;

import com.Techsphere.TechsphereV2Backend.Repository.Blog_CategoryRepository;
import com.Techsphere.TechsphereV2Backend.Repository.UserRepository;
import com.Techsphere.TechsphereV2Backend.Service.Blog_CategoryService;
import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;
import com.Techsphere.TechsphereV2Backend.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

public class Blog_CategoryImpl implements Blog_CategoryService {

    private Blog_CategoryRepository repository;

    private UserRepository userRepository;

    @Override
    public List<Blog_Category> getAll() {
        return List.of();
    }

    @Override
    public Blog_Category getById(int id) {
        return null;
    }

    @Override
    public Blog_Category Upload(Blog_Category category) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsernameOrEmail(currentUsername, currentUsername);
        if (currentUser == null){
            throw new RuntimeException("User not found! Please login again");
        }else{

        }
        return null;
    }
}
