package org.example;

import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ClassLoaderUtil {

    public static Class<?> loadClass(String className, String classpath) throws Exception {
        Path path = Paths.get(classpath).toAbsolutePath();
        URL[] urls = { path.toUri().toURL() };
        try (URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls)) {
            return urlClassLoader.loadClass(className);
        }
    }
}
