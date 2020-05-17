package com.njuse.seecjvm.vo;

public class StateVO {
    private String nextInstruction;
    private FrameVO currentFrame;
    private FrameVO optionalNextFrame;
    private MemoryVO memory;
    private boolean hasNext;
    private ThreadVO thread;

    public StateVO(FrameVO currentFrame, FrameVO optionalNextFrame, MemoryVO memory, String instruction, ThreadVO thread, boolean hasNext) {
        this.nextInstruction = instruction;
        this.currentFrame = currentFrame;
        this.optionalNextFrame = optionalNextFrame;
        this.memory = memory;
        this.thread = thread;
        this.hasNext = hasNext;
    }
}
