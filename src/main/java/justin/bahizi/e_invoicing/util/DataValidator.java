package justin.bahizi.e_invoicing.util;

import justin.bahizi.e_invoicing.domain.Invoice;
import justin.bahizi.e_invoicing.message.AppMessage;

import java.math.BigDecimal;
import java.time.LocalDate;

public class DataValidator {

    /**
     *
     * For simplicity, let's just check if the email contains the character '@'
     *
     */
    public static Boolean validEmail(final String email){

        if(email == null || email.isEmpty())
            return Boolean.FALSE;

        if(!email.contains("@")){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    /**
     * I am assuming that only valid numbers are ranging from 0 to 9.
     * and the length is 12, For example: 250788286350
     *
     */
    public static Boolean validPhoneNumber(final String phone){

        String regex = "^[0-9]*$";

        if(phone == null || phone.isEmpty())
            return Boolean.FALSE;

        if(phone.trim().length() != 12)
            return Boolean.FALSE;

        if(!phone.matches(regex))
            return Boolean.FALSE;

        return Boolean.TRUE;
    }

    /**
     * I am assuming that there is no invoice with zero or negative amount
     * and the invoice date should be valid and not in the future. i.e: a date greater that today.
     */
    public static String validInvoice(final Invoice invoice){
        String errorMessage = null;

        if(invoice.getAmount().compareTo(BigDecimal.ZERO) <= 0)
            errorMessage = AppMessage.INVOICE_AMOUNT_ERROR;

        if(invoice.getInvoiceDate() == null)
            errorMessage = AppMessage.INVOICE_DATE_REQUIRED;

        if(invoice.getInvoiceDate() != null && invoice.getInvoiceDate().isAfter(LocalDate.now()))
           errorMessage = AppMessage.INVOICE_DATE_IN_FUTURE;

       return errorMessage;
    }
}
