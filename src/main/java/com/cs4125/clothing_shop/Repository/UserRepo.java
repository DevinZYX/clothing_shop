package com.cs4125.clothing_shop.Repository;


import com.cs4125.clothing_shop.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.stereotype.Repository; //need to set up sql database later to make this framework work


=======
import com.cs4125.clothing_shop.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
>>>>>>> origin/main
public interface UserRepo extends JpaRepository<User, Integer> {

}
