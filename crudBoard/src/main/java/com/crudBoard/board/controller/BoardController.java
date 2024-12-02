package com.crudBoard.board.controller;

import com.crudBoard.board.domain.Board;
import com.crudBoard.board.dto.BoardFormDto;
import com.crudBoard.board.dto.BoardFormUpdateDto;
import com.crudBoard.board.dto.PageDto;
import com.crudBoard.board.repository.BoardRepository;
import com.crudBoard.board.service.PagingService;
import com.crudBoard.board.service.PostService;
import com.crudBoard.user.domain.User;
import com.crudBoard.user.web.SessionConst;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final PostService postService;
    private final PagingService chkService;
    private final BoardRepository repository;

    @GetMapping("/board/boardList")
    public String list(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {

        PageDto pageDto = chkService.paging(page);

        model.addAttribute("pageDto", pageDto);
        return "board/boardList";
    }

    @GetMapping("/board/boardAdd")
    public String add(HttpSession session, Model model) {

        User chkSession = (User) session.getAttribute(SessionConst.LOGIN_USER);
        if (chkSession == null) {
            return "redirect:/user/login";
        }
        model.addAttribute("formDto", new BoardFormDto());
        return "board/boardAdd";
    }

    @PostMapping("/board/boardAdd")
    public String addForm(HttpSession session, @ModelAttribute("boardFormDto") BoardFormDto boardFormDto) {
        log.info("boardFormDto={}", boardFormDto);

        postService.post(session, boardFormDto);

        return "redirect:/";
    }

    @GetMapping("/board/boardList/{boardId}")
    public String boardDetail(@PathVariable("boardId") BigInteger boardId, Model model) {
        Board findBoard = chkService.detailBoard(boardId);
        model.addAttribute("findBoard", findBoard);
        return "board/boardDetail";
    }

    @GetMapping("/board/boardList/{boardId}/edit")
    public String boardEdit(@PathVariable("boardId") BigInteger boardId, BoardFormUpdateDto updateDto, Model model, HttpSession session) {
        User findUser = (User) session.getAttribute(SessionConst.LOGIN_USER);
        if (findUser == null) {
            return "redirect:/user/login";
        }

        Board findBoard = chkService.detailBoard(boardId);

        updateDto.setBoardId(findBoard.getBoardId());
        updateDto.setBoardTitle(findBoard.getBoardTitle());
        updateDto.setBoardContent(findBoard.getBoardContent());
        updateDto.setBoardUpdateDate(findBoard.getBoardUpdateDate());
        updateDto.setBoardAuthorId(findBoard.getBoardAuthorId());
        log.info("findBoardAuthorId={}", findBoard.getBoardAuthorId());

        model.addAttribute("updateDto", updateDto);
        return "board/boardEdit";
    }

    @PostMapping("/board/boardList/{boardId}/edit")
    public String boardUpdateEdit(@ModelAttribute("updateDto") BoardFormUpdateDto updateDto, @PathVariable("boardId") BigInteger boardId, HttpSession session) {

        User findUser = (User) session.getAttribute(SessionConst.LOGIN_USER);
        String findBoardAuthorId = chkService.detailBoard(boardId).getBoardAuthorId();
        String findUserId = findUser.getUserId();

        if (!findUserId.equals(findBoardAuthorId)) {
            return "redirect:/board/boardList";
        }

        postService.update(updateDto);

        return "redirect:/board/boardList";
    }

    @PostMapping("/board/boardList/{boardId}/delete")
    public String boardDelete(@ModelAttribute("updateDto") BoardFormUpdateDto updateDto, @PathVariable("boardId") BigInteger boardId, HttpSession session) {

        log.info("updateDto={}", updateDto);

        User findUser = (User) session.getAttribute(SessionConst.LOGIN_USER);
        String findUserId = findUser.getUserId();
        String findBoardAuthorId = chkService.detailBoard(boardId).getBoardAuthorId();

        if (!findUserId.equals(findBoardAuthorId)) {
            return "redirect:/board/boardList";
        }

        repository.deleteBoard(boardId, 1);

        return "redirect:/board/boardList";
    }
}
