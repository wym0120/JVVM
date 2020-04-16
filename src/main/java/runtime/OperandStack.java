package runtime;

import lombok.Data;
import runtime.struct.JObject;
import runtime.struct.Slot;

import java.util.EmptyStackException;

@Data
public class OperandStack {
    private int top;
    private int maxStackSize;
    private Slot[] slots;

    public OperandStack(int maxStackSize) {
        assert maxStackSize >= 0;
        this.maxStackSize = maxStackSize;
        slots = new Slot[maxStackSize];
        for (int i = 0; i < maxStackSize; i++) slots[i] = new Slot();
        top = 0;
    }

    public void pushInt(int value) {
        if (top >= maxStackSize) throw new StackOverflowError();
        slots[top].setValue(value);
        top++;
    }

    public int popInt() {
        top--;
        if (top < 0) throw new EmptyStackException();
        return slots[top].getValue();
    }

    public void pushFloat(float value) {
        if (top >= maxStackSize) throw new StackOverflowError();
        slots[top].setValue(Float.floatToIntBits(value));
        top++;
    }

    public float popFloat() {
        top--;
        if (top < 0) throw new EmptyStackException();
        return Float.intBitsToFloat(slots[top].getValue());
    }

    public void pushLong(long value) {
        if (top + 1 >= maxStackSize) throw new StackOverflowError();
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
        if (top >= maxStackSize) throw new StackOverflowError();
        slots[top].setObject(ref);
        top++;
    }

    public JObject popObjectRef(){
        top--;
        if(top<0)throw new EmptyStackException();
        return slots[top].getObject();
    }

    public void pushSlot(Slot slot){
        if (top >= maxStackSize) throw new StackOverflowError();
        slots[top] = slot;
        top++;
    }

    public Slot popSlot(){
        top--;
        if(top<0)throw new EmptyStackException();
        return slots[top];
    }

}
