package classloader.classfilereader.classpath;

import util.IOUtil;
import util.PathUtil;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

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
            String pathName = PathUtil.transform(ze.getName());
            if (pathName.equals(className)) {
                return IOUtil.readFileByBytes(zipFile.getInputStream(ze));
            }
        }
        return null;
    }
}
