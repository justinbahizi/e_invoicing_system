package justin.bahizi.e_invoicing.service;

import justin.bahizi.e_invoicing.dao.IInvoiceRepository;
import justin.bahizi.e_invoicing.domain.EInvoiceStatus;
import justin.bahizi.e_invoicing.domain.Invoice;
import justin.bahizi.e_invoicing.exception.InvalidUserDataException;
import justin.bahizi.e_invoicing.util.DataValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service(IInvoiceService.NAME)
public class InvoiceService implements IInvoiceService{

    private final IInvoiceRepository invoiceDao;

    @Autowired
    public InvoiceService(IInvoiceRepository invoiceDao) {
        this.invoiceDao = invoiceDao;
    }


    @Override
    public List<Invoice> findAllInvoices() {
        return invoiceDao.findAll();
    }

    @Override
    public Invoice createNewInvoice(final Invoice invoice) {
        try{
            String errorMessage = DataValidator.validInvoice(invoice);
            if(errorMessage != null){
                throw new InvalidUserDataException(errorMessage);
            }
            return invoiceDao.save(invoice);
        }catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Invoice updateInvoice(final Invoice invoice) {
        return invoiceDao.save(invoice);
    }

    @Override
    public Invoice changeInvoiceStatus(final Invoice invoice, final EInvoiceStatus newStatus) {
       invoice.setInvoiceStatus(newStatus);
       return invoiceDao.save(invoice);
    }

    @Override
    public void deleteInvoice(final Long id) {
        invoiceDao.deleteById(id);
    }
}
