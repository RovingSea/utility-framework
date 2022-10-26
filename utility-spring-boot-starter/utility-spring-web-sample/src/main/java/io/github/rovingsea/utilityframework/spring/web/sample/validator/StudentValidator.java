package io.github.rovingsea.utilityframework.spring.web.sample.validator;

import io.github.rovingsea.utilityframework.spring.web.utils.Throw;
import io.github.rovingsea.utilityframework.spring.web.validator.ValidateMapping;
import io.github.rovingsea.utilityframework.spring.web.validator.Validator;
import io.github.rovingsea.utilityframework.spring.web.sample.exception.StudentExceptionEnum;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Validator("/student")
public class StudentValidator {

    @ValidateMapping("/queryStudentById")
    public void queryStudentById(int id) {
        if (id < 0) {
            Throw.badRequest(StudentExceptionEnum.QUERY_BY_ID);
        }
    }

    @ValidateMapping("/queryStudentsByAge")
    public void queryStudentsByAge(int age) {
        if (age < 0 || age > 150) {
            Throw.badRequest(StudentExceptionEnum.QUERY_BY_AGE);
        }
    }

}

