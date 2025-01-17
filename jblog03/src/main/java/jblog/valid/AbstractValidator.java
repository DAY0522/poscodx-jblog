package jblog.valid;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public abstract class AbstractValidator<T> implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return true;  // 일반적으로는 실제 DTO 타입에 대한 검사를 해야 하지만, 여기서는 간단하게 true로 두었습니다.
    }

    @Override
    public void validate(Object target, Errors errors) {
        try {
            doValidate((T) target, errors);  // 수정: 'Errors errors'를 올바르게 전달
        } catch (RuntimeException e) {
            System.out.println("중복 검증 에러: " + e);
            throw e;
        }
    }

    protected abstract void doValidate(final T dto, final Errors errors);
}
