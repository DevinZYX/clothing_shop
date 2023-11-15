package com.cs4125.clothing_shop.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cs4125.clothing_shop.Model.AuthenticationToken;
import com.cs4125.clothing_shop.Model.User.User;

public class TokenRepo {
    @Repository
public interface TokenRepository extends JpaRepository<AuthenticationToken, Integer> {
    AuthenticationToken findTokenByUser(User user);
    AuthenticationToken findTokenByToken(String token);
}
}
