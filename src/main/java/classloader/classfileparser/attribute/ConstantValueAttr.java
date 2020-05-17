package classloader.classfileparser.attribute;

import classloader.classfileparser.BuildUtil;
import lombok.Data;

@Data
public class ConstantValueAttr extends AttributeInfo {
    private int constantValueIndex;

    public ConstantValueAttr(BuildUtil buildUtil, int index, int length) {
        super(index, length);
        constantValueIndex = buildUtil.getU2();
    }
}
