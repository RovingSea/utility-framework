package io.github.utilityframework.boot.web.exception;

/**
 * <p>
 * Provide the handling method after the exception is thrown,
 * the default is to do nothing.It will be used with {@link ExceptionEnum}
 * to override this method in the implemented enums.
 * </p>
 * For Example:
 * <pre>{@code
 *     public enum PeopleExceptionEnum implements ExceptionEnum {
 *
 *          QUERY_BY_ID(400001, "id cannot be less than 0") {
 *              @Override
 *              public void postProcessAfterThrow(Object opinion) {
 *                   // todo
 *              }
 *           };
 *
 *          private final int code;
 *
 *          private final String message;
 *
 *          PeopleExceptionEnum(int code, String message) {
 *              this.code = code;
 *              this.message = message;
 *          }
 *
 *          @Override
 *          public int getCode() {
 *              return this.code;
 *          }
 *
 *          @Override
 *          public String getMessage() {
 *              return this.message;
 *          }
 *
 *      }
 * }</pre>
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public interface ExceptionPostProcessor {

    /**
     * A method belonging to an enumeration class,
     * when an exception is thrown with this enum, it will execute this method.
     * <p>
     * For example, I found meat I didn't like, but now I have two pets.
     * The opinion is to decide which pet to eat.
     * </p>
     *
     * @param opinion the opinion of context passing when an exception is thrown.
     */
    default void postProcessAfterThrow(Object opinion) {

    }
}
