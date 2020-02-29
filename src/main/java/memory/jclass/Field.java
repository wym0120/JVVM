package memory.jclass;

import classloader.classfileparser.FieldInfo;
import lombok.Data;

@Data
public class Field extends ClassMember {
    private int slotID;
    private int constValueIndex;

    public Field(FieldInfo info, JClass clazz) {
        this.clazz = clazz;
        accessFlags = info.getAccessFlags();
        name = info.getName();
        descriptor = info.getDescriptor();
        if (info.getConstantValueAttr() != null) {
            constValueIndex = info.getConstantValueAttr().getConstantValueIndex();
        }
    }
}
