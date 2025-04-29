package com.talentflow.MeatPriceTracker.Repository;

import com.talentflow.MeatPriceTracker.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {


    Optional<User> findByEmail(String email);
}
