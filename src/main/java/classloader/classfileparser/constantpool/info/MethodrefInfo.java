package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;
import com.sun.tools.javac.util.Pair;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
public class MethodrefInfo extends MemberRefInfo {
    private int classIndex;
    private int nameAndTypeIndex;

    public MethodrefInfo(ConstantPool constantPool, int classIndex, int nameAndTypeIndex) {
        super(constantPool);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
        super.tag = ConstantPoolInfo.METHOD_REF;
    }


    public String getClassName() {
        return getClassName(classIndex);
    }


    public Pair<String, String> getNameAndDescriptor() {
        return ((NameAndTypeInfo) myCP.get(nameAndTypeIndex)).getNameAndDescriptor();
    }
}
