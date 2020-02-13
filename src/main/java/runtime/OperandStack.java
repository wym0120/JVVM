package runtime;

import runtime.struct.JObject;
import runtime.struct.Slot;
import util.BasicTypeUtil;

import java.util.EmptyStackException;

/**
 * 操作数栈
 *
 * @author WYM
 */
public class OperandStack {
    //表示栈顶位置
    private int top;
    private Slot[] slots;

    public OperandStack(int maxStackSize) {
        assert maxStackSize > 0;
        slots = new Slot[maxStackSize];
        top = 0;
    }

    public void pushInt(int value) {
        slots[top].setValue(value);
        top++;
    }

    public int popInt() {
        top--;
        if (top < 0) throw new EmptyStackException();
        return slots[top].getValue();
    }

    public void pushFloat(float value) {
        slots[top].setValue(BasicTypeUtil.float2int(value));
        top++;
    }

    public float popFloat() {
        top--;
        if (top < 0) throw new EmptyStackException();
        return BasicTypeUtil.int2float(slots[top].getValue());
    }

    public void pushLong(long value) {
        int low = (int) value;
        int high = (int) (value >> 32);
        slots[top].setValue(low);
        slots[top + 1].setValue(high);
        top += 2;
    }

    public long popLong() {
        top -= 2;
        if (top < 0) throw new EmptyStackException();
        int low = slots[top].getValue();
        int high = slots[top+1].getValue();
        return (((long) high) <<32) | ((long)low & 0x0ffffffffL);
    }

    public void pushDouble(double value){
        pushLong(Double.doubleToLongBits(value));
    }

    public double popDouble(){
        return Double.longBitsToDouble(popLong());
    }

    public void pushObjectRef(JObject ref){
        slots[top].setObject(ref);
        top++;
    }

    public JObject popObjectRef(){
        top--;
        if(top<0)throw new EmptyStackException();
        return slots[top].getObject();
    }

}
