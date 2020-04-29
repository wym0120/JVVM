package classloader.classfileparser.constantpool.info;

import classloader.classfileparser.constantpool.ConstantPool;
import com.sun.tools.javac.util.Pair;
import lombok.Getter;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
@Getter
public class NameAndTypeInfo extends ConstantPoolInfo {
    private int nameIndex;
    private int descriptorIndex;

    public NameAndTypeInfo(ConstantPool constantPool, int nameIndex, int descriptorIndex) {
        super(constantPool);
        this.nameIndex = nameIndex;
        this.descriptorIndex = descriptorIndex;
        super.tag = ConstantPoolInfo.NAME_AND_TYPE;
    }

    public Pair<String, String> getNameAndDescriptor() {
        return Pair.of(
                ((UTF8Info) myCP.get(nameIndex)).getString(),
                ((UTF8Info) myCP.get(descriptorIndex)).getString());
    }
}
