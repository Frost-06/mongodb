package com.grokonez.spring.restapi.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grokonez.spring.restapi.mongodb.model.Customer;
import com.grokonez.spring.restapi.mongodb.repo.CustomerRepository;
import com.grokonez.spring.restapi.mongodb.service.CustomerService;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class CustomerController {

	@Autowired
	CustomerRepository repository;

	//get all customers done
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		System.out.println("Get all Customers...");

		return ResponseEntity.ok(CustomerService.getAllCustomers());
	}

	//post done
	@PostMapping("/customer")
	public Customer postCustomer(@RequestBody Customer customer) {
		return CustomerService.addCustomer(customer);
	}

	@DeleteMapping("/customer/{id}")
	public ResponseEntity deleteCustomer(@PathVariable("id") String id) {
		System.out.println("Delete Customer with ID = " + id + "...");

		CustomerService.deleteCustomer(id);

		return new ResponseEntity<>("Customer has been deleted!", HttpStatus.OK);
	}

	//search age done
	@GetMapping("customers/age/{age}")
	public List findByAge(@PathVariable int age) {
		return CustomerService.searchAge(age);
	}

	//update done
	@PutMapping("/customer/{id}")
	public ResponseEntity updateCustomer(@PathVariable("id") String id, @RequestBody Customer customer) {
		System.out.println("Update Customer with ID = " + id + "...");

		Optional customerData = repository.findById(id);

		if (customerData.isPresent()) {
			return new ResponseEntity<>(repository.save(CustomerService.updateCustomer(id, customer)), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}