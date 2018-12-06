package app.repository;

import app.domain.entity.UserMaximumResultsForProblem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMaximumResultForProblemRepository extends JpaRepository<UserMaximumResultsForProblem, Long> {
    @Query(value = "select * from max_results_for_problems where contestant_id = :uid and problem_id = :pid", nativeQuery = true)
    Optional<UserMaximumResultsForProblem> bestProblemSolution(@Param(value = "uid") Long uid, @Param(value = "pid") Long pid);
}
