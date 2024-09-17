package com.aforo.project.serviceImpl;

import com.aforo.project.dto.CustomerDTO;
import com.aforo.project.exception.ResourceNotFoundException;
import com.aforo.project.model.Customer;
import com.aforo.project.model.User;
import com.aforo.project.repository.CustomerRepository;
import com.aforo.project.repository.UserRepository;
import com.aforo.project.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = mapToEntity(customerDTO);

        // Ensure User exists before setting it to Customer
        if (customerDTO.getUser() != null && customerDTO.getUser().getId() != null) {
            User user = userRepository.findById(customerDTO.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + customerDTO.getUser().getId()));
            customer.setUser(user);
        }

        Customer savedCustomer = customerRepository.save(customer);
        return mapToDTO(savedCustomer);
    }

    @Override
    public CustomerDTO getCustomerById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        return mapToDTO(customer);
    }

    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));

        // Update fields
        customer.setFirst_name(customerDTO.getFirst_name());
        customer.setLast_name(customerDTO.getLast_name());
        customer.setCompany_name(customerDTO.getCompany_name());
        customer.setPhone(customerDTO.getPhone());

        // Update User if provided
        if (customerDTO.getUser() != null && customerDTO.getUser().getId() != null) {
            User user = userRepository.findById(customerDTO.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + customerDTO.getUser().getId()));
            customer.setUser(user);
        }

        Customer updatedCustomer = customerRepository.save(customer);
        return mapToDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + id));
        customerRepository.delete(customer);
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Helper methods for mapping
    private CustomerDTO mapToDTO(Customer customer) {
        return new CustomerDTO(
                customer.getId(),
                customer.getFirst_name(),
                customer.getLast_name(),
                customer.getCompany_name(),
                customer.getPhone(),
                customer.getCreated_at(),
                customer.getUpdated_at(),
                null,  // Map userDTO and subscriptionsDTO if needed
                null
        );
    }

    private Customer mapToEntity(CustomerDTO customerDTO) {
        Customer customer = new Customer();
        customer.setFirst_name(customerDTO.getFirst_name());
        customer.setLast_name(customerDTO.getLast_name());
        customer.setCompany_name(customerDTO.getCompany_name());
        customer.setPhone(customerDTO.getPhone());
        return customer;
    }
}

