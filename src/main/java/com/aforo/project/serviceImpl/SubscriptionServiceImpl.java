package com.aforo.project.serviceImpl;

import com.aforo.project.dto.SubscriptionDTO;
import com.aforo.project.exception.ResourceNotFoundException;
import com.aforo.project.dto.CustomerDTO; // Ensure this is imported
import com.aforo.project.model.Subscription;
import com.aforo.project.model.Customer; // Ensure this is imported
import com.aforo.project.repository.SubscriptionRepository;
import com.aforo.project.repository.CustomerRepository; // Ensure this is imported
import com.aforo.project.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private CustomerRepository customerRepository; // Inject CustomerRepository

    @Override
    public SubscriptionDTO createSubscription(@Valid SubscriptionDTO subscriptionDTO) {
        Subscription subscription = mapToEntity(subscriptionDTO);
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return mapToDTO(savedSubscription);
    }

    @Override
    public SubscriptionDTO getSubscriptionById(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));
        return mapToDTO(subscription);
    }

    @Override
    public SubscriptionDTO updateSubscription(Long id, @Valid SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subscription not found"));

        // Update fields
        subscription.setProduct_id(subscriptionDTO.getProduct_id());
        subscription.setStart_date(subscriptionDTO.getStart_date());
        subscription.setEnd_date(subscriptionDTO.getEnd_date());
        subscription.setStatus(subscriptionDTO.getStatus());

        // Handle customer mapping
        if (subscriptionDTO.getCustomer() != null && subscriptionDTO.getCustomer().getId() != null) {
            Customer customer = customerRepository.findById(subscriptionDTO.getCustomer().getId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            subscription.setCustomer(customer);
        } else {
            throw new IllegalArgumentException("Customer information must be provided");
        }

        Subscription updatedSubscription = subscriptionRepository.save(subscription);
        return mapToDTO(updatedSubscription);
    }

    
    @Override
    public void deleteSubscription(Long id) {
        Subscription subscription = subscriptionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Subscription not found with id: " + id));
        subscriptionRepository.delete(subscription);
    }


    @Override
    public List<SubscriptionDTO> getAllSubscriptions() {
        List<Subscription> subscriptions = subscriptionRepository.findAll();
        return subscriptions.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    private SubscriptionDTO mapToDTO(Subscription subscription) {
        return new SubscriptionDTO(
                subscription.getId(),
                subscription.getProduct_id(),
                subscription.getStart_date(),
                subscription.getEnd_date(),
                subscription.getStatus(),
                subscription.getCreated_at(),
                subscription.getUpdated_at(),
                subscription.getCustomer() != null ? mapToDTO(subscription.getCustomer()) : null
        );
    }

    private Subscription mapToEntity(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = new Subscription();
        subscription.setProduct_id(subscriptionDTO.getProduct_id());
        subscription.setStart_date(subscriptionDTO.getStart_date());
        subscription.setEnd_date(subscriptionDTO.getEnd_date());
        subscription.setStatus(subscriptionDTO.getStatus());

        // Ensure the Customer is mapped correctly
        if (subscriptionDTO.getCustomer() != null && subscriptionDTO.getCustomer().getId() != null) {
            Customer customer = customerRepository.findById(subscriptionDTO.getCustomer().getId())
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            subscription.setCustomer(customer);
        } else {
            throw new IllegalArgumentException("Customer information must be provided");
        }

        return subscription;
    }

    private CustomerDTO mapToDTO(Customer customer) {
        // Implement this method to map Customer to CustomerDTO
        return new CustomerDTO(customer.getId()); // Fill in remaining fields
    }
}
