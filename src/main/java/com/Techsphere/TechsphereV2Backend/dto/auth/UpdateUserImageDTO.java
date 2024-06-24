package com.Techsphere.TechsphereV2Backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserImageDTO {
    MultipartFile file ;
    String time;
}
