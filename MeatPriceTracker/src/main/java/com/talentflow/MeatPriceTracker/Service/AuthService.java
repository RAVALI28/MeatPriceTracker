package com.talentflow.MeatPriceTracker.Service;

import com.talentflow.MeatPriceTracker.Entity.User;
import com.talentflow.MeatPriceTracker.Repository.UserRepo;
import com.talentflow.MeatPriceTracker.Utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class AuthService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private JwtUtils jwtUtils;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(String email, String password){
        Optional<User> optionalUser = userRepo.findByEmail(email);

        if(optionalUser.isPresent()){
            User user = optionalUser.get();
            if(passwordEncoder.matches(password, user.getPassword())){  //In real world, you would hash+verify using Bcrypt
                return jwtUtils.generateJwtToken(user);
            }else {
                throw new RuntimeException("Invalid Password");
            }
        }
        else {
            throw new RuntimeException("User not found!");
        }
    }
}
