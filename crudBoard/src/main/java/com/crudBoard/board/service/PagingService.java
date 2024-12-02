package com.crudBoard.board.service;

import com.crudBoard.board.domain.Board;
import com.crudBoard.board.dto.PageDto;
import com.crudBoard.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PagingService {

    public final BoardRepository repository;

    public PageDto paging(int page) {
        int pageSize = 10;
        int offset = (page - 1) * pageSize;

        List<Board> boards = repository.pagingBoard(pageSize, offset);
        int boardCount = repository.countBoard();

        return new PageDto(boards, boardCount, page, pageSize);
    }

    public Board detailBoard(BigInteger boardId) {
        return repository.detailBoard(boardId);
    }

}
