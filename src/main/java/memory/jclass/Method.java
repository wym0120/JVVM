package memory.jclass;

import classloader.MethodInfo;

public class Method {
    private short accessFlags;
    private String name;
    private String descriptor;
    private JClass clazz;
    private int maxStack;
    private int maxLocalVars;
    private byte[] code;


    public Method (MethodInfo info,JClass clazz){
        this.clazz = clazz;
        //        accessFlags = info.getAccessFlags();
//        name = info.getName();
//        descriptor = info.getDescriptor();
        //todo code attribute
    }
}
