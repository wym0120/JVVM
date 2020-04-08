package classloader.classfilereader.classpath;

import util.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 适用于 abc/dir 的情况
 * @author WYM
 */
public class DirEntry extends Entry{
    public DirEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        File dir = new File(classpath);
        String absDirPath = dir.getAbsolutePath();
        String truePath = String.join(FILE_SEPARATOR,absDirPath,className);
        File file = new File(truePath);
        if (file.isFile() && file.exists()) {
            return IOUtil.readFileByBytes(new FileInputStream(truePath));
        } else{
            return null;
        }

    }
}
