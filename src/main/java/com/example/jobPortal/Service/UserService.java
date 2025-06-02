package com.example.jobPortal.Service;

import com.example.jobPortal.Dto.LoginDto;
import com.example.jobPortal.Dto.RegistrationDto;
import com.example.jobPortal.Dto.Response;
import jakarta.validation.Valid;

public interface UserService {

    Response<?> register(@Valid RegistrationDto registrationDto);

    Response<?> login(@Valid LoginDto loginDto);
}
