package com.aforo.project.service;

import com.aforo.project.dto.SubscriptionDTO;
import java.util.List;

public interface SubscriptionService {
    SubscriptionDTO createSubscription(SubscriptionDTO subscriptionDTO);
    SubscriptionDTO getSubscriptionById(Long id);
    SubscriptionDTO updateSubscription(Long id, SubscriptionDTO subscriptionDTO);
    void deleteSubscription(Long id);
    List<SubscriptionDTO> getAllSubscriptions();
}
