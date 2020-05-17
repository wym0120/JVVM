package classloader.classfileparser.attribute.smta;

import classloader.classfileparser.BuildUtil;

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
