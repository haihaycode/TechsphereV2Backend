package com.Techsphere.TechsphereV2Backend.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserDTO {
    private String name;
    private String email;
    private String username;
    private String gender;
    private String address;
    private String phoneNumber;
    private String profilePicture;
}
