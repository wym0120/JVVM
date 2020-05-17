package com.njuse.seecjvm.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njuse.seecjvm.vo.StateVO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class JsonUtil {
    private static String storePath;

    static {
        storePath = String.join(File.separator, "src", "test", "testfile", "res");
    }

    public static void storeResult(String name, ArrayList<StateVO> res) {
        ObjectMapper mapper = new ObjectMapper();
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        String jsonString = null;
        try {

            jsonString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(res);
            File outFile = new File(storePath + File.separator + name.replace("/", "_") + ".json");
            outFile.createNewFile();
            PrintWriter os = new PrintWriter(new FileOutputStream(outFile));
            os.println(jsonString);
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
