package com.shop.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.user.dto.UserEditDto;
import com.shop.user.dto.UserRegistrationDto;
import com.shop.user.entity.Users;
import com.shop.user.service.UserService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("users", new UserRegistrationDto());
        return "/join_form";
    }

    @PostMapping("/join")
    public String registerUser(@Valid @ModelAttribute("users") UserRegistrationDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "join_form";
        }

        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            bindingResult.rejectValue("confirmPassword", "error.user", "비밀번호가 일치하지 않습니다.");
            return "join_form";
        }

        if (userService.isUsernameTaken(userDto.getUsername())) {
            bindingResult.rejectValue("username", "error.user", "이미 사용 중인 아이디입니다.");
            return "join_form";
        }

        try {
            userService.registerUser(userDto);
        } catch (Exception e) {
            bindingResult.rejectValue("username", "error.user", "계정을 등록하는 동안 오류가 발생했습니다.");
            return "join_form";
        }

        return "redirect:/user/login";
    }

    

    @GetMapping("/mypage2")
    public String myPage() {
        return "mypage2";
    }

    @GetMapping("/cart")
    public String cart(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            Users user = userService.loadUserByUsername1(username);
            model.addAttribute("cart", user.getCart());
        }
        return "cart";
    }

    @GetMapping("/logout")
    public String logoutSuccess() {
        return "logout-success";
    }

    @GetMapping("/edit")
    public String editUser(Model model, Authentication authentication) {
        if (authentication != null) {
            String username = authentication.getName();
            Users user = userService.loadUserByUsername1(username);
            UserEditDto userEditDto = new UserEditDto();
            userEditDto.setName(user.getName());
            userEditDto.setTel(user.getTel());
            userEditDto.setEmail(user.getEmail());
            userEditDto.setAddr(user.getAddr());
            userEditDto.setDaddr(user.getDaddr());
            model.addAttribute("userEditDto", userEditDto);
        }
        return "edit_user";
    }

    @GetMapping("/password_check")
    public String passwordCheck() {
        return "password_check";
    }

    @PostMapping("/checkPassword")
    public String checkPassword(@ModelAttribute("password") String password, Authentication authentication, Model model) {
        if (authentication != null) {
            String username = authentication.getName();
            Users user = userService.loadUserByUsername1(username);
            if (passwordEncoder.matches(password, user.getPassword())) {
                model.addAttribute("userEditDto", new UserEditDto());
                return "redirect:/user/edit";
            } else {
                model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
                return "password_check";
            }
        }
        return "redirect:/user/login";
    }

    @PostMapping("/update")
    public String updateUser(@Valid UserEditDto userEditDto, BindingResult bindingResult, Authentication authentication, Model model) {
        if (bindingResult.hasErrors()) {
            return "edit_user";
        }

        if (authentication != null) {
            String username = authentication.getName();
            Users existingUser = userService.loadUserByUsername1(username);
            existingUser.setName(userEditDto.getName());
            existingUser.setTel(userEditDto.getTel());
            existingUser.setEmail(userEditDto.getEmail());
            existingUser.setAddr(userEditDto.getAddr());
            existingUser.setDaddr(userEditDto.getDaddr());
            userService.updateUser(existingUser);
        }

        return "redirect:/user/mypage2";
    }
}
