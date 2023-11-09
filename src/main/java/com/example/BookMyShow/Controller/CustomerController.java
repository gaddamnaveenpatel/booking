package com.example.BookMyShow.Controller;

import com.example.BookMyShow.Service.CustomerService;
import com.example.BookMyShow.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

@PostMapping("save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer){

        Customer savecustomer = customerService.createCustomer(customer);

        return new ResponseEntity<>(savecustomer, HttpStatus.OK);
    }

    public ResponseEntity<List<Customer>>getallCustomer(){

    List<Customer> customerList = customerService.getAllCustomers();

    return new ResponseEntity<>(customerList,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getbyid(@PathVariable Long id){

          Customer customer = customerService.getCustomerById(id);
        return new ResponseEntity<>(customer, HttpStatus.OK);


    }


}
