package util;

import java.io.File;

public class PathUtil {

    public static String transform(String pathName) {
        if (pathName.contains("/")) {
            //work for both linux and Windows, getName method in windows will return abc/def rather than abc\def
            return pathName.replace("/", File.separator);
        }
        return pathName;
    }
}
