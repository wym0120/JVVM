package classloader.attribute.smta;

import classloader.BuildUtil;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class ChopFrame extends StackMapFrame {
    private int offsetDelta;

    public ChopFrame(int frameType, BuildUtil buildUtil) {
        super(frameType);
        offsetDelta = buildUtil.getU2();
    }

    @Override
    public int getOffsetDelta() {
        return offsetDelta;
    }
}
