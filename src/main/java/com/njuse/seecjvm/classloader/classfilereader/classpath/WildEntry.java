package com.njuse.seecjvm.classloader.classfilereader.classpath;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * format : dir/*
 */
public class WildEntry extends Entry {
    public WildEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        String baseDir = classpath.substring(0, classpath.length() - 2);
        File dir = new File(baseDir);
        if (!dir.isDirectory() || !dir.exists()) {
            throw new FileNotFoundException("Cannot find the folder : " + baseDir);
        }
        File[] dirList = dir.listFiles((f) -> f.getName().endsWith(".jar") || f.getName().endsWith(".JAR"));
        assert dirList != null;
        String classPath = Arrays.stream(dirList).map(File::getAbsolutePath).collect(Collectors.joining(this.PATH_SEPARATOR));
        CompositeEntry entry = new CompositeEntry(classPath);
        return entry.readClass(className);
    }
}
