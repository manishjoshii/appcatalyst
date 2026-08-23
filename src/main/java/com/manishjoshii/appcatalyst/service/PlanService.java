package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.subscription.PlanResponse;
import org.jspecify.annotations.Nullable;

import java.util.List;

public interface PlanService {
     List<PlanResponse> getAllActivePlans();
}
