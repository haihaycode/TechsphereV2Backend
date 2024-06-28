package com.Techsphere.TechsphereV2Backend.mail;

import com.Techsphere.TechsphereV2Backend.Logout.Blacklist;
import com.Techsphere.TechsphereV2Backend.Service.AuthService;
import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateUserDTO;
import jakarta.mail.MessagingException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class mailController {
    @Autowired
    private AuthService authService;
    @Autowired
    private Blacklist blacklist;
    // Build Login REST API
    @Autowired
    private OtpService otpService;
    @Autowired
    MailServiceImpl mailService;
    @PostMapping("/send")
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Map<String, String> sendOtp() {
        Map<String, String> response = new HashMap<>();
        try {
            UpdateUserDTO userDTO = mailService.sendMail();
            String otp = otpService.generateOtp(userDTO.getEmail());
            otpService.sendOtpEmail(userDTO.getEmail(), otp);
            response.put("message", "OTP sent successfully");
        } catch (RuntimeException | MessagingException e) {
            response.put("message", "Failed to send OTP: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    @PostMapping("/sendWelcome")
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public Map<String, String> welcomeMail() {
        Map<String, String> response = new HashMap<>();
        try {
            UpdateUserDTO userDTO = mailService.sendMail();

            otpService.sendMailWelcome(userDTO.getEmail());
            response.put("message", "sent successfully");
        } catch (RuntimeException | MessagingException | IOException e) {
            response.put("message", "Failed to send: " + e.getMessage());
            e.printStackTrace();
        }
        return response;
    }
    @PostMapping("/verify")
    public Map<String, String> verifyOtp(@RequestParam("otp") String otp, @RequestParam("password") String password) {
        Map<String, String> response = new HashMap<>();
        try {
            UpdateUserDTO userDTO = mailService.sendMail();
            boolean isValid = otpService.validateOtp(userDTO.getEmail(), otp);
            if (isValid) {
                mailService.updatePassword(password); // Cập nhật email mới cho người dùng
                response.put("message", "Email updated successfully");
                // Thực hiện các hoạt động tiếp theo sau khi cập nhật email
            } else {
                response.put("message", "Invalid OTP, please try again");
            }
        } catch (RuntimeException e) {
            response.put("message", "User not found, Please Login again!");
            e.printStackTrace();
        }
        return response;
    }
}
