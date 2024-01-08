package org.yechan.userservice.service;

import org.yechan.userservice.dto.UserDto;

public interface UserService {
    UserDto createUser(UserDto userDto);
}
