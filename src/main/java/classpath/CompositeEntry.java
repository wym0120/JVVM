package classpath;

import classloader.ClassFileReader;

import java.io.IOException;

/**
 * 适用于 abc/dir;abc/dir/subDir/* 的情况
 * @author WYM
 */
public class CompositeEntry extends Entry{
    public CompositeEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClass(String className) throws IOException {
        String[] pathList = classpath.split(PATH_SEPARATOR);
        byte[] ret = new byte[0];
        for(String path:pathList){
            Entry tmp = ClassFileReader.chooseEntryType(path);
            ret = tmp.readClass(className);
            if(ret!=null && ret.length!=0)break;
        }
        return ret;
    }
}
