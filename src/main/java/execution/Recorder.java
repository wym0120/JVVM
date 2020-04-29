package execution;

import instructions.base.Instruction;
import memory.JHeap;
import memory.jclass.Method;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.Vars;
import runtime.struct.JObject;
import vo.FrameVO;
import vo.MemoryVO;
import vo.ObjectVO;
import vo.StateVO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Recorder {

    public static StateVO recodeState(StackFrame ori, StackFrame next, Instruction instruction) {
        StateVO state;
        Set<JObject> heap = JHeap.getInstance().getHeap();
        //must handle instruction before next frame!!
        String currentInst = instruction.toString();
        MemoryVO memoryVO = generateMemoryVO(heap);
        FrameVO oriVO = generateFrameVO(ori);
        if (ori == next) {
            state = new StateVO(oriVO, memoryVO, currentInst);
        } else {
            FrameVO nextVO = generateFrameVO(next);
            state = new StateVO(oriVO, nextVO, memoryVO, currentInst);
        }
        return state;
    }

    private static MemoryVO generateMemoryVO(Set<JObject> heap) {
        MemoryVO ret = new MemoryVO();
        List<ObjectVO> heapVO = ret.getObjects();
        assert heap != null;
        heap.forEach(obj -> {
            heapVO.add(new ObjectVO(obj));
        });
        return ret;
    }

    private static FrameVO generateFrameVO(StackFrame frame) {
        Method method = frame.getMethod();
        if (!method.isParsed()) method.parseCode();
        assert method.isParsed();
        OperandStack stack = frame.getOperandStack();
        Vars localVars = frame.getLocalVars();
        int nextPC = frame.getNextPC();
        ArrayList<String> instList = method.getInstList();
        return new FrameVO(stack, localVars, nextPC, instList);
    }
}
