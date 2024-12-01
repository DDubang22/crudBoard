package com.crudBoard.board.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardFormDto {

    private String boardTitle;
    private String boardContent;
    private String boardAuthorId;
    private LocalDateTime boardCreateTime;
    private LocalDateTime boardUpdateTime;
    private int boardLikeCount;
    private int boardView;
    private int boardDel;
}
