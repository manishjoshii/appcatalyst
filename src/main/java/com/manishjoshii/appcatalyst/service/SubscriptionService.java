package com.manishjoshii.appcatalyst.service;

import com.manishjoshii.appcatalyst.dto.subscription.CheckoutRequest;
import com.manishjoshii.appcatalyst.dto.subscription.CheckoutResponse;
import com.manishjoshii.appcatalyst.dto.subscription.PortalResponse;
import com.manishjoshii.appcatalyst.dto.subscription.SubscriptionResponse;
import org.jspecify.annotations.Nullable;

public interface SubscriptionService {
    SubscriptionResponse getCurrentSubscription(Long userId);

    CheckoutResponse createCheckoutSessionUrl(CheckoutRequest request, Long userId);

    PortalResponse openCustomerPortal(Long userId);
}
