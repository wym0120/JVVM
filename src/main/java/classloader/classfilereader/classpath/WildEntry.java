package classloader.classfilereader.classpath;

import util.IOUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;

/**
 * 适用于 abc/dir/* 的情况
 * @author WYM
 */
public class WildEntry extends Entry {
    public WildEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        String baseDir = classpath.substring(0,classpath.length()-2);
        File dir = new File(baseDir);
        if(!dir.isDirectory() || !dir.exists()){
            throw new FileNotFoundException("Cannot find the folder : "+baseDir);
        }
        byte[] ret = new byte[0];
        LinkedList<File> dirList = new LinkedList<>();
        for(File tmp : Objects.requireNonNull(dir.listFiles())){
            if(tmp.isFile()){
                if(tmp.getName().equals(className)){
                    String absPath = tmp.getAbsolutePath();
                    ret = IOUtil.readFileByBytes(new FileInputStream(absPath));
                    break;
                }
            }else{
                dirList.add(tmp);
            }
        }
        File subDir = null;
        while(!dirList.isEmpty()){
            subDir = dirList.removeFirst();
            for(File tmp: Objects.requireNonNull(subDir.listFiles())){
                if(tmp.isFile()){
                    if(tmp.getName().equals(className)){
                        String absPath = tmp.getAbsolutePath();
                        ret = IOUtil.readFileByBytes(new FileInputStream(absPath));
                        break;
                    }
                }else{
                    dirList.add(tmp);
                }
            }
        }
        return ret;
    }

}
