package com.Techsphere.TechsphereV2Backend.Service;

import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateUserDTO;
import com.Techsphere.TechsphereV2Backend.dto.auth.UpdateUserImageDTO;
import com.Techsphere.TechsphereV2Backend.entity.User;
import com.Techsphere.TechsphereV2Backend.model.LoginDto;
import com.Techsphere.TechsphereV2Backend.model.SignUpDto;
import org.hibernate.sql.Update;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AuthService {
    String login(LoginDto loginDto);
    User   signUp(SignUpDto signUpDto);

    User getUserByUsernameOrEmail(String usernameOrEmail);
    User getUsernameOrEmailActive(String usernameOrEmail);

    List<User> getAll();



    User getUserById(String Id);

//    String changePassword(String passOld ,String passNew, int OTP);

    User updateUserInfo(UpdateUserDTO updateUserDTO);

    User updateUserEmail(UpdateUserDTO updateUserDTO);

    UpdateUserDTO UserInfo();

    User updateUserImage(MultipartFile file);

    UpdateUserDTO sendMail();
    void updatePassword(String newPassword);



}