package app.repository;

import app.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = "select u from app.domain.entities.User as u join u.products as p group by u.id having COUNT(p.buyer) > 0 order by u.lastName asc, u.firstName asc")
    List<User> successfullySoldProductsQuery2();

    @Query(value = "select u from app.domain.entities.User as u join u.products as p group by u.id having COUNT(p.buyer) > 0")
    List<User> UserAndProductsQuery4();
}
