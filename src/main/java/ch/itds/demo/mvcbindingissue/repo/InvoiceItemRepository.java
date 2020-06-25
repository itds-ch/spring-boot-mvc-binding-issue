package ch.itds.demo.mvcbindingissue.repo;


import ch.itds.demo.mvcbindingissue.domain.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {
}
