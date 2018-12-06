package app.repository;

import app.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from app.domain.entity.User as u join fetch u.contests as c where c.id = :contestId and u.id = :uid")
    User inContest(@Param(value = "contestId") Long contestId, @Param(value = "uid") Long userId);
}
