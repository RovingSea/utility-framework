package io.github.rovingsea.utilityframework.spring.web.sample.exception;

import io.github.rovingsea.utilityframework.spring.web.exception.ExceptionEnum;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.People;
import io.github.rovingsea.utilityframework.spring.web.sample.service.PeopleService;
import io.github.rovingsea.utilityframework.spring.web.utils.SpringBeanUtils;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public enum PeopleExceptionEnum implements ExceptionEnum {

    /**
     * Query {@link People} by id
     */
    QUERY_BY_ID(400001, "id cannot be less than 0") {
        @Override
        public void postProcessAfterThrow(Object opinion) {
            PeopleService peopleService = SpringBeanUtils.getBean(PeopleService.class);
            System.out.println("studentService.getStudentById(1) = " + peopleService.getStudentById(1));
        }
    },

    QUERY_BY_AGE(400002, "age cannot be less than 0 or more than 150");

    private final int code;

    private final String message;

    PeopleExceptionEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

}

