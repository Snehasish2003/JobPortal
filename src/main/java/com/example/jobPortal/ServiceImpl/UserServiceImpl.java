package com.example.jobPortal.ServiceImpl;

import com.example.jobPortal.Config.JwtService;
import com.example.jobPortal.Dto.LoginDto;
import com.example.jobPortal.Dto.RegistrationDto;
import com.example.jobPortal.Dto.Response;
import com.example.jobPortal.Entity.User;
import com.example.jobPortal.Exceptions.UserNotFound;
import com.example.jobPortal.Repository.UserRepository;
import com.example.jobPortal.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    JwtService jwtService;
    @Autowired
    AuthenticationManager authenticationManager;

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
    @Override
    public Response<?> register(RegistrationDto registrationDto) {
        if(registrationDto.getId()==null){
            User user = new User();
            user.setUserName(registrationDto.getUserName());
            user.setRole(registrationDto.getRole());
            user.setEmail(registrationDto.getEmail());
            user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
            user.setCreatedAt(new Date(System.currentTimeMillis()));
            userRepository.save(user);
            return new Response<>(HttpStatus.OK.value(),"Registration Successful",null);
        }
        Optional<User> optionalUser = userRepository.findById(registrationDto.getId());
        if(optionalUser.isEmpty()){
            throw new UserNotFound("user not found");
        }
        User user = optionalUser.get();
        user.setUserName(registrationDto.getUserName());
        user.setRole(registrationDto.getRole());
        user.setEmail(registrationDto.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(registrationDto.getPassword()));
        user.setUpdatedAt(new Date(System.currentTimeMillis()));
        userRepository.save(user);
        return new Response<>(HttpStatus.OK.value(),"Update Successful",null);

    }

    @Override
    public Response<?> login(LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(),loginDto.getPassword()));
        if(authentication.isAuthenticated()){

        return new Response<>(HttpStatus.OK.value(), "Login Successful",jwtService.generateToken(loginDto.getEmail()));
        }
        return new Response<>(HttpStatus.BAD_REQUEST.value(), "Login Failed",null);
    }
}
