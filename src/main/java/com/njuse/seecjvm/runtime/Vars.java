package com.njuse.seecjvm.runtime;

import com.njuse.seecjvm.runtime.struct.JObject;
import com.njuse.seecjvm.runtime.struct.Slot;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Vars {
    private Slot[] varSlots;
    private int maxSize;

    public Vars(int maxVarSize) {
        assert maxVarSize >= 0;
        maxSize = maxVarSize;
        varSlots = new Slot[maxVarSize];
        for (int i = 0; i < maxVarSize; i++) varSlots[i] = new Slot();
    }

    public void setInt(int index, int value) {
        if (index < 0 || index >= maxSize) throw new IndexOutOfBoundsException();
        varSlots[index].setValue(value);
    }

    public int getInt(int index) {
        if (index < 0 || index >= maxSize) throw new IndexOutOfBoundsException();
        return varSlots[index].getValue();
    }

    public void setFloat(int index, float value) {
        if (index < 0 || index >= maxSize) throw new IndexOutOfBoundsException();
        varSlots[index].setValue(Float.floatToIntBits(value));
    }

    public float getFloat(int index) {
        if (index < 0 || index >= maxSize) throw new IndexOutOfBoundsException();
        return Float.intBitsToFloat(varSlots[index].getValue());
    }

    public void setLong(int index, long value) {
        if (index < 0 || index + 1 >= maxSize) throw new IndexOutOfBoundsException();
        varSlots[index].setValue((int) value);
        varSlots[index + 1].setValue((int) (value >> 32));
    }

    public long getLong(int index) {
        if (index < 0 || index + 1 >= maxSize) throw new IndexOutOfBoundsException();
        int low = varSlots[index].getValue();
        int high = varSlots[index + 1].getValue();
        return (((long) high) << 32) | ((long) low & 0x0ffffffffL);
    }

    public void setDouble(int index, double value) {
        if (index < 0 || index + 1 >= maxSize) throw new IndexOutOfBoundsException();
        setLong(index, Double.doubleToLongBits(value));
    }

    public double getDouble(int index) {
        if (index < 0 || index + 1 >= maxSize) throw new IndexOutOfBoundsException();
        return Double.longBitsToDouble(getLong(index));
    }

    public void setObjectRef(int index, JObject ref) {
        if (index < 0 || index >= maxSize) throw new IndexOutOfBoundsException();
        varSlots[index].setObject(ref);
    }

    public JObject getObjectRef(int index) {
        if (index < 0 || index >= maxSize) throw new IndexOutOfBoundsException();
        return varSlots[index].getObject();
    }

    public void setSlot(int index, Slot slot) {
        if (index < 0 || index >= maxSize) throw new IndexOutOfBoundsException();
        varSlots[index] = slot;
    }

}
