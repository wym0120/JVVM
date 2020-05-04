package vo;

/**
 * Description:
 *
 * @author xiao bai
 * Created on 2020-04-15
 */
public class StateVO {
    private String instruction;
    private FrameVO currentFrame;
    private FrameVO optionalNextFrame;
    private MemoryVO memory;
    private boolean hasNext;
    private ThreadVO thread;

    public StateVO(FrameVO currentFrame, FrameVO optionalNextFrame, MemoryVO memory, String instruction) {
        this.instruction = instruction;
        this.currentFrame = currentFrame;
        this.optionalNextFrame = optionalNextFrame;
        this.memory = memory;
        hasNext = true;
    }

    public StateVO(FrameVO currentFrame, MemoryVO memory, String instruction) {
        this.instruction = instruction;
        this.currentFrame = currentFrame;
        this.memory = memory;
        hasNext = false;
    }
}
