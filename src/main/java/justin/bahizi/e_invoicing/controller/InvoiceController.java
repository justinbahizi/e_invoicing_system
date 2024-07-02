package justin.bahizi.e_invoicing.controller;

import justin.bahizi.e_invoicing.domain.Invoice;
import justin.bahizi.e_invoicing.service.InvoiceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/e-invoicing/api/v1/invoice")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Invoice>> listAllInvoices(){
        List<Invoice> all = invoiceService.findAllInvoices();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping("/addNew")
    public ResponseEntity<Invoice> createNewInvoice(@RequestBody Invoice invoice){
        Invoice created = invoiceService.createNewInvoice(invoice);
        return new ResponseEntity<>(created,HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<Invoice> updateInvoice(@RequestBody Invoice invoice){
        Invoice updated = invoiceService.updateInvoice(invoice);
        return new ResponseEntity<>(updated,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable("id") Long id){
        invoiceService.deleteInvoice(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
