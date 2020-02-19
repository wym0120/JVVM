package classloader.attribute.smta;

import classloader.BuildInfo;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class FullFrame extends StackMapFrame {
    private int offsetDelta;
    private int numberOfLocals;
    private VerificationTypeInfo[] locals;
    private int numberOfStackItems;
    private VerificationTypeInfo[] stack;

    public FullFrame(int frameType, BuildInfo buildInfo) {
        super(frameType);
        offsetDelta = buildInfo.getU2();
        numberOfLocals = buildInfo.getU2();
        locals = new VerificationTypeInfo[numberOfLocals];
        for (int i = 0; i < locals.length; i++) {
            locals[i] = VerificationTypeInfo.read(buildInfo);
        }
        numberOfStackItems = buildInfo.getU2();
        stack = new VerificationTypeInfo[numberOfStackItems];
        for (int i = 0; i < stack.length; i++) {
            stack[i] = VerificationTypeInfo.read(buildInfo);
        }
    }

    @Override
    public int getOffsetDelta() {
        return offsetDelta;
    }
}
