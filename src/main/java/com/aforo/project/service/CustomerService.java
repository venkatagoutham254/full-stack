package com.aforo.project.service;
import com.aforo.project.dto.CustomerDTO;
import java.util.List;
public interface CustomerService {
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    CustomerDTO getCustomerById(Long id);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomer(Long id);
    List<CustomerDTO> getAllCustomers();
}
