package com.crudBoard.board.dto;

import com.crudBoard.board.domain.Board;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PageDto {

    private List<Board> content;
    private int totalCount;
    private int currentPage;
    private int totalPage;

    public PageDto(List<Board> content, int totalCount, int currentPage, int pageSize) {
        this.content = content;
        this.totalCount = totalCount;
        this.currentPage = currentPage;
        this.totalPage = (int) Math.ceil((double) totalCount / pageSize);
    }
}
