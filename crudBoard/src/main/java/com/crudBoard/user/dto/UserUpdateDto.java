package com.crudBoard.user.dto;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserUpdateDto {

    private String userId;
    private String userPwd;
    private String userRePwd;
    private String userNickname;
    private String userEmail;
    private LocalDate userBirthday;

}
