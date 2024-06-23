package com.Techsphere.TechsphereV2Backend.implementation;

import com.Techsphere.TechsphereV2Backend.Repository.RoleRepository;
import com.Techsphere.TechsphereV2Backend.Repository.UserRepository;
import com.Techsphere.TechsphereV2Backend.Service.AuthService;
import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateUserDTO;
import com.Techsphere.TechsphereV2Backend.entity.Role;
import com.Techsphere.TechsphereV2Backend.entity.User;
import com.Techsphere.TechsphereV2Backend.model.LoginDto;
import com.Techsphere.TechsphereV2Backend.model.SignUpDto;
import com.Techsphere.TechsphereV2Backend.security.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);


        String token = jwtTokenProvider.generateToken(authentication);

        return token;
    }
    @Override
    public User signUp(SignUpDto signUpDto) {
        // Check if username or email already exists
        if (userRepository.existsByUsername(signUpDto.getUsername()) ||
                userRepository.existsByEmail(signUpDto.getEmail())) {

            throw new RuntimeException("Username or email already exists");
        }


        User user = new User();
        user.setName(signUpDto.getName());
        user.setUsername(signUpDto.getUsername());
        user.setEmail(signUpDto.getEmail());
        user.setActive(true);
        user.setPassword(new BCryptPasswordEncoder().encode(signUpDto.getPassword()));

            Role defaultRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(defaultRole));
        // Save user to database
        return userRepository.save(user);
    }
    @Override
    public User getUserByUsernameOrEmail(String usernameOrEmail){
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (user == null) {
            throw new RuntimeException("Username or email not found");
        }

        return user;
    }
    @Override
    public User getUsernameOrEmailActive(String usernameOrEmail){
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail);
        if (user == null) {
            throw new RuntimeException("Username or email not found");
        }
        if (!user.getActive()) {
            throw new RuntimeException("User account is deactivated");
        }
        return user;
    }

    public User getUserById(String Id) {
        Optional<User> optionalUser = userRepository.findById(Long.parseLong(Id));
        return optionalUser.orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User updateUserInfo(UpdateUserDTO updateUserDTO) {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        User currentUser = userRepository.findByUsernameOrEmail(currentUsername, currentUsername);
        if (currentUser == null) {
            throw new RuntimeException("User not found , Please Login again !");
        }
        currentUser.setGender(updateUserDTO.getGender());
        currentUser.setAddress(updateUserDTO.getAddress());
        currentUser.setName(updateUserDTO.getName());
        currentUser.setPhoneNumber(updateUserDTO.getPhoneNumber());

        return userRepository.save(currentUser);

    }

}