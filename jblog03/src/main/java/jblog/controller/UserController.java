package jblog.controller;

import jakarta.validation.Valid;
import jblog.service.BlogService;
import jblog.service.UserService;
import jblog.valid.CheckIdValidator;
import jblog.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user")
public class UserController {

    private static UserService userService;

    private CheckIdValidator checkIdValidator;

    @InitBinder
    public void validatorBinder(WebDataBinder binder) {
        binder.addValidators(checkIdValidator);
    }

    public UserController(UserService userService, CheckIdValidator checkIdValidator) {
        this.userService = userService;
        this.checkIdValidator = checkIdValidator;
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }


    @GetMapping("/join")
    public String join(@ModelAttribute("user") UserVo user) {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute("user") @Valid UserVo user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAllAttributes(result.getModel());
            return "user/join";
        }
        userService.join(user);

        return "redirect:/user/joinsuccess";
    }

    @GetMapping("/joinsuccess")
    public String joinSuccess() {
        return "user/joinsuccess";
    }
}
