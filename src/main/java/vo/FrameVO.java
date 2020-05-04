package vo;

import runtime.OperandStack;
import runtime.Vars;
import runtime.struct.ArrayObject;
import runtime.struct.NonArrayObject;
import runtime.struct.Slot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-04-14
 */
public class FrameVO {
    private ArrayList<String> operandStack;//operandStack[0] is the bottom of this stack
    private ArrayList<String> localVars;
    private int nextPC;
    private ArrayList<String> code;
    private String methodName;
    private boolean fresh;

    public FrameVO(OperandStack stack, Vars localVars, int nextPC, List<String> code, String methodName, boolean fresh) {
        this.nextPC = nextPC;
        this.code = new ArrayList<>(code);
        this.operandStack = printVars(stack.getSlots());
        this.localVars = printVars(localVars.getVarSlots());
        this.methodName = methodName;
        this.fresh = fresh;
    }

    private ArrayList<String> printVars(Slot[] vars) {
        ArrayList<String> ret = new ArrayList<>();
        Arrays.stream(vars)
                .forEach(s -> {
                    assert s != null;
                    String info = null;
                    if (s.getValue() != null) info = "value = " + String.format("0x%08X", s.getValue());
                    else if (s.getObject() != null) {
                        if (s.getObject() instanceof NonArrayObject)
                            info = "Object ref to " + s.getObject().getClazz().getName();
                        else if (s.getObject() instanceof ArrayObject)
                            info = "Object ref to " + ((ArrayObject) s.getObject()).getType();
                    } else {
                        info = "Empty";
                    }
                    ret.add(info);
                });
        return ret;
    }
}
