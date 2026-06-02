package com.rydo.auth.service;

import com.rydo.auth.dto.AuthResponse;
import com.rydo.auth.dto.RegisterRequest;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {

    AuthResponse register(RegisterRequest request);
}
