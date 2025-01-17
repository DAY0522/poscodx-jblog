package jblog.controller.api;

import jblog.dto.JsonResult;
import jblog.service.UserService;
import jblog.vo.UserVo;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController("userApiController")
@RequestMapping("/api/user")
public class UserApiController {
    private UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/checkemail/{id}")
    public JsonResult checkEmail(@PathVariable("id") String id) {
        UserVo userVo = userService.getUser(id);
        System.out.printf("id: " +id);
        System.out.println("userVo: " + userVo);
        return JsonResult.success(Map.of("exist", userVo != null));
    }
}
