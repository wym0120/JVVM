package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class ClassInfo extends ConstantPoolInfo {
    private int nameIndex;

    public ClassInfo(ConstantPool constantPool, int nameIndex) {
        super(constantPool);
        this.nameIndex = nameIndex;
        super.tag = ConstantPoolInfo.CLASS;
    }


    public String getClassName() {
        return ((UTF8Info) myCP.get(nameIndex)).getString();

    }

    @Override
    public String toString() {
        return getClassName();
    }
}
