package app.repository;

import app.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Optional<Branch> findByName(String name);

    @Query(value = "SELECT b.name as name, t.name as town, IFNULL(SUM(p.clients), 0) as total_clients FROM branches AS b\n" +
            "LEFT JOIN products AS p\n" +
            "ON p.branch_id = b.id\n" +
            "LEFT JOIN towns as t\n" +
            "on t.id = b.town_id\n" +
            "GROUP BY b.id\n" +
            "ORDER BY total_Clients DESC", nativeQuery = true)
    List<Object[]> topBranches();
}
