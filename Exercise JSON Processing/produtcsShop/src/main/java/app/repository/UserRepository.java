package app.repository;

import app.dto.Query4Dto;
import app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    @Query("" +
            "SELECT u FROM User AS u " +
            "JOIN u.products AS p ON p.seller.id = u.id " +
            "WHERE p.buyer IS NOT NULL " +
            "GROUP BY u.id " +
            "ORDER BY u.lastName, u.firstName")
    List<User> successfullySoldProducts();

    @Query(value = "SELECT u from app.entity.User as u join u.products as p on p.seller.id = u.id where p.buyer is not null group by u.id order by COUNT(p.buyer.id) desc")
    List<User> query4();
}
