package justin.bahizi.e_invoicing.service;

import justin.bahizi.e_invoicing.domain.EInvoiceStatus;
import justin.bahizi.e_invoicing.domain.Invoice;

import java.util.List;

public interface IInvoiceService {

    public static final String NAME = "InvoiceService";

    public abstract List<Invoice> findAllInvoices();

    public abstract Invoice createNewInvoice(final Invoice invoice);

    public abstract Invoice updateInvoice(final Invoice invoice);

    public abstract Invoice changeInvoiceStatus(final Invoice invoice, final EInvoiceStatus newStatus);

    public abstract void deleteInvoice(final Long iid);
}
