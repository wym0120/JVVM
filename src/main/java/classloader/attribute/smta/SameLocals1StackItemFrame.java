package classloader.attribute.smta;

import classloader.BuildInfo;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class SameLocals1StackItemFrame extends StackMapFrame {
    private VerificationTypeInfo[] stack = new VerificationTypeInfo[1];

    public SameLocals1StackItemFrame(int frameType, BuildInfo buildInfo) {
        super(frameType);
        stack[0] = VerificationTypeInfo.read(buildInfo);
    }

    @Override
    public int getOffsetDelta() {
        return super.frameType - 64;
    }
}
