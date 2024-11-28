package com.crudBoard.user.controller;

import com.crudBoard.user.domain.User;
import com.crudBoard.user.dto.UserDto;
import com.crudBoard.user.dto.UserLoginDto;
import com.crudBoard.user.dto.UserUpdateDto;
import com.crudBoard.user.repository.UserRepository;
import com.crudBoard.user.service.LoginService;
import com.crudBoard.user.service.UserService;
import com.crudBoard.user.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserRepository repository;
    private final LoginService loginService;
    private final UserService userService;

    //회원가입 호출
    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("user", new UserDto());
        return "user/join";
    }

    //회원가입 생성
    @PostMapping("/join")
    public String join(@ModelAttribute("userForm") UserDto userForm, Model model) {
        log.info("userDTO={}", userForm);

        userService.join(userForm);
        model.addAttribute("user", userForm);
        return "redirect:/user/login";
    }

    //로그인 호출
    @GetMapping("/login")
    public String loginForm(@ModelAttribute("userLoginDto") UserLoginDto userLoginDto) {
        return "user/login";
    }

    //로그인 생성
    @PostMapping("/login")
    public String login(@Validated @ModelAttribute("userLoginDto") UserLoginDto userLoginDto, BindingResult result, HttpServletRequest request) {
        if (result.hasErrors()) {
            return "user/login";
        }
        log.info("UserLoginDto={}", userLoginDto);
        User loginUser = loginService.login(userLoginDto.getUserId(), userLoginDto.getUserPwd());

        if (loginUser == null) {
            result.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "user/login";
        }

        HttpSession session = request.getSession();
        //세션에 회원정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        return "redirect:/";
    }

    //로그아웃
    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

    //회원정보 호출
    @GetMapping("/userDetail")
    public String update(HttpSession session, Model model) {
        User findUser =  (User) session.getAttribute("loginUser");
        UserUpdateDto userUpdateDto = new UserUpdateDto();
        userUpdateDto.setUserId(findUser.getUserId());
        userUpdateDto.setUserNickname(findUser.getUserNickname());
        userUpdateDto.setUserBirthday(findUser.getUserBirthday());
        userUpdateDto.setUserEmail(findUser.getUserEmail());

        model.addAttribute("userUpdateForm", userUpdateDto);
        return "user/userDetail";
    }

    //회원정보 생성
    @PostMapping("/userDetail")
    public String update(@ModelAttribute("userUpdateForm") UserUpdateDto userUpdateForm,BindingResult result, Model model) {
        log.info("userUpdateForm={}",userUpdateForm);

        String findPwd = repository.getPwd(userUpdateForm.getUserId()).getUserPwd();

        if (!findPwd.equals(userUpdateForm.getUserPwd())) {
            result.reject("updateFail","기존 비밀번호가 틀렸습니다.");
            return "user/userDetail";
        }

        if (userUpdateForm.getUserPwd().equals(userUpdateForm.getUserRePwd())) {
            result.reject("eqPWD","기존 비밀번호와 새로운 비밀번호가 같습니다");
            return "user/userDetail";
        }

        userService.updateUser(userUpdateForm);

        return "redirect:/";
    }
}
