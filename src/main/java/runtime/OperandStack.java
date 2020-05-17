package runtime;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import runtime.struct.JObject;
import runtime.struct.Slot;

import java.util.EmptyStackException;

@Getter
@Setter
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
        int ret = slots[top].getValue();
        slots[top] = new Slot();
        return ret;
    }

    public void pushFloat(float value) {
        if (top >= maxStackSize) throw new StackOverflowError();
        slots[top].setValue(Float.floatToIntBits(value));
        top++;
    }

    public float popFloat() {
        top--;
        if (top < 0) throw new EmptyStackException();
        float ret = Float.intBitsToFloat(slots[top].getValue());
        slots[top] = new Slot();
        return ret;
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
        int high = slots[top + 1].getValue();
        slots[top] = new Slot();
        slots[top + 1] = new Slot();
        return (((long) high) << 32) | ((long) low & 0x0ffffffffL);
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

    public JObject popObjectRef() {
        top--;
        if (top < 0) throw new EmptyStackException();
        JObject ret = slots[top].getObject();
        slots[top] = new Slot();
        return ret;
    }

    public void pushSlot(Slot slot){
        if (top >= maxStackSize) throw new StackOverflowError();
        slots[top] = slot;
        top++;
    }

    public Slot popSlot() {
        top--;
        if (top < 0) throw new EmptyStackException();
        Slot ret = slots[top];
        slots[top] = new Slot();
        return ret;
    }

}
