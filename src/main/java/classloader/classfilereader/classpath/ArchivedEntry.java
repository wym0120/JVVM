package classloader.classfilereader.classpath;

import util.IOUtil;
import util.PathUtil;

import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * format: dir/subdir/target.zip(jar)
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
