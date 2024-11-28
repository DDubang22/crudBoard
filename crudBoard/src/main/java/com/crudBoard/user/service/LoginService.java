package com.crudBoard.user.service;

import com.crudBoard.user.domain.User;
import com.crudBoard.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository repository;

    public User login(String userId, String userPwd) {
        User findUser = repository.getPwd(userId);
        if (findUser != null && userPwd != null && userPwd.equals(findUser.getUserPwd())) {
            return findUser;
        } else {
            return null;
        }
    }
}
