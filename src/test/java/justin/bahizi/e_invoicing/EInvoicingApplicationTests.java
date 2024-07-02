package justin.bahizi.e_invoicing;

import justin.bahizi.e_invoicing.domain.Customer;
import justin.bahizi.e_invoicing.domain.EInvoiceStatus;
import justin.bahizi.e_invoicing.domain.Invoice;
import justin.bahizi.e_invoicing.exception.InvalidEmailException;
import justin.bahizi.e_invoicing.message.AppMessage;
import justin.bahizi.e_invoicing.service.CustomerService;
import justin.bahizi.e_invoicing.service.ICustomerService;
import justin.bahizi.e_invoicing.service.InvoiceService;
import justin.bahizi.e_invoicing.util.DataValidator;
import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.time.LocalDate;

@SpringBootTest
class EInvoicingApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    CustomerService customerService;

    @Autowired
    InvoiceService invoiceService;

    @Test
    public void testFindCustomerPositive(){
        Customer customer1 = customerService.findCustomerById(new Long(3));
        Assertions.assertEquals(customer1.getPhoneNumber(),"250788296151");
        Assertions.assertEquals(customer1.getEmail(),"jane@example.com");
    }

    @Test
    public void testFindCustomerNegative(){
        Customer customer1 = customerService.findCustomerById(new Long(3));
        Assertions.assertNotEquals(customer1.getPhoneNumber(),"18272771717171");
        Assertions.assertNotEquals(customer1.getEmail(),"xmels@example.com");
    }

    @Test
    public void testCreateCustomerPositive(){
        Customer customer = new Customer();
        customer.setName("Corol Madisson");
        customer.setEmail("madison@gmail.com");
        customer.setPhoneNumber("250766286340");
        Customer created = customerService.createNewCustomer(customer);
        Assertions.assertEquals(created.getEmail(),"madison@gmail.com");
    }


    @Test
    public void testCreateCustomerNegative(){
        Customer customer = new Customer();
        customer.setName("Marie Curry");
        customer.setEmail("marycurrygmail.com");
        customer.setPhoneNumber("250788259959900");
        Boolean flag = DataValidator.validEmail(customer.getEmail());
        Boolean flag2 = DataValidator.validPhoneNumber(customer.getPhoneNumber());
        Assertions.assertFalse(flag);
        Assertions.assertFalse(flag2);
    }

    @Test
    public void testInvoiceNegative(){Invoice invoice = new Invoice();
       invoice.setAmount(BigDecimal.ZERO);
       invoice.setInvoiceDate(LocalDate.now());
       invoice.setInvoiceStatus(EInvoiceStatus.CREATED);
       invoice.setCustomer(customerService.findCustomerById(new Long("3")));
       String errorMessage = DataValidator.validInvoice(invoice);
       Assertions.assertTrue(errorMessage.equals(AppMessage.INVOICE_AMOUNT_ERROR));
    }

    @Test
    public void testInvoiceNegativeTwo(){Invoice invoice = new Invoice();
        invoice.setAmount(BigDecimal.ZERO);
        invoice.setInvoiceDate(LocalDate.of(2025,05,10));
        invoice.setInvoiceStatus(EInvoiceStatus.CREATED);
        invoice.setCustomer(customerService.findCustomerById(new Long("3")));
        String errorMessage = DataValidator.validInvoice(invoice);
        Assertions.assertTrue(errorMessage.equals(AppMessage.INVOICE_DATE_IN_FUTURE));
    }



}
