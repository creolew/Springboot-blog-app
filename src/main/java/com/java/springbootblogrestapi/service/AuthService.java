package com.java.springbootblogrestapi.service;

import com.java.springbootblogrestapi.payload.LoginDto;
import com.java.springbootblogrestapi.payload.RegisterDto;

public interface AuthService {
    String login(LoginDto loginDto);

    String register(RegisterDto registerDto);
}
