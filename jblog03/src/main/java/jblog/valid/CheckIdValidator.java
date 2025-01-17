package jblog.valid;

import jblog.repository.UserRepository;
import jblog.vo.UserVo;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class CheckIdValidator extends AbstractValidator<UserVo> {

    private final UserRepository userRepository;

    public CheckIdValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void doValidate(UserVo vo, Errors errors) {
        if (userRepository.findById(vo.getId()) != null) {
            System.out.println("중복 확인 중");
            errors.rejectValue("id", "아이디 중복 오류", "이미 사용 중인 아이디입니다.");
        }
    }
}
