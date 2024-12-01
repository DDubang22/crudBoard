package com.crudBoard.board.repository;

import com.crudBoard.board.domain.Board;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BoardRepository {

    void boardPost(Board board);
    List<Board> boardList();
    List<Board> pagingBoard(int pageSize, @Param("offset") int offset);
    int countBoard();
}
