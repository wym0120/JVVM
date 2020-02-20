package classloader.attribute;

import classloader.BuildUtil;
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

    public ConstantValueAttr(BuildUtil buildUtil, int index, int length) {
        super(index, length);
        constantValueIndex = buildUtil.getU2();
    }
}
