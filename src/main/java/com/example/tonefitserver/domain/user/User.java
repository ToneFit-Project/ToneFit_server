package com.example.tonefitserver.domain.user;

import com.example.tonefitserver.core.enums.*;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private UserStatus status;

    @Column(nullable = false)
    private int birthYear;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Industry industry;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanySize companySize;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private JobLevel jobLevel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CareerYear careerYear;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Plan plan;

    @Column(nullable = false)
    private int freeUsed;

    @Column(nullable = false)
    private int creditBalance;

    @CreatedDate
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Builder
    public User(String email, String password, UserStatus status,
                int birthYear, Industry industry, CompanySize companySize,
                JobLevel jobLevel, CareerYear careerYear) {
        this.email = email;
        this.password = password;
        this.status = status != null ? status : UserStatus.ACTIVE;
        this.birthYear = birthYear;
        this.industry = industry;
        this.companySize = companySize;
        this.jobLevel = jobLevel;
        this.careerYear = careerYear;
        this.plan = Plan.FREE;
        this.freeUsed = 0;
        this.creditBalance = 0;
    }

    public void deactivate() {
        this.status = UserStatus.INACTIVE;
    }

    public void updateProfile(Industry industry, CompanySize companySize,
                              JobLevel jobLevel, CareerYear careerYear) {
        if (industry != null) this.industry = industry;
        if (companySize != null) this.companySize = companySize;
        if (jobLevel != null) this.jobLevel = jobLevel;
        if (careerYear != null) this.careerYear = careerYear;
    }
}
