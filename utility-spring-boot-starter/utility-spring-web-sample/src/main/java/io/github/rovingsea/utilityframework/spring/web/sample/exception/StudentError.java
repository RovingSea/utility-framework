package io.github.rovingsea.utilityframework.spring.web.sample.exception;

import io.github.rovingsea.utilityframework.spring.web.exception.BaseEnum;
import io.github.rovingsea.utilityframework.spring.web.sample.entity.Student;
import io.github.rovingsea.utilityframework.spring.web.sample.service.StudentService;
import io.github.rovingsea.utilityframework.spring.web.utils.SpringBeanUtils;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public enum StudentError implements BaseEnum {

    /**
     * Query {@link Student} by id
     */
    QUERY_BY_ID(400001, "id cannot be less than 0") {
        @Override
        public void postProcessAfterThrow() {
            StudentService studentService = SpringBeanUtils.getBean(StudentService.class);
            System.out.println("studentService.getStudentById(1) = " + studentService.getStudentById(1));
        }
    },

    QUERY_BY_AGE(400002, "age cannot be less than 0 or more than 150");

    private final int code;

    private final String message;

    StudentError(int code, String message) {
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

