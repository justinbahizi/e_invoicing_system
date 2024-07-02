package justin.bahizi.e_invoicing.dao;

import justin.bahizi.e_invoicing.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IInvoiceRepository extends JpaRepository<Invoice, Long> {

}
