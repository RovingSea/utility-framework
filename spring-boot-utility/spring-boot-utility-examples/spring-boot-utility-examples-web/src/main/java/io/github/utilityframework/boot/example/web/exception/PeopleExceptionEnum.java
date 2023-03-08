package io.github.utilityframework.boot.example.web.exception;

import io.github.utilityframework.boot.example.web.entity.People;
import io.github.utilityframework.boot.web.exception.ExceptionEnum;

/**
 * @author Haixin Wu
 * @since 1.0.0
 */
public enum PeopleExceptionEnum implements ExceptionEnum {

    /**
     * Query {@link People} by id
     */
    QUERY_BY_ID(400001, "id cannot be less than 0"),

    QUERY_BY_AGE(400002, "age cannot be less than 0 or more than 150"),

    COOK_INGREDIENT_BAD(400003, "the given ingredient is bad"),

    COOK_INGREDIENT_OUT_OF_DATE(400004, "the given ingredient is out of date"),

    COOK_TIME_IS_TOO_LONG(400005, "the given cook time is too long") {
        @Override
        public void postProcessAfterThrow(Object opinion) {
            int duration = (int) opinion;
            System.out.println("煮了" + duration + "分钟，都快烧了，喂猫吧！");
        }
    },

    COOK_TIME_IS_TOO_SHORT(400006, "the given cook time is too short") {
        @Override
        public void postProcessAfterThrow(Object opinion) {
            int duration = (int) opinion;
            System.out.println("才煮了" + duration + "分钟，熟都没熟，喂狗吧！");
        }
    };

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

