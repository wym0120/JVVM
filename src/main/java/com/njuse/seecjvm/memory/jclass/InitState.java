package com.njuse.seecjvm.memory.jclass;

public enum InitState {
    PREPARED,// class is prepared and is not initialized
    BUSY,// class is initializing by other thread
    SUCCESS,// success initialized
    FAIL// cannot be initialized again
}
