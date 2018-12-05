package mostwanted.repository;

import mostwanted.domain.entities.RaceEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RaceEntryRepository extends JpaRepository<RaceEntry, Integer> {

}
