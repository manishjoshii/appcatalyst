package com.manishjoshii.appcatalyst.service.impl;

import com.manishjoshii.appcatalyst.dto.subscription.CheckoutRequest;
import com.manishjoshii.appcatalyst.dto.subscription.CheckoutResponse;
import com.manishjoshii.appcatalyst.dto.subscription.PortalResponse;
import com.manishjoshii.appcatalyst.dto.subscription.SubscriptionResponse;
import com.manishjoshii.appcatalyst.service.SubscriptionService;
import org.springframework.stereotype.Service;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {
    @Override
    public SubscriptionResponse getCurrentSubscription(Long userId) {
        return null;
    }

    @Override
    public CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId) {
        return null;
    }

    @Override
    public PortalResponse openCustomerPortal(Long userId) {
        return null;
    }
}
