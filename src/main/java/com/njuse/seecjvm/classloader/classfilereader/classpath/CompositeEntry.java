package com.njuse.seecjvm.classloader.classfilereader.classpath;

import com.njuse.seecjvm.classloader.classfilereader.ClassFileReader;

import java.io.IOException;

/**
 * format dir/subdir/... ; dir/* ; dir/subdir/target.zip(jar)
 */
public class CompositeEntry extends Entry {
    public CompositeEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        String[] pathList = classpath.split(PATH_SEPARATOR);
        byte[] ret = null;
        for (String path : pathList) {
            Entry tmp = ClassFileReader.chooseEntryType(path);
            ret = tmp.readClass(className);
            if (ret != null) break;
        }
        return ret;
    }
}
