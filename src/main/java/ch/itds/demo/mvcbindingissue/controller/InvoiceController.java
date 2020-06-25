package ch.itds.demo.mvcbindingissue.controller;

import ch.itds.demo.mvcbindingissue.domain.InvoiceItem;
import ch.itds.demo.mvcbindingissue.repo.InvoiceItemRepository;
import ch.itds.demo.mvcbindingissue.repo.InvoiceRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;

@Controller
public class InvoiceController {

    private final transient InvoiceRepository invoiceRepository;
    private final transient InvoiceItemRepository invoiceItemRepository;

    public InvoiceController(InvoiceRepository invoiceRepository, InvoiceItemRepository invoiceItemRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceItemRepository = invoiceItemRepository;
    }

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("invoiceList", invoiceRepository.findAll());
        return "index";
    }

    @RequestMapping("/invoice/show/{id}")
    public String show(@PathVariable Long id, Model model) {
        model.addAttribute("invoice", invoiceRepository.findById(id).orElseThrow(() -> new EntityNotFoundException()));
        return "show";
    }

    @GetMapping("/invoice/addItem")
    public String addItem(@ModelAttribute("invoiceItem") InvoiceItem invoiceItem) {
        return "addItem";
    }

    @PostMapping("/invoice/addItem")
    public String addItemSave(@Validated @ModelAttribute("invoiceItem") InvoiceItem invoiceItem, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "addItem";
        }
        invoiceItemRepository.save(invoiceItem);
        return "redirect:/invoice/show/" + invoiceItem.getInvoice().getId();
    }

}
