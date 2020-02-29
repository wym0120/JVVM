package classloader.classfilereader.classpath;

import util.IOUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

/**
 * 适用于 abc/dir/target.zip 的情况
 * @author WYM
 */
public class ZipEntry extends Entry {
    public ZipEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        File dir = new File(classpath);
        String absDirPath = dir.getAbsolutePath();
        ZipFile zipFile = new ZipFile(absDirPath);

        byte[] ret = new byte[0];
        FileInputStream input = new FileInputStream(absDirPath);
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(input), Charset.forName("GBK"));
        java.util.zip.ZipEntry ze = null;

        while ((ze = zipInputStream.getNextEntry()) != null) {
            if(ze.getName().equals(className)){
               ret = IOUtil.readFileByBytes(zipFile.getInputStream(ze));
               break;
            }
        }

        zipInputStream.closeEntry();
        input.close();
        return ret;
    }
}
