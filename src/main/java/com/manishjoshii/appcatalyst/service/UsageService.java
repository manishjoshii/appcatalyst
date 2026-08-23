package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.subscription.PlanLimitsResponse;
import com.manishjoshii.appcatalyst.dto.subscription.UsageTodayResponse;
import org.jspecify.annotations.Nullable;

public interface UsageService {
     UsageTodayResponse getTodayUsageOfUser(Long userId);

    PlanLimitsResponse getCurrentSubscriptionLimitsOfUser(Long userId);
}
