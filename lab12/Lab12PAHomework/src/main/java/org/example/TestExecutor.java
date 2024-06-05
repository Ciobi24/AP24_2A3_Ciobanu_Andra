package org.example;

import java.lang.reflect.Method;

public class TestExecutor {
    public static void executeTests(Class<?> clazz) {
        int testsRun = 0;
        int testsFailed = 0;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                try {
                    method.invoke(clazz.getDeclaredConstructor().newInstance());
                    testsRun++;
                } catch (Exception e) {
                    e.printStackTrace();
                    testsFailed++;
                }
            }
        }

        System.out.println("Tests run: " + testsRun);
        System.out.println("Tests failed: " + testsFailed);
    }
}
