# Spring Boot 2.3.1/2.2.8 MVC Binding Issue Example

The code in this repository worked with Spring Boot 2.3.0/2.2.7, but no longer does with 2.3.1/2.2.8.

Therefore, the tests in `MvcBindingIssueApplicationTests` fail with 2.3.1 and 2.2.8 but work with 2.3.0 and 2.2.7.

## Details

Calling `/invoice/addItem?invoice=1` did bind the invoice with the given id to `InvoiceItem.invoice` before. Now, an error occurs:
 
```
2020-06-25 11:50:19.824  WARN 98947 --- [    Test worker] .w.s.m.s.DefaultHandlerExceptionResolver : Resolved [org.springframework.validation.BindException: org.springframework.validation.BeanPropertyBindingResult: 1 errors
Field error in object 'invoiceItem' on field 'invoice': rejected value [1]; codes [typeMismatch.invoiceItem.invoice,typeMismatch.invoice,typeMismatch.ch.itds.demo.mvcbindingissue.domain.Invoice,typeMismatch]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [invoiceItem.invoice,invoice]; arguments []; default message [invoice]]; default message [Failed to convert property value of type 'java.lang.String' to required type 'ch.itds.demo.mvcbindingissue.domain.Invoice' for property 'invoice'; nested exception is java.lang.IllegalStateException: Cannot convert value of type 'java.lang.String' to required type 'ch.itds.demo.mvcbindingissue.domain.Invoice' for property 'invoice': no matching editors or conversion strategy found]]
```

Extract from the controller:

```java
@GetMapping("/invoice/addItem")
public String addItem(@ModelAttribute("invoiceItem") InvoiceItem invoiceItem) {
    return "addItem";
}
```