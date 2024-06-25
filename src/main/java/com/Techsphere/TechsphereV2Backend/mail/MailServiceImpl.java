package com.Techsphere.TechsphereV2Backend.mail;

import com.Techsphere.TechsphereV2Backend.Repository.RoleRepository;
import com.Techsphere.TechsphereV2Backend.Repository.UserRepository;
import com.Techsphere.TechsphereV2Backend.Service.Image.ImageStorageService;
import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateUserDTO;
import com.Techsphere.TechsphereV2Backend.entity.User;
import com.Techsphere.TechsphereV2Backend.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MailServiceImpl {
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    @Autowired
    ImageStorageService imageStorageService;
    public UpdateUserDTO sendMail() {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsernameOrEmail(currentUsername, currentUsername);
        if (currentUser == null) {
            throw new RuntimeException("User not found, Please Login again!");
        }
        UpdateUserDTO userDTO = new UpdateUserDTO();
        userDTO.setEmail(currentUser.getEmail());
        return userDTO;
    }
    public void updatePassword(String newPassword) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsernameOrEmail(currentUsername, currentUsername);
        if (currentUser != null) {
            currentUser.setPassword(new BCryptPasswordEncoder().encode(newPassword)); // Mã hóa mật khẩu mới
            userRepository.save(currentUser);
        } else {
            throw new RuntimeException("User not found, Please Login again!");
        }
    }
}
