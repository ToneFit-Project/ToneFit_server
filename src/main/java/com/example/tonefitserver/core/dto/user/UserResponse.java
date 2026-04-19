package com.example.tonefitserver.core.dto.user;

import com.example.tonefitserver.core.enums.*;

import java.time.LocalDateTime;

public record UserResponse(
        Long userId,
        String email,
        int birthYear,
        Industry industry,
        CompanySize companySize,
        JobLevel jobLevel,
        CareerYear careerYear,
        Plan plan,
        int freeUsed,
        int creditBalance,
        LocalDateTime createdAt
) {
}
