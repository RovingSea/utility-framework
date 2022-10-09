package io.github.rovingsea.utilityframework.core.validator;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Haixin Wu
 * @since 1.0.0
 */
public class AppTest {

    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Map<String, Method> map = new ConcurrentHashMap<>();
        for (Method declaredMethod : XxxValidator.class.getDeclaredMethods()) {
            map.put(declaredMethod.getName(), declaredMethod);
        }
        Method print = map.get("print");
        print.invoke(new XxxValidator(), "wu", null);
    }

}
