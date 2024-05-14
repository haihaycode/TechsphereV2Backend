package com.Techsphere.TechsphereV2Backend.Controller;

import com.Techsphere.TechsphereV2Backend.Service.AuthService;
import com.Techsphere.TechsphereV2Backend.entity.User;
import com.Techsphere.TechsphereV2Backend.model.JwtAuthResponse;
import com.Techsphere.TechsphereV2Backend.model.LoginDto;
import com.Techsphere.TechsphereV2Backend.model.Response;
import com.Techsphere.TechsphereV2Backend.model.SignUpDto;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private AuthService authService;

    // Build Login REST API
    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponse> login(@RequestBody LoginDto loginDto){
        try {
        String token = authService.login(loginDto);

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setAccessToken(token);

        return new ResponseEntity<>(jwtAuthResponse, HttpStatus.OK);
        }
        catch (RuntimeException e){
            JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
            jwtAuthResponse.setError(e.getMessage());
            return new ResponseEntity<>(jwtAuthResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<Response<User>> signUp(@RequestBody SignUpDto signUpDto){
         try {
             User user = authService.signUp(signUpDto);
             Response<User> response = new Response<>(user, "User registered successfully", HttpStatus.OK);
             return new ResponseEntity<>(response, HttpStatus.OK);
         }catch (RuntimeException e){
             Response<User> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
             return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
         }

    }

}