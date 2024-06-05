package org.example;

public class Main {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java org.example.Main <fully-qualified-class-name> <classpath>");
            return;
        }

        String className = args[0];
        String classpath = args[1];

        try {
            Class<?> clazz = ClassLoaderUtil.loadClass(className, classpath);
            ClassAnalyzer.analyzeClass(clazz);
            TestExecutor.executeTests(clazz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
