package com.Techsphere.TechsphereV2Backend.Controller;

import com.Techsphere.TechsphereV2Backend.Service.AuthService;
import com.Techsphere.TechsphereV2Backend.entity.User;
import com.Techsphere.TechsphereV2Backend.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class TestController {
    @Autowired
    private AuthService authService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public ResponseEntity<String> helloAdmin(){
        return ResponseEntity.ok("Hello Admin");
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/user")
    public ResponseEntity<String> helloUser(){
        return ResponseEntity.ok("Hello User");
    }

    @PreAuthorize("hasRole('OTHER')")
    @GetMapping("/other")
    public ResponseEntity<String> helloOTHER(){
        return ResponseEntity.ok("Hello OTHER");
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/getUsernameOrEmailActive")
    public ResponseEntity<Response<User>> getUsernameOrEmailActive(@RequestParam("usernameOrEmail") String usernameOrEmail) {
        try {
            User user = authService.getUsernameOrEmailActive(usernameOrEmail);
            Response<User> response = new Response<>(user, "Get user registered successfully", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Response<User> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/getUsernameOrEmail")
    public ResponseEntity<Response<User>> getUsernameOrEmail(@RequestParam("usernameOrEmail") String usernameOrEmail) {
        try {
            User user = authService.getUserByUsernameOrEmail(usernameOrEmail);
            Response<User> response = new Response<>(user, "Get user registered successfully", HttpStatus.OK);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (RuntimeException e) {
            Response<User> response = new Response<>(null, e.getMessage(), HttpStatus.BAD_REQUEST);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }


}
