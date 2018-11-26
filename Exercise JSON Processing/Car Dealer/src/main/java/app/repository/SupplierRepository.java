package app.repository;

import app.domain.entity.PartSupplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupplierRepository extends JpaRepository<PartSupplier, Long> {
    @Query("select ps from app.domain.entity.PartSupplier as ps where ps.importer = 0")
    List<PartSupplier> query3();
}
