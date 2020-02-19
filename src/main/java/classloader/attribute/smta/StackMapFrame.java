package classloader.attribute.smta;

import classloader.BuildInfo;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class StackMapFrame {
    protected final int frameType;

    static StackMapFrame read(BuildInfo buildInfo) {
        int frameType = buildInfo.getU1();
        if (frameType <= 63) {
            return new SameFrame(frameType);
        } else if (frameType <= 127) {
            return new SameLocals1StackItemFrame(frameType, buildInfo);
        } else if (frameType <= 246) {
            throw new UnsupportedOperationException("unknown frame_type " + frameType);
        } else if (frameType == 247) {
            return new SameLocals1StackItemFramExtended(frameType, buildInfo);
        } else if (frameType <= 250) {
            return new ChopFrame(frameType, buildInfo);
        } else if (frameType == 251) {
            return new SameFramExtended(frameType, buildInfo);
        } else {
            return (frameType <= 254 ? new AppendFrame(frameType, buildInfo)
                    : new FullFrame(frameType, buildInfo));
        }
    }

    public StackMapFrame(int frameType) {
        this.frameType = frameType;
    }


    public int getOffsetDelta() {
        return frameType;
    }
}
