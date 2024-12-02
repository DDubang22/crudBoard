package com.crudBoard.board.service;

import com.crudBoard.board.domain.Board;
import com.crudBoard.board.dto.BoardFormDto;
import com.crudBoard.board.dto.BoardFormUpdateDto;
import com.crudBoard.board.repository.BoardRepository;
import com.crudBoard.user.domain.User;
import com.crudBoard.user.web.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Slf4j
@Service
public class PostService {

    private final BoardRepository repository;

    public void post(HttpSession session, BoardFormDto boardFormDto) {
        User findUser = (User) session.getAttribute(SessionConst.LOGIN_USER);
        boardFormDto.setBoardAuthorId(findUser.getUserId());
        boardFormDto.setBoardCreateTime(LocalDateTime.now());
        boardFormDto.setBoardUpdateTime(LocalDateTime.now());
        boardFormDto.setBoardLikeCount(0);
        boardFormDto.setBoardView(0);
        boardFormDto.setBoardDel(0);

        log.info("[service] boardFormDto={}", boardFormDto);

        Board board = convertToEntity(boardFormDto);

        repository.postBoard(board);
    }

    public void update(BoardFormUpdateDto updateDto) {
        updateDto.setBoardUpdateDate(LocalDateTime.now());
        repository.updateBoard(updateDto);
    }

    private Board convertToEntity(BoardFormDto dto) {
        return new Board(dto);
    }
}
