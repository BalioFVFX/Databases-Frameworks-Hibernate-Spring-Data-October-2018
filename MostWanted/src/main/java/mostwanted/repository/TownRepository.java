package mostwanted.repository;

import mostwanted.domain.entities.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TownRepository extends JpaRepository<Town, Integer> {
    Optional<Town> findByName(String name);
    @Query(value = "select t from mostwanted.domain.entities.Town as t where size(t.racers) > 0 order by size(t.racers) desc, t.name asc")
    List<Town> racingTowns();
}
