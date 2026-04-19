package com.example.tonefitserver.core.dto.auth;

import com.example.tonefitserver.core.enums.CareerYear;
import com.example.tonefitserver.core.enums.CompanySize;
import com.example.tonefitserver.core.enums.Industry;
import com.example.tonefitserver.core.enums.JobLevel;
import jakarta.validation.constraints.*;

public record SignupRequest(
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @NotBlank(message = "비밀번호는 필수입니다.")
        @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
        String password,

        @NotNull(message = "출생연도는 필수입니다.")
        Integer birthYear,

        @NotNull(message = "산업군은 필수입니다.")
        Industry industry,

        @NotNull(message = "회사 규모는 필수입니다.")
        CompanySize companySize,

        @NotNull(message = "직급은 필수입니다.")
        JobLevel jobLevel,

        @NotNull(message = "연차는 필수입니다.")
        CareerYear careerYear
) {
}
