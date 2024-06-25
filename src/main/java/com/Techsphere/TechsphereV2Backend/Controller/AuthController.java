package com.Techsphere.TechsphereV2Backend.Controller;


import com.Techsphere.TechsphereV2Backend.Logout.Blacklist;
import com.Techsphere.TechsphereV2Backend.Service.AuthService;
import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateUserDTO;
import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateUserImageDTO;
import com.Techsphere.TechsphereV2Backend.entity.User;
import com.Techsphere.TechsphereV2Backend.model.JwtAuthResponse;
import com.Techsphere.TechsphereV2Backend.model.LoginDto;
import com.Techsphere.TechsphereV2Backend.model.Response;
import com.Techsphere.TechsphereV2Backend.model.SignUpDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @Autowired
    private Blacklist blacklist;
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
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        String token = null;

        // Kiểm tra và lấy token từ header Authorization
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }

        if (token != null) {
            // Đưa token vào blacklist
            blacklist.blackListToken(token);
            return ResponseEntity.ok("Logout successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Logout failed");
        }
    }

    @GetMapping ("/account/auth")
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Response<UpdateUserDTO>> Profile(){
        try {
            UpdateUserDTO user = authService.UserInfo();
            Response<UpdateUserDTO> response = new Response<>(user, "Info account ", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (RuntimeException e){
            Response<UpdateUserDTO> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/account/update/profile")
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Response<User>> updateProfile(@RequestBody UpdateUserDTO updateUserDTO){
        try {
            User user = authService.updateUserInfo(updateUserDTO);

            Response<User> response = new Response<>(user, "User updated successfully", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (RuntimeException e){
            Response<User> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

        @PostMapping("/account/update/avatar")
    @ResponseBody
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Response<User>> updateUserImage(@RequestParam("image")MultipartFile file){
        try {
            User user = authService.updateUserImage(file);

            Response<User> response = new Response<>(user, "User updated successfully", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }catch (RuntimeException e){
            Response<User> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }

}