package com.njuse.seecjvm.classloader.classfilereader.classpath;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;

@Getter
@Setter
public abstract class Entry {
    public final String PATH_SEPARATOR = File.pathSeparator;
    public final String FILE_SEPARATOR = File.separator;
    public String classpath;

    public Entry(String classpath) {
        this.classpath = classpath;
    }

    public abstract byte[] readClass(String className) throws IOException;
}
