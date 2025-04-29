package com.talentflow.MeatPriceTracker.Controller;

import com.talentflow.MeatPriceTracker.Entity.User;
import com.talentflow.MeatPriceTracker.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String,String> loginRequest){
        String email =  loginRequest.get("email");
        String password = loginRequest.get("password");

        String token = authService.login(email, password);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        return response;


    }
}
