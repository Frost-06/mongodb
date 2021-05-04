package com.grokonez.spring.restapi.mongodb.service;

import java.util.List;
import java.util.Optional;

import com.grokonez.spring.restapi.mongodb.model.Customer;
import com.grokonez.spring.restapi.mongodb.repo.CustomerRepository;

import org.springframework.stereotype.Service;

@Service
public class CustomerService {
    private static CustomerRepository custrepo;

    public CustomerService(CustomerRepository custrepo) {
        CustomerService.custrepo = custrepo;
    }

    //post add customer
    public static Customer addCustomer(Customer customer) {
        Customer _customerOutput = custrepo.save(new Customer(customer.getName(), customer.getAge()));
    
        return _customerOutput;
    }

    //get all customer
    public static List<Customer> getAllCustomers() {
        return custrepo.findAll();
    }

    //get (search customer by age)
    public static List<Customer> searchAge(int age) {
        List customers = custrepo.findByAge(age);
		return customers;
    }

    //delete customer data
    public static void deleteCustomer(String id) {
        custrepo.deleteById(id);
    }

    //update customers
    public static Customer updateCustomer(String id, Customer customer) {
        Optional customerData = custrepo.findById(id);
        
        Customer _customer = (Customer) customerData.get();
			_customer.setName(customer.getName());
			_customer.setAge(customer.getAge());
			_customer.setActive(customer.isActive());
       
        return _customer;
    }

}
