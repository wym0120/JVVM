package classloader.attribute.smta;

import classloader.BuildInfo;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class SameLocals1StackItemFramExtended extends StackMapFrame {
    private int offsetDelta;
    private VerificationTypeInfo[] stack = new VerificationTypeInfo[1];
    public SameLocals1StackItemFramExtended(int frameType, BuildInfo buildInfo) {
        super(frameType);
        offsetDelta = buildInfo.getU2();
        stack[0] = VerificationTypeInfo.read(buildInfo);
    }

    @Override
    public int getOffsetDelta() {
        return offsetDelta;
    }
}
