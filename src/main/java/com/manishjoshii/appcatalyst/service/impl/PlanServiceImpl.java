package com.manishjoshii.appcatalyst.service.impl;

import com.manishjoshii.appcatalyst.dto.subscription.PlanResponse;
import com.manishjoshii.appcatalyst.service.PlanService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanServiceImpl implements PlanService {
    @Override
    public List<PlanResponse> getAllActivePlans() {
        return List.of();
    }
}
