package io.github.rovingsea.utilityframework.core.validator;

import org.springframework.util.StringUtils;

/**
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class XxxValidator {

    public void print(String name) {
        if (StringUtils.isEmpty(name)) {
            System.out.println("name cannot be empty");
        }
    }

    public void print(String name, String age) {
        if (StringUtils.isEmpty(name)) {
            System.out.println("name cannot be empty");
        }
        if (StringUtils.isEmpty(age)) {
            System.out.println("age cannot be empty");
        }
    }

}
