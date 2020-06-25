package ch.itds.demo.mvcbindingissue;

import ch.itds.demo.mvcbindingissue.domain.Invoice;
import ch.itds.demo.mvcbindingissue.repo.InvoiceItemRepository;
import ch.itds.demo.mvcbindingissue.repo.InvoiceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MvcBindingIssueApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private InvoiceItemRepository invoiceItemRepository;

	@Test
	void invoiceAddItemForm() throws Exception {
		Invoice invoice = invoiceRepository.findAll().get(0);

		mockMvc.perform(get("/invoice/addItem")
				.param("invoice", String.valueOf(invoice.getId())))
				.andExpect(status().isOk());

	}

	@Test
	void invoiceAddItemSave() throws Exception {
		Invoice invoice = invoiceRepository.findAll().get(0);

		mockMvc.perform(post("/invoice/addItem")
				.param("invoice", String.valueOf(invoice.getId()))
				.param("name", "Example Item"))
				.andExpect(status().is3xxRedirection());

		assertThat(invoiceItemRepository.findAll().get(0).getName()).isEqualTo("Example Item");

	}


}
