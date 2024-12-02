package com.crudBoard.board.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class BoardFormUpdateDto {

    private BigInteger boardId;
    private String boardTitle;
    private String boardContent;
    private String boardAuthorId;
    private LocalDateTime boardUpdateDate;

}
