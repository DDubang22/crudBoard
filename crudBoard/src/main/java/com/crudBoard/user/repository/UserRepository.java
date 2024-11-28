package com.crudBoard.user.repository;

import com.crudBoard.user.domain.User;
import com.crudBoard.user.dto.UserUpdateDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserRepository {
    void join(User user);
    User getPwd(String id);
    void updateUser(UserUpdateDto user);
}
