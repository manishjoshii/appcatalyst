package com.manishjoshii.appcatalyst.service.impl;

import com.manishjoshii.appcatalyst.dto.auth.UserProfileResponse;
import com.manishjoshii.appcatalyst.dto.subscription.PlanLimitsResponse;
import com.manishjoshii.appcatalyst.dto.subscription.UsageTodayResponse;
import com.manishjoshii.appcatalyst.service.UsageService;
import com.manishjoshii.appcatalyst.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UsageServiceImpl implements UsageService {

    @Override
    public UsageTodayResponse getTodayUsageOfUser(Long userId) {
        return null;
    }

    @Override
    public PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId) {
        return null;
    }
}
