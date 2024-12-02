package com.crudBoard.board.repository;

import com.crudBoard.board.domain.Board;
import com.crudBoard.board.dto.BoardFormUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigInteger;
import java.util.List;

@Mapper
public interface BoardRepository {

    void postBoard(Board board);
    void updateBoard(BoardFormUpdateDto updateDto);
    Board detailBoard(BigInteger boardId);
    List<Board> pagingBoard(int pageSize, @Param("offset") int offset);
    int countBoard();
    void deleteBoard(@Param("boardId") BigInteger boardId, @Param("boardDel") int boardDel);
}
