package com.manishjoshii.appcatalyst.account_service.mapper;

import com.manishjoshii.appcatalyst.account_service.dto.subscription.SubscriptionResponse;
import com.manishjoshii.appcatalyst.account_service.entity.Plan;
import com.manishjoshii.appcatalyst.account_service.entity.Subscription;
import com.manishjoshii.appcatalyst.common_lib.dto.PlanDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionResponse toSubscriptionResponse(Subscription subscription);

    PlanDto toPlanResponse(Plan plan);
}