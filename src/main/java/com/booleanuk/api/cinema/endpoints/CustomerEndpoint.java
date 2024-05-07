package com.booleanuk.api.cinema.endpoints;

import com.booleanuk.api.cinema.models.Customer;
import com.booleanuk.api.cinema.models.Response;
import com.booleanuk.api.cinema.repositories.CustomerRepository;
import com.booleanuk.api.cinema.services.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="customers")
public class CustomerEndpoint {
    @Autowired
    private CustomerRepository repository;

    @GetMapping
    public ResponseEntity<Response<List<Customer>>> getCustomers() {
        List<Customer> customers = repository.findAll();
        return ResponseUtil.buildResponseEntity(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<Customer>> getCustomer(@PathVariable long id) {
        Customer customer = repository.findById(id).orElse(null);

        return ResponseUtil.buildResponseEntity(customer);
    }

    @PostMapping
    public ResponseEntity<Response<Customer>> createCustomer(@RequestBody Customer inputCustomer) {
        Customer customer = repository.save(inputCustomer);

        return ResponseUtil.buildResponseEntity(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<Customer>> updateCustomer(@PathVariable long id, @RequestBody Customer customerInput) {
        Customer customer = repository.findById(id)
                .map(c -> {
                    c.update(customerInput);
                    return repository.save(c);
                })
                .orElse(null);

        return ResponseUtil.buildResponseEntity(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<Customer>> deleteCustomer(@PathVariable long id) {
        Customer customer = repository.findById(id)
                .map(c -> {
                    repository.delete(c);
                    return c;
                })
                .orElse(null);

        return ResponseUtil.buildResponseEntity(customer);
    }
}
