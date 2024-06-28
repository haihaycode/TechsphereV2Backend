package com.Techsphere.TechsphereV2Backend.dto.auth;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Data
@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCategoryDTO {
    private String categoryName;
    private MultipartFile categoryImage;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isActive;

}
