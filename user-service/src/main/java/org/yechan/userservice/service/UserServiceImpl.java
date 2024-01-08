package org.yechan.userservice.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yechan.userservice.dto.UserDto;
import org.yechan.userservice.jpa.UserEntity;
import org.yechan.userservice.jpa.UserRepository;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;
    
    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        
        ModelMapper mapper=new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity=mapper.map(userDto,UserEntity.class);
        userEntity.setEncryptedPwd("encrypted_password");
        
        userRepository.save(userEntity);
        
        UserDto returnUserDto=mapper.map(userEntity, UserDto.class);
        
        return returnUserDto;
    }
}
