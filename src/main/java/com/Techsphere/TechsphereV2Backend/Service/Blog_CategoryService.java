package com.Techsphere.TechsphereV2Backend.Service;

import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;

import java.util.List;

public interface Blog_CategoryService {
    List<Blog_Category> getAll();
    public Blog_Category getById(int id);
    public Blog_Category Upload(Blog_Category category);
}
