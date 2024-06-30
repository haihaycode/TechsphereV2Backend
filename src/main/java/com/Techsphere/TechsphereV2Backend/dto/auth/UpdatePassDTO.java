package com.Techsphere.TechsphereV2Backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor@AllArgsConstructor
public class UpdatePassDTO {
    private String otp;
    private String password;
}
