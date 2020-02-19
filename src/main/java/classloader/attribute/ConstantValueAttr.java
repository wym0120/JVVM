package classloader.attribute;

import classloader.BuildInfo;
import lombok.Data;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-15
 */
@Data
public class ConstantValueAttr extends AttributeInfo {
    private int constantValueIndex;

    public ConstantValueAttr(BuildInfo buildInfo, int index, int length) {
        super(index, length);
        constantValueIndex = buildInfo.getU2();
    }
}
