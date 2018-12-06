package app.repository;

import app.domain.entity.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Long> {
    @Query(value = "SELECT * FROM submissions where contestant_id = :uid and problem_id = :pid order by id desc limit 1", nativeQuery = true)
    Optional<Submission> getMaxUserProblem(@Param(value = "uid") Long uId, @Param(value = "pid") Long problemId);

    @Query(value = "SELECT * FROM submissions as s join problems as p on p.contest_id = :cid where s.contestant_id = :uid and p.contest_id = :cid group by :cid", nativeQuery = true)
    List<Submission> contestMaxPonints(@Param(value = "uid") Long uid, @Param(value = "cid") Long pid);
}
