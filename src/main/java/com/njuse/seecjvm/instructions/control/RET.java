package com.njuse.seecjvm.instructions.control;

import com.njuse.seecjvm.instructions.base.Index8Instruction;
import com.njuse.seecjvm.runtime.StackFrame;

/**
 * It is no longer supported after JDK5
 */
@Deprecated
public class RET extends Index8Instruction {
    @Override
    public void execute(StackFrame frame) {
        int index = super.index;
//        frame.getLocalVars()
    }
}
