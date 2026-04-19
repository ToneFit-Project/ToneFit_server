package com.example.tonefitserver.core.dto.user;

import com.example.tonefitserver.core.enums.CareerYear;
import com.example.tonefitserver.core.enums.CompanySize;
import com.example.tonefitserver.core.enums.Industry;
import com.example.tonefitserver.core.enums.JobLevel;

public record UpdateUserRequest(
        Industry industry,
        CompanySize companySize,
        JobLevel jobLevel,
        CareerYear careerYear
) {
}
