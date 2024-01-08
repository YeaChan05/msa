package org.yechan.userservice.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.yechan.userservice.dto.UserDto;
import org.yechan.userservice.service.UserService;
import org.yechan.userservice.vo.Greeting;
import org.yechan.userservice.vo.RequestUser;
import org.yechan.userservice.vo.ResponseUser;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private Environment env;
    
    private UserService userService;
    
    @Autowired
    private Greeting greeting;
    
    public UserController(Environment env,UserService userService) {
        this.env = env;
        this.userService=userService;
    }
    
    @GetMapping("/user-service/health_check")
    public String status() {
        return String.format("It's Working in User Service on PORT %s",
                env.getProperty("local.server.port"));
    }
    
    @GetMapping("/welcome")
    public String welcome() {
//        return env.getProperty("greeting.message");
        return greeting.getMessage();
    }
    
    @PostMapping("/users")
    public ResponseEntity createUser(@RequestBody RequestUser user){
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        
        UserDto userDto=mapper.map(user,UserDto.class);
        userService.createUser(userDto);
        
        ResponseUser responseUser=mapper.map(userDto, ResponseUser.class);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(responseUser);
    }
}
