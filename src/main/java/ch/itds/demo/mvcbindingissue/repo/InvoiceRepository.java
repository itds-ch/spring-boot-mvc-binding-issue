package ch.itds.demo.mvcbindingissue.repo;


import ch.itds.demo.mvcbindingissue.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
