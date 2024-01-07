package org.yechan.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yechan.userservice.vo.Greeting;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private Environment env;
    
    @Autowired
    private Greeting greeting;
    
    public UserController(Environment env) {
        this.env = env;
    }
    
    @GetMapping("/health_check")
    public String status() {
        return "It's  Working in User Service";
    }
    
    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }
    
}
