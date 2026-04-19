package com.example.tonefitserver.domain.auth;

import com.example.tonefitserver.core.dto.ApiResponse;
import com.example.tonefitserver.core.dto.auth.AuthResponse;
import com.example.tonefitserver.core.dto.auth.LoginRequest;
import com.example.tonefitserver.core.dto.auth.LoginResponse;
import com.example.tonefitserver.core.dto.auth.ReissueRequest;
import com.example.tonefitserver.core.dto.auth.SignupRequest;
import com.example.tonefitserver.core.dto.auth.TokenResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<AuthResponse> signup(@RequestBody @Valid SignupRequest request) {
        return ApiResponse.success(authService.signup(request));
    }

    @PostMapping("/login")
    public ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        return ApiResponse.success(authService.login(request));
    }

    @PostMapping("/refresh")
    public ApiResponse<TokenResponse> refresh(@RequestBody @Valid ReissueRequest request) {
        return ApiResponse.success(authService.refresh(request));
    }
}
