package com.cs4125.clothing_shop.Repository;

import com.cs4125.clothing_shop.Model.Order;
import com.cs4125.clothing_shop.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserOrderByCreatedDateDesc(User user);

}