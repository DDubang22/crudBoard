package com.crudBoard.user.dto;

import com.crudBoard.user.domain.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto {

    private String userId;
    private String userPwd;
    private String userNickname;
    private String userEmail;
    private LocalDate userBirthday;
    private LocalDateTime createdTime;

}
