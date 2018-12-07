package app.repository;

import app.entity.Town;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
    Optional<Town> findByName(String name);

    @Query(value = "SELECT t.name, t.population, IFNULL(SUM(p.clients), 0) as town_clients FROM towns as t\n" +
            "LEFT JOIN branches as b\n" +
            "on b.town_id = t.id\n" +
            "LEFT JOIN products as p\n" +
            "on p.branch_id = b.id\n" +
            "GROUP BY b.id\n" +
            "order by town_clients desc", nativeQuery = true)
    List<Object[]> towns();
}
