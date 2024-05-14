package com.Techsphere.TechsphereV2Backend.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignUpDto {
    private String name;
    private String username;
    private String email;
    private String password;
}
