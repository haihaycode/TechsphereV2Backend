package com.Techsphere.TechsphereV2Backend.Service;

import com.Techsphere.TechsphereV2Backend.entity.User;
import com.Techsphere.TechsphereV2Backend.model.LoginDto;
import com.Techsphere.TechsphereV2Backend.model.SignUpDto;

public interface AuthService {
    String login(LoginDto loginDto);
    User   signUp(SignUpDto signUpDto);

    User getUserByUsernameOrEmail(String usernameOrEmail);
    User getUsernameOrEmailActive(String usernameOrEmail);

    User getUserById(String Id);

//    String changePassword(String passOld ,String passNew, int OTP);


}