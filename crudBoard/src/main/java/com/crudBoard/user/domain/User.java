package com.crudBoard.user.domain;

import com.crudBoard.user.dto.UserDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class User {

    private String userId;
    private String userPwd;
    private String userNickname;
    private String userEmail;
    private LocalDate userBirthday;
    private LocalDateTime userCreatedDate;

    public User(UserDto dto) {
        this.userId = dto.getUserId();
        this.userPwd = dto.getUserPwd();
        this.userNickname = dto.getUserNickname();
        this.userEmail = dto.getUserEmail();
        this.userBirthday = dto.getUserBirthday();
        this.userCreatedDate = dto.getCreatedTime();
    }
}
