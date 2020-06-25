package ch.itds.demo.mvcbindingissue.domain;

import javax.persistence.*;

@Entity
public class InvoiceItem {

    @Id
    @GeneratedValue
    Long id;

    String name;

    @ManyToOne(optional = false)
    @JoinColumn(nullable = false)
    Invoice invoice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
