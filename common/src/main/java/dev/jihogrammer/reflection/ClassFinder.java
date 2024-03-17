package dev.jihogrammer.reflection;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static java.util.Objects.requireNonNull;

public final class ClassFinder {

    private static final String CLASS_EXTENSION = ".class";

    private static final char CLASS_DELIMITER = '.';

    public static Collection<Class<?>> findClasses(final String packageName) throws IOException {
        var path = packageName.replace('.', '/');
        var directories = new ArrayList<File>();

        Thread.currentThread().getContextClassLoader().getResources(path).asIterator()
                .forEachRemaining(resource -> directories.add(new File(resource.getFile())));

        return directories.stream()
                .map(directory -> findClasses(directory, packageName))
                .flatMap(Collection::stream)
                .toList();
    }

    private static List<Class<?>> findClasses(File directory, String packageName) {
        if (!directory.exists()) {
            return Collections.emptyList();
        }

        var classes = new ArrayList<Class<?>>();

        for (var file : requireNonNull(directory.listFiles())) {
            if (file.isDirectory()) {
                classes.addAll(findClasses(file, packageName + CLASS_DELIMITER + file.getName()));
            } else if (file.getName().endsWith(CLASS_EXTENSION)) {
                try {
                    var className = file.getName().substring(0, file.getName().length() - CLASS_EXTENSION.length());
                    classes.add(Class.forName(packageName + CLASS_DELIMITER + className));
                } catch (ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return classes;
    }

}
