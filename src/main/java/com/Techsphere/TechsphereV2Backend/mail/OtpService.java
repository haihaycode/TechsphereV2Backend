package com.Techsphere.TechsphereV2Backend.mail;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;
    private final ResourceLoader resourceLoader;


    public OtpService(JavaMailSender mailSender, ResourceLoader resourceLoader) {
        this.mailSender = mailSender;
        this.resourceLoader = resourceLoader;
    }


    private Map<String, String> otpMap = new HashMap<>();
    public String generateOtp(String email) {
        SecureRandom random = new SecureRandom();
        String otp = String.format("%06d", random.nextInt(1000000));
        otpMap.put(email, otp);
        return otp;
    }

    public boolean validateOtp(String email, String otp) {
        if (otpMap.containsKey(email) && otpMap.get(email).equals(otp)) {
            return true;
        }
        return false;
    }
    public void sendOtpEmail(String toEmail, String otp) throws MessagingException {
        String subject = "Your OTP Code";
        String body = "Your OTP code is: " + otp;

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);
    }
    public void sendMailWelcome(String toEmail) throws MessagingException, IOException {
        String subject = "Welcome to Our Service!";
        Resource resource = resourceLoader.getResource("classpath:templates/Mail.html");

        // Đọc nội dung tệp HTML thành một chuỗi
        String body;
        try (InputStream inputStream = resource.getInputStream();
             Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
            body = scanner.useDelimiter("\\A").next();
        }

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(toEmail);
        helper.setSubject(subject);
        helper.setText(body, true);

        mailSender.send(message);
    }

}