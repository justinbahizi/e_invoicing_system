package justin.bahizi.e_invoicing.service;

import justin.bahizi.e_invoicing.exception.CustomerNotFoundException;
import justin.bahizi.e_invoicing.exception.InvalidEmailException;
import justin.bahizi.e_invoicing.exception.InvalidPhoneException;
import justin.bahizi.e_invoicing.dao.ICustomerRepository;
import justin.bahizi.e_invoicing.domain.Customer;
import justin.bahizi.e_invoicing.message.AppMessage;
import justin.bahizi.e_invoicing.util.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(ICustomerService.NAME)
public class CustomerService implements ICustomerService {

    private final ICustomerRepository customerDao;

    @Autowired
    public CustomerService(ICustomerRepository customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public List<Customer> findAllCustomers() {
        return customerDao.findAll();
    }

    @Override
    public Customer createNewCustomer(final Customer customer) {
        Boolean invalidEmail = DataValidator.validEmail(customer.getEmail());
        Boolean invalidPhone = DataValidator.validPhoneNumber(customer.getPhoneNumber());
        if(!invalidEmail){
            throw new InvalidEmailException(AppMessage.INVALID_EMAIL + customer.getEmail()+" !! Please provide a valid email address");
        }
        if(!invalidPhone){
            throw new InvalidPhoneException(AppMessage.INVALID_PHONE+customer.getPhoneNumber()+" !! Please provide a valid phone, eg: 250788286350");
        }
        return customerDao.save(customer);
    }

    @Override
    public Customer updateCustomer(final Customer customer) {
        return customerDao.save(customer);
    }

    @Override
    public void deleteCustomer(final Long id) {
        customerDao.deleteById(id);
    }

    @Override
    public Customer findCustomerById(final Long id) {
        return customerDao.findCustomerById(id).
                orElseThrow(() -> new CustomerNotFoundException("Customer with id " +id + " not found in the system," +
                        " Please try a valid customer"));
    }

}
