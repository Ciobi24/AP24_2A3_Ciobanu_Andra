package org.example;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class TestExecutor {
    public static void executeTests(Class<?> clazz) throws Exception {
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {
            if (method.isAnnotationPresent(Test.class) && Modifier.isStatic(method.getModifiers()) && method.getParameterCount() == 0) {
                System.out.println("Invoking test method: " + method.getName());
                method.setAccessible(true);
                method.invoke(null);
            }
        }
    }
}
