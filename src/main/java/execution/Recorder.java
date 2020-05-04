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
import java.util.HashSet;
import java.util.List;

public class Recorder {

    public static StateVO recodeState(StackFrame ori, StackFrame next, Instruction instruction) {
        HashSet objects = JHeap.getInstance().getObjects();
        Collection<JClass> methodArea = MethodArea.getClassMap().values();
        //must handle instruction before next frame!!
        String currentInst = instruction.toString();
        MemoryVO memoryVO = generateMemoryVO(objects, methodArea);
        FrameVO oriVO = generateFrameVO(ori);
        StateVO state;
        if (ori == next) {
            state = new StateVO(oriVO, memoryVO, currentInst);
        } else {
            FrameVO nextVO = generateFrameVO(next);
            state = new StateVO(oriVO, nextVO, memoryVO, currentInst);
        }
        //make all origin classes and objects outdated before return
        makeOutdated();
        return state;
    }

    private static MemoryVO generateMemoryVO(HashSet<JObject> heapObjects, Collection<JClass> methodArea) {
        MemoryVO ret = new MemoryVO();
        List<ObjectVO> objects = ret.getObjects();
        List<ClassVO> classes = ret.getClasses();
        assert heapObjects != null;
        heapObjects.forEach(obj -> {
            objects.add(new ObjectVO(obj, JHeap.getObjectState().get(obj.getId())));
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

    private static void makeOutdated() {
        JHeap.getObjectState().keySet().forEach(id -> {
            JHeap.getObjectState().put(id, false);
        });
        MethodArea.getClassState().keySet().forEach(name -> {
            MethodArea.getClassState().put(name, false);
        });
    }
}
