package memory.jclass;

import classloader.FieldInfo;

public class Field {
    private short accessFlags;
    private String name;
    private String descriptor;
    private JClass clazz;

    public Field(FieldInfo info,JClass clazz){
        this.clazz = clazz;
//        accessFlags = info.getAccessFlags();
//        name = info.getName();
//        descriptor = info.getDescriptor();

    }
}
