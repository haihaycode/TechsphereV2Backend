package com.Techsphere.TechsphereV2Backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UploadCategory {
    private String category_name;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
