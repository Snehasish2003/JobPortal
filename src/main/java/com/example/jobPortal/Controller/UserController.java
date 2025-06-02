package com.example.jobPortal.Controller;

import com.example.jobPortal.Dto.LoginDto;
import com.example.jobPortal.Dto.RegistrationDto;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
@Autowired
    UserService userService;
    @PostMapping("/register")
    public ResponseEntity<Object> registration(@RequestBody @Valid RegistrationDto registrationDto)  {

            Response<?> response = userService.register(registrationDto);
            return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));



    }
    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody @Valid LoginDto loginDto)  {

        Response<?> response = userService.login(loginDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));



    }

}
