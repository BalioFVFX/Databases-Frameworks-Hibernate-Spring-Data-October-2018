package alararestaurant.repository;

import alararestaurant.domain.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    //@Query(value = "select e from alararestaurant.domain.entities.Employee as e join e.orders as o left join o.orderItems as oi left join oi.item as i left join e.position as p where p.name = 'Burger Flipper' group by o.id order by e.name asc, o.id asc")
    @Query(value = "select o from alararestaurant.domain.entities.Order as o join o.employee as e join o.orderItems as oi join oi.item as i join e.position as p where p.name = 'Burger Flipper' group by o.id order by e.name asc, o.id asc")
    List<Order> query2();
}
