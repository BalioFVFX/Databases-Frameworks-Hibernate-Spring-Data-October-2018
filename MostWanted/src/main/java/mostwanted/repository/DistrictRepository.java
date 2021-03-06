package mostwanted.repository;

import mostwanted.domain.entities.District;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DistrictRepository extends JpaRepository<District, Integer> {
    Optional<District> findByName(String name);
}
