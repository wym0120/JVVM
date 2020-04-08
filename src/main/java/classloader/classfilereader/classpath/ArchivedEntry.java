package classloader.classfilereader.classpath;

import util.IOUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 适用于 abc/dir/target.zip 的情况
 *
 * @author WYM
 */
public class ArchivedEntry extends Entry {
    public ArchivedEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        File dir = new File(classpath);
        String absDirPath = dir.getAbsolutePath();
        ZipFile zipFile = new ZipFile(absDirPath);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry ze = entries.nextElement();
            String pathName = ze.getName();
            //work for both linux and Windows, getName method in windows will return abc/def rather than abc\def
            if (pathName.contains("/")) pathName = pathName.replace("/", this.FILE_SEPARATOR);
            if (pathName.equals(className)) {
                return IOUtil.readFileByBytes(zipFile.getInputStream(ze));
            }
        }
        return null;
    }
}
