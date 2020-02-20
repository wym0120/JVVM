package classloader.attribute.smta;

import classloader.BuildUtil;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class AppendFrame extends StackMapFrame {
    private int offsetDelta;
    private VerificationTypeInfo[] locals;
    public AppendFrame(int frameType, BuildUtil buildUtil) {
        super(frameType);
        offsetDelta = buildUtil.getU2();
        locals = new VerificationTypeInfo[frameType - 251];
        for (int i = 0; i < locals.length; i++) {
            locals[i] = VerificationTypeInfo.read(buildUtil);
        }
    }

    @Override
    public int getOffsetDelta() {
        return offsetDelta;
    }
}
