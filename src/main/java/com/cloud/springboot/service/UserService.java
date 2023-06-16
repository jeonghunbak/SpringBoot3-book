package com.cloud.springboot.service;

import com.cloud.springboot.domain.User;
import com.cloud.springboot.dto.AddUserRequest;
import com.cloud.springboot.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public Long save(AddUserRequest dto){
        return userRepository.save(User.builder()
                .email(dto.getEmail()).passwd(bCryptPasswordEncoder.encode(dto.getPasswd()))
                .build()).getId();
    }
}
