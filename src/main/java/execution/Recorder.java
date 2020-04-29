package execution;

import instructions.base.Instruction;
import memory.JHeap;
import memory.MethodArea;
import memory.jclass.JClass;
import memory.jclass.Method;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.Vars;
import runtime.struct.JObject;
import vo.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class Recorder {

    public static StateVO recodeState(StackFrame ori, StackFrame next, Instruction instruction) {
        StateVO state;
        Set<JObject> heap = JHeap.getInstance().getHeap();
        Collection<JClass> methodArea = MethodArea.getClassMap().values();
        //must handle instruction before next frame!!
        String currentInst = instruction.toString();
        MemoryVO memoryVO = generateMemoryVO(heap, methodArea);
        FrameVO oriVO = generateFrameVO(ori);
        if (ori == next) {
            state = new StateVO(oriVO, memoryVO, currentInst);
        } else {
            FrameVO nextVO = generateFrameVO(next);
            state = new StateVO(oriVO, nextVO, memoryVO, currentInst);
        }
        return state;
    }

    private static MemoryVO generateMemoryVO(Set<JObject> heap, Collection<JClass> methodArea) {
        MemoryVO ret = new MemoryVO();
        List<ObjectVO> objects = ret.getObjects();
        List<ClassVO> classes = ret.getClasses();
        assert heap != null;
        heap.forEach(obj -> {
            objects.add(new ObjectVO(obj));
        });
        methodArea.forEach(clazz -> {
            classes.add(new ClassVO(clazz));
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
        String methodName = method.getClazz().getName() + " : " + method.getName();
        return new FrameVO(stack, localVars, nextPC, instList, methodName);
    }
}
