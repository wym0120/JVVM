package classloader.classfileparser.attribute.smta;

import classloader.classfileparser.BuildUtil;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class SameLocals1StackItemFramExtended extends StackMapFrame {
    private int offsetDelta;
    private VerificationTypeInfo[] stack = new VerificationTypeInfo[1];
    public SameLocals1StackItemFramExtended(int frameType, BuildUtil buildUtil) {
        super(frameType);
        offsetDelta = buildUtil.getU2();
        stack[0] = VerificationTypeInfo.read(buildUtil);
    }

    @Override
    public int getOffsetDelta() {
        return offsetDelta;
    }
}
