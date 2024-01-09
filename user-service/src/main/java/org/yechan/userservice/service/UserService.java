package org.yechan.userservice.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.yechan.userservice.dto.UserDto;
import org.yechan.userservice.jpa.UserEntity;

public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto userDto);
    UserDto getUserByUserId(String userId);
    Iterable<UserEntity> getUserByAll();
    
    UserDto getUserDetailsByEmail(String email);
}
