package com.cs4125.clothing_shop.Service;

import com.cs4125.clothing_shop.Repository.CartRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    @Autowired
    CartRepo cartRepository;
}
