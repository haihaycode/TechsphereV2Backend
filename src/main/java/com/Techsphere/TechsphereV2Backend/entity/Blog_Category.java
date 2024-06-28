package com.Techsphere.TechsphereV2Backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

    @Entity
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Table(name = "blog_category")
    //Fix database ngày 26-6, lỗi tên
    public class Blog_Category {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "categoryId")
        private Long categoryId;

        @Column(nullable = false, name = "categoryName")
        private String categoryName;

        @Column(nullable = false,name = "isActive")
        private boolean isActive;

        @Column(name = "categoryImage")
        private String categoryImage;

        @Column(nullable = true,name = "createdAt")
        private LocalDateTime createdAt;

        @Column(nullable = true,name = "updatedAt")
        private LocalDateTime updatedAt;
    }
