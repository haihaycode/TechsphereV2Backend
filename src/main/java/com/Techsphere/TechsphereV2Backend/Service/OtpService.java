package com.Techsphere.TechsphereV2Backend.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {

    @Autowired
    private JavaMailSender mailSender;
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


}
