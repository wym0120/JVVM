package classloader.attribute.smta;

import classloader.BuildInfo;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class SameFramExtended extends StackMapFrame {
    private int offsetDelta;
    public SameFramExtended(int frameType, BuildInfo buildInfo) {
        super(frameType);
        offsetDelta = buildInfo.getU2();
    }

    @Override
    public int getOffsetDelta() {
        return offsetDelta;
    }
}
