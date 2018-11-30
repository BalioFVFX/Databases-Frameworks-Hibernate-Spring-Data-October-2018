package app.repository;

import app.domain.entity.PartSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<PartSupplier, Long> {
    List<PartSupplier> findAllByImporterFalse();
}
