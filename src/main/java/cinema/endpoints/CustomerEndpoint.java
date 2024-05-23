package cinema.endpoints;

import cinema.exceptions.ResourceNotFoundException;
import cinema.models.Customer;
import cinema.models.Response;
import cinema.repositories.CustomerRepository;
import cinema.services.ResponseUtil;
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
    public ResponseEntity<Response<?>> getCustomers() {
        List<Customer> customers = repository.findAll();
        return ResponseUtil.buildResponse(customers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<?>> getCustomer(@PathVariable long id) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer with "+id+" not found"));

        return ResponseUtil.buildResponse(customer);
    }

    @PostMapping
    public ResponseEntity<Response<?>> createCustomer(@RequestBody Customer inputCustomer) {
        Customer customer = repository.save(inputCustomer);

        return ResponseUtil.buildResponse(customer);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<?>> updateCustomer(@PathVariable long id, @RequestBody Customer customerInput) {
        Customer customer = repository.findById(id)
                .map(c -> {
                    c.update(customerInput);
                    return repository.save(c);
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer with "+id+" not found"));

        return ResponseUtil.buildResponse(customer);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<?>> deleteCustomer(@PathVariable long id) {
        Customer customer = repository.findById(id)
                .map(c -> {
                    repository.delete(c);
                    return c;
                })
                .orElseThrow(() -> new ResourceNotFoundException("Customer with "+id+" not found"));

        return ResponseUtil.buildResponse(customer);
    }
}
