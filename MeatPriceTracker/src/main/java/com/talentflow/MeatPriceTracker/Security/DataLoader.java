package com.talentflow.MeatPriceTracker.Security;

import com.talentflow.MeatPriceTracker.Entity.User;
import com.talentflow.MeatPriceTracker.Repository.UserRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDatabase(UserRepo userRepo){

        return args -> {
            if(userRepo.count() ==0){
                BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

                User admin = new User();
                admin.setName("Admin User");
                admin.setEmail("admin@example.com");
                admin.setPassword(passwordEncoder.encode("admin"));
                admin.setRole("ADMIN");


                User manager = new User();
                manager.setName("Manager User");
                manager.setEmail("manager@example.com");
                manager.setPassword(passwordEncoder.encode("manager"));
                manager.setRole("MANAGER");

                userRepo.save(admin);
                userRepo.save(manager);

                System.out.println("Sample Users created with encrypted passwords");
            }
        };
    }
}
