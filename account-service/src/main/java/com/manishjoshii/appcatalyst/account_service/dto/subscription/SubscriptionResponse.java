package com.manishjoshii.appcatalyst.account_service.dto.subscription;

import com.manishjoshii.appcatalyst.common_lib.dto.PlanDto;

import java.time.Instant;

public record SubscriptionResponse(
        PlanDto plan,
        String status,
        Instant currentPeriodEnd,
        Long tokensUsedThisCycle
) {
}