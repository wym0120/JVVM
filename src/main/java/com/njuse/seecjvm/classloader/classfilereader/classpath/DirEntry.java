package com.njuse.seecjvm.classloader.classfilereader.classpath;

import com.njuse.seecjvm.util.IOUtil;
import com.njuse.seecjvm.util.PathUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * format dir/subdir/...
 */
public class DirEntry extends Entry {
    public DirEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        File dir = new File(classpath);
        String absDirPath = dir.getAbsolutePath();
        String truePath = PathUtil.transform(String.join(FILE_SEPARATOR, absDirPath, className));
        File file = new File(truePath);
        if (file.isFile() && file.exists()) {
            return IOUtil.readFileByBytes(new FileInputStream(truePath));
        } else {
            return null;
        }

    }
}
