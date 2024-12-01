package com.crudBoard.board.controller;

import com.crudBoard.board.domain.Board;
import com.crudBoard.board.dto.BoardFormDto;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class BoardController {

    private final PostService postService;
    private final PagingService pageService;
    private final BoardRepository repository;

    @GetMapping("/board/boardList")
    public String list(@RequestParam(name = "page", defaultValue = "1") int page, Model model) {

        PageDto pageDto = pageService.paging(page);

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

}
