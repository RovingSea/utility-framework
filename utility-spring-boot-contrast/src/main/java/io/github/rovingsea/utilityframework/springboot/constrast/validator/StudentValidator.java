package io.github.rovingsea.utilityframework.springboot.constrast.validator;

import io.github.rovingsea.utilityframework.springboot.constrast.exception.BusinessEnum;
import io.github.rovingsea.utilityframework.springboot.constrast.exception.ParamException;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public class StudentValidator {

    public static void queryStudentById(int id) {
        if (id < 0) {
            throw new ParamException(BusinessEnum.STUDENT_QUERY_BY_ID);
        }
    }

    public static void queryStudentsByAge(int age) {
        if (age < 0 || age > 150) {
            throw new ParamException(BusinessEnum.STUDENT_QUERY_BY_AGE);
        }
    }

}

