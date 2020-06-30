package com.njuse.seecjvm.execution;

import com.njuse.seecjvm.instructions.base.Instruction;
import com.njuse.seecjvm.memory.jclass.Method;
import com.njuse.seecjvm.runtime.JThread;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.ArrayObject;
import com.njuse.seecjvm.runtime.struct.NonArrayObject;
import com.njuse.seecjvm.runtime.struct.Slot;
import com.njuse.seecjvm.util.ColorUtil;
import com.njuse.seecjvm.vo.StateVO;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;

public class Interpreter {
    private static ByteBuffer codeReader;

    public static ArrayList<StateVO> interpret(JThread thread) {
        initCodeReader(thread);
        return loop(thread);
    }

    /**
     * This method set the code reader according to topFrame
     * When topFrame changes, this method should be called
     */
    private static void initCodeReader(JThread thread) {
        byte[] code = thread.getTopFrame().getMethod().getCode();
        codeReader = ByteBuffer.wrap(code);
        int nextPC = thread.getTopFrame().getNextPC();
        codeReader.position(nextPC);
    }

    private static ArrayList<StateVO> loop(JThread thread) {
        ArrayList<StateVO> ret = new ArrayList<>();
        //record first state
        ret.add(Recorder.initialState(thread.getTopFrame(), thread));
        while (true) {
            StackFrame oriTop = thread.getTopFrame();
            //parse code attribute for VO
            Method method = oriTop.getMethod();
            if (!method.isParsed()) {
                method.parseCode();
            }
            //set the reader's position to nextPC
            codeReader.position(oriTop.getNextPC());
            //fetch and decode
            int opcode = codeReader.get() & 0xff;
            Instruction instruction = Decoder.decode(opcode);
            instruction.fetchOperands(codeReader);
            //set nextPC to reader's position
            int nextPC = codeReader.position();
            oriTop.setNextPC(nextPC);
            instruction.execute(oriTop);
            //check whether there's a new frame
            //and whether there's more frame to exec
            StackFrame newTop = thread.getTopFrame();
            if (newTop == null) {
                return ret;
            }
//            PrintInfo(oriTop, newTop, thread, instruction);
            ret.add(Recorder.recodeState(oriTop, newTop, thread));
            if (oriTop != newTop) {
                initCodeReader(thread);
            }
        }

    }

    private static void PrintInfo(StackFrame ori, StackFrame next, JThread thread, Instruction instruction) {
        String langSpace = "    ";
        String classNameOfInst = instruction.getClass().toString();
        System.out.println("After " + classNameOfInst.substring(classNameOfInst.lastIndexOf(".") + 1) + " exec:");
        ColorUtil.printYellow(("    Methods in current thread:"));
        thread.getStack().getStack().forEach(m -> System.out.println(langSpace + m.getMethod().getClazz().getName() + " : " + m.getMethod().getName()));
        System.out.println();
        ColorUtil.printYellow(langSpace + "Contents in operand stack:");
        printVars(ori.getOperandStack().getSlots());
        System.out.println();
        ColorUtil.printYellow(langSpace + "Contents in local var:");
        printVars(ori.getLocalVars().getVarSlots());
        System.out.println();

        if (ori == next) {
            ColorUtil.printCyan("Next frame doesn't change.Method is still " + ori.getMethod().getClazz().getName() + " : " + ori.getMethod().getName());
        } else {
            ColorUtil.printRed("Next frame changed.Method is " + next.getMethod().getClazz().getName() + " : " + next.getMethod().getName());
        }
        ColorUtil.printBlue("----------------------------------------------------------------------");
    }

    private static void printVars(Slot[] vars) {
        String langSpace = "    ";
        Arrays.stream(vars)
                .forEach(s -> {
                    assert s != null;
                    if (s.getValue() != null) System.out.println(langSpace + "value = " + s.getValue());
                    else if (s.getObject() != null) {
                        if (s.getObject() instanceof NonArrayObject)
                            System.out.println(langSpace + "Object ref to -> " + s.getObject().getClazz().getName());
                        else if (s.getObject() instanceof ArrayObject)
                            System.out.println(langSpace + "Object ref to -> " + ((ArrayObject) s.getObject()).getType());
                    }
                });
    }

}
