package com.example.BookMyShow.ServiceImp;

import com.example.BookMyShow.Excpetion.CustomerNotFoundException;
import com.example.BookMyShow.Repostiory.CustomerRepostiory;
import com.example.BookMyShow.Service.CustomerService;
import com.example.BookMyShow.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImp implements CustomerService {

    @Autowired
    private CustomerRepostiory customerRepostiory;
    @Override
    public Customer createCustomer(Customer customer) {

        if(customer.getName()==null || customer.getName().isEmpty()) {

            throw new CustomerNotFoundException("Customer name is required.");
        }
        customer.setTickets(customer.getTickets());

        return customerRepostiory.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepostiory.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {

        Optional<Customer> customerOptional = customerRepostiory.findById(id);

        if (customerOptional.isPresent()) {
            return customerOptional.get();
        } else {
            throw new CustomerNotFoundException("Customer not found with ID: " + id);
        }
    }

    @Override
    public Customer updateCustomer(Long id, Customer customer) {
        return null;
    }

    @Override
    public void deleteCustomer(Long id) {

    }
}
