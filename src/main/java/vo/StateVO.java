package vo;

import instructions.base.Instruction;

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

    public StateVO(FrameVO currentFrame, FrameVO optionalNextFrame, MemoryVO memory, Instruction instruction) {
        this.currentFrame = currentFrame;
        this.optionalNextFrame = optionalNextFrame;
        this.memory = memory;
        this.instruction = instruction.toString();
        hasNext = true;
    }

    public StateVO(FrameVO currentFrame, MemoryVO memory, Instruction instruction) {
        this.currentFrame = currentFrame;
        this.memory = memory;
        this.instruction = instruction.toString();
        hasNext = false;
    }
}
