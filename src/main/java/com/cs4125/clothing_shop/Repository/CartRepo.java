package com.cs4125.clothing_shop.Repository;

import com.cs4125.clothing_shop.Model.Cart;
import com.cs4125.clothing_shop.Model.Product;
import com.cs4125.clothing_shop.Model.User.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepo extends JpaRepository<Cart, Integer> {
    List<Cart> findAllByUserOrderByCreatedDateDesc(User user);
    void deleteByUser(User user);

    List<Cart> findByProductId(int id);
}
