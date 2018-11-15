package app.repositories;

import app.entities.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    List<User> findAllByEmailEndingWith(String provider);

    @Transactional
    @Modifying
    @Query("update User u set u.deleted = 1 where u.lastTimeLoggedIn <= ?1")
    void markAllIsDeleted(Date date);

    @Transactional
    @Modifying
    @Query("delete FROM User u where u.deleted = 1")
    int removeInactiveUsers();

}
