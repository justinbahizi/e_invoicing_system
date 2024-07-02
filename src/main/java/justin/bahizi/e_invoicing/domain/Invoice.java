package justin.bahizi.e_invoicing.domain;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "INV_INVOICE")
public class Invoice implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private BigDecimal amount = BigDecimal.ZERO;

    @Column(nullable = false)
    private LocalDate invoiceDate = LocalDate.now();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EInvoiceStatus invoiceStatus = EInvoiceStatus.CREATED;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Customer customer;

    public Invoice() {
    }

    public Invoice(Long id, BigDecimal amount, LocalDate invoiceDate, EInvoiceStatus invoiceStatus, Customer customer) {
        this.id = id;
        this.amount = amount;
        this.invoiceDate = invoiceDate;
        this.invoiceStatus = invoiceStatus;
        this.customer = customer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(LocalDate invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public EInvoiceStatus getInvoiceStatus() {
        return invoiceStatus;
    }

    public void setInvoiceStatus(EInvoiceStatus invoiceStatus) {
        this.invoiceStatus = invoiceStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return id.equals(invoice.id) && amount.equals(invoice.amount) && invoiceDate.equals(invoice.invoiceDate) && customer.equals(invoice.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, amount, invoiceDate, customer);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", amount=" + amount +
                ", invoiceDate=" + invoiceDate +
                ", invoiceStatus=" + invoiceStatus +
                ", customerId=" + customer +
                '}';
    }
}
