package justin.bahizi.e_invoicing.dao;

import justin.bahizi.e_invoicing.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findCustomerById(final Long id);
}
