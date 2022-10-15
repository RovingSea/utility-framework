package io.github.rovingsea.utilityframework.sample.validator;

import io.github.rovingsea.utilityframework.core.Throw;
import io.github.rovingsea.utilityframework.core.validator.ValidateMapping;
import io.github.rovingsea.utilityframework.core.validator.Validator;
import io.github.rovingsea.utilityframework.sample.exception.StudentError;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
@Validator("/sample")
public class StudentValidator {

    @ValidateMapping("/queryStudentById")
    public void queryStudentById(int id) {
        if (id < 0) {
            Throw.badRequest(StudentError.QUERY_BY_ID);
        }
    }

    @ValidateMapping("/queryStudentsByAge")
    public void queryStudentsByAge(int age) {
        if (age < 0 || age > 150) {
            Throw.badRequest(StudentError.QUERY_BY_AGE);
        }
    }

}

