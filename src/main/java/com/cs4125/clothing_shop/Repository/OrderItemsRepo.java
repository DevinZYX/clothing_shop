package com.cs4125.clothing_shop.Repository;

import com.cs4125.clothing_shop.Model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepo extends JpaRepository<OrderItem,Integer> {
}

