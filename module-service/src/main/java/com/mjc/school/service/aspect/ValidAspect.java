package com.mjc.school.service.aspect;

import static com.mjc.school.service.constants.Constants.*;

import com.mjc.school.service.dto.impl.AuthorDtoRequest;
import com.mjc.school.service.dto.BaseDto;
import com.mjc.school.service.dto.impl.NewsDtoRequest;
import com.mjc.school.service.dto.impl.TagDtoRequest;
import com.mjc.school.service.validator.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ValidAspect {

    @Before("execution(* com.mjc.school.service.impl.*.update(..)) && args(dto)")
    private <T extends BaseDto<Long>> void validateId(JoinPoint joinPoint, T dto) {
        validateNumber(joinPoint, dto.getId());
    }

    @Before(value = "@annotation(com.mjc.school.service.annotation.NotEmpty)" +
            "&& args(id)")
    private void validateNumber(JoinPoint joinPoint, Long id) {
        String param = joinPoint.getSourceLocation().getWithinType()
                .getSimpleName().replace("Service", "");
        Validator.validateNumber(id, param);
    }

    @Before(value = "@annotation(com.mjc.school.service.annotation.Valid) " +
            "&& args(dto)")
    private <T extends BaseDto<Long>> void validateDto(T dto) {
        if(dto instanceof NewsDtoRequest request) {
            Validator.validateString(request.getTitle(), TITLE_PARAM, MIN_TITLE_LENGTH, MAX_TITLE_LENGTH);
            Validator.validateString(request.getContent(), CONTENT_PARAM, MIN_CONTENT_LENGTH, MAX_CONTENT_LENGTH);
        } else if(dto instanceof AuthorDtoRequest request) {
            Validator.validateString(request.getName(), NAME_PARAM, MIN_NAME_LENGTH, MAX_NAME_LENGTH);
        } else if(dto instanceof TagDtoRequest request) {
            Validator.validateString(request.getName(), NAME_PARAM, MIN_NAME_LENGTH, MIN_NAME_LENGTH);
        }
    }
}
