package justin.bahizi.e_invoicing.service;

import justin.bahizi.e_invoicing.domain.Customer;

import java.util.List;

public interface ICustomerService {

    public static String NAME = "CustomerService";

    public abstract List<Customer> findAllCustomers();

    public abstract Customer createNewCustomer(final Customer customer);

    public abstract Customer updateCustomer(final Customer customer);

    public abstract void deleteCustomer(final Long id);

    public abstract Customer findCustomerById(final Long id);

}
