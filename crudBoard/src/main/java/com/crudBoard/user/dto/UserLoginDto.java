package com.crudBoard.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@ToString
public class UserLoginDto {

    @NotEmpty
    private String userId;
    @NotEmpty
    private String userPwd;

}
