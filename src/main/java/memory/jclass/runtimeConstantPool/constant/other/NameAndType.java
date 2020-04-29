package memory.jclass.runtimeConstantPool.constant.other;

import classloader.classfileparser.constantpool.info.NameAndTypeInfo;
import memory.jclass.runtimeConstantPool.constant.Constant;

public class NameAndType implements Constant {
    private int nameIndex;
    private int descriptorIndex;

    public NameAndType(NameAndTypeInfo info) {
        this.nameIndex = info.getNameIndex();
        this.descriptorIndex = info.getDescriptorIndex();
    }

    @Override
    public String toString() {
        return "NameAndType : nameIndex = " + nameIndex + " descriptorIndex = " + descriptorIndex;
    }
}
