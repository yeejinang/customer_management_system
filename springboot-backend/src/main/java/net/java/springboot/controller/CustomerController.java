package net.java.springboot.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import net.java.springboot.exception.ResourceNotFoundException;
import net.java.springboot.model.Customer;
import net.java.springboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200")
public class CustomerController {

    @Autowired
    private CustomerRepository customerRepository;

    //get all customers
    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    //create customer rest api
    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Customer customer) {
        Optional<Customer> customerByPhoneNumber = customerRepository
                .findCustomerByPhoneNumber(customer.getPhoneNumber());
        if (customerByPhoneNumber.isPresent()) {
            throw new IllegalStateException("Phone number existed!");
        }
        return customerRepository.save(customer);
    }

    //get customer by id rest api
    @GetMapping("/customers/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: " + id));
        return (ResponseEntity.ok(customer));
    }

    //update customer rest api
    @PutMapping("/customers/{id}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable Long id, @RequestBody Customer customerDetails) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer not exist with id: " + id));
        customer.setFirstName(customerDetails.getFirstName());
        customer.setLastName(customerDetails.getLastName());
        customer.setEmailId(customerDetails.getEmailId());
        Optional<Customer> customerByPhoneNumber = customerRepository
                .findCustomerByPhoneNumber(customerDetails.getPhoneNumber());
        if (customerByPhoneNumber.isPresent()) {
            throw new IllegalStateException("Phone number existed!");
        }
        customer.setPhoneNumber(customerDetails.getPhoneNumber());
//        customer.setDateOfBirth(customerDetails.getDateOfBirth());
        Customer updatedCustomer = customerRepository.save(customer);
        return ResponseEntity.ok(updatedCustomer);
    }

    @DeleteMapping("/customers/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteCustomer(@PathVariable Long id) {
        boolean exist = customerRepository.existsById(id);
        if (!exist) {
            throw new IllegalStateException("Customer with id " + id + "does not exist!");
        }
        customerRepository.deleteById(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
