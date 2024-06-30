package com.Techsphere.TechsphereV2Backend.dto.auth.Blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Blog_PostDTO {
    private Long PostId;
    private String Title;
    private String content;
    private String image;
    private boolean isActive;
    private Long categoryId;
    private Long authorId;
    private MultipartFile imageFile;
    private String tags; // Danh sách các tag

}
