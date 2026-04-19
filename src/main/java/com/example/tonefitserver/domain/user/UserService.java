package com.example.tonefitserver.domain.user;

import com.example.tonefitserver.core.dto.user.UpdateUserRequest;
import com.example.tonefitserver.core.dto.user.UserResponse;
import com.example.tonefitserver.core.enums.ErrorType;
import com.example.tonefitserver.core.enums.UserStatus;
import com.example.tonefitserver.core.exception.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserResponse getMe(String email) {
        User user = userRepository.findByEmailAndStatus(email, UserStatus.ACTIVE)
                .orElseThrow(() -> new BusinessException(ErrorType.USER_NOT_FOUND));
        return toUserResponse(user);
    }

    @Transactional
    public UserResponse updateMe(String email, UpdateUserRequest request) {
        User user = userRepository.findByEmailAndStatus(email, UserStatus.ACTIVE)
                .orElseThrow(() -> new BusinessException(ErrorType.USER_NOT_FOUND));
        user.updateProfile(request.industry(), request.companySize(),
                request.jobLevel(), request.careerYear());
        return toUserResponse(user);
    }

    private UserResponse toUserResponse(User user) {
        return new UserResponse(
                user.getId(), user.getEmail(), user.getBirthYear(),
                user.getIndustry(), user.getCompanySize(), user.getJobLevel(),
                user.getCareerYear(), user.getPlan(), user.getFreeUsed(),
                user.getCreditBalance(), user.getCreatedAt()
        );
    }
}
