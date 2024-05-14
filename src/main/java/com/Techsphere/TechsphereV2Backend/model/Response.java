package com.Techsphere.TechsphereV2Backend.model;

import com.Techsphere.TechsphereV2Backend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Response<T> {
    private T data;
    private String message;
    private HttpStatus status;
    private LocalDateTime timestamp;

    public Response(T data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status;
        this.timestamp = LocalDateTime.now();
    }

}
