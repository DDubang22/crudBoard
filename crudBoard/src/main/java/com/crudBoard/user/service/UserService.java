package com.crudBoard.user.service;

import com.crudBoard.user.domain.User;
import com.crudBoard.user.dto.UserDto;
import com.crudBoard.user.dto.UserUpdateDto;
import com.crudBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public void join(UserDto userDto) {
        userDto.setCreatedTime(LocalDateTime.now());
        User user = convertToEntity(userDto);
        repository.join(user);
    }

    public void updateUser(UserUpdateDto userDto) {
        repository.updateUser(userDto);
    }

    private User convertToEntity(UserDto dto) {
        return new User(dto);
    }
}


