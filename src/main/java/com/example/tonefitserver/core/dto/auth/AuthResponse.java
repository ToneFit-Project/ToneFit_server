package com.example.tonefitserver.core.dto.auth;

import com.example.tonefitserver.core.enums.Plan;

public record AuthResponse(
        Long userId,
        String email,
        Plan plan,
        String accessToken,
        String refreshToken
) {
}
