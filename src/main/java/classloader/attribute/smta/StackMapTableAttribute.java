package classloader.attribute.smta;

import classloader.BuildInfo;
import classloader.attribute.AttributeInfo;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class StackMapTableAttribute extends AttributeInfo {
    private int numberOfEntries;
    private StackMapFrame[] entries;

    public StackMapTableAttribute(BuildInfo buildInfo, int index, int length) {
        super(index,length);
        numberOfEntries = buildInfo.getU2();
        entries = new StackMapFrame[numberOfEntries];
        for (int i = 0; i < entries.length; i++) {
            entries[i] = StackMapFrame.read(buildInfo);
        }

    }
}
