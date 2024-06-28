package com.Techsphere.TechsphereV2Backend.Repository;

import com.Techsphere.TechsphereV2Backend.entity.Blog_Category;

import java.util.List;

public interface Blog_CategoryRepository {
    public List<Blog_Category> findAll();
}