package justin.bahizi.e_invoicing.controller;

import justin.bahizi.e_invoicing.domain.Customer;
import justin.bahizi.e_invoicing.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-invoicing/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> listAllCustomers(){
        List<Customer> all = customerService.findAllCustomers();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @GetMapping("/findOne/{id}")
    public ResponseEntity<Customer> findCustomerById(@PathVariable("id") Long id){
        Customer customer = customerService.findCustomerById(id);
        return new ResponseEntity<>(customer,HttpStatus.OK);
    }

    @PostMapping("/addNew")
    public ResponseEntity<Customer> createNewCustomer(@RequestBody Customer customer){
        Customer created = customerService.createNewCustomer(customer);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer){
        Customer updated = customerService.updateCustomer(customer);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable("id") Long id){
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
