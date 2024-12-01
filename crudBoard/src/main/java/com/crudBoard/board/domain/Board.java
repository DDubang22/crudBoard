package com.crudBoard.board.domain;

import com.crudBoard.board.dto.BoardFormDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigInteger;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@ToString
public class Board {

    private BigInteger boardId;
    private String boardTitle;
    private String boardAuthorId;
    private String boardContent;
    private LocalDateTime boardCreateDate;
    private LocalDateTime boardUpdateDate;
    private int boardLikeCount;
    private int boardView;
    private int boardDel;

    public Board(BoardFormDto dto) {
        this.boardId = null;
        this.boardTitle = dto.getBoardTitle();
        this.boardAuthorId = dto.getBoardAuthorId();
        this.boardContent = dto.getBoardContent();
        this.boardCreateDate = dto.getBoardCreateTime();
        this.boardUpdateDate = dto.getBoardUpdateTime();
        this.boardLikeCount = dto.getBoardLikeCount();
        this.boardView = dto.getBoardView();
        this.boardDel = dto.getBoardDel();
    }
}
