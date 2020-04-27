package util;

import java.lang.instrument.Instrumentation;

public class SizeCalculatorUtil {
    private static Instrumentation instrumentation;

    public static void premain(String args, Instrumentation inst) {
        instrumentation = inst;
    }

    public static long getObjectSize(Object obj) {
        assert obj != null;
        return instrumentation.getObjectSize(obj);
    }
}
