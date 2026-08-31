package com.manishjoshii.appcatalyst.mapper;

import com.manishjoshii.appcatalyst.dto.subscription.PlanResponse;
import com.manishjoshii.appcatalyst.dto.subscription.SubscriptionResponse;
import com.manishjoshii.appcatalyst.entity.Plan;
import com.manishjoshii.appcatalyst.entity.Subscription;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    SubscriptionResponse toSubscriptionResponse(Subscription subscription);

    PlanResponse toPlanResponse(Plan plan);
}
