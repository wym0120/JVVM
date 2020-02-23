package runtime;

import runtime.struct.JObject;
import runtime.struct.Slot;
import util.BasicTypeUtil;

public class Vars {
    private Slot[] varSlots;

    public Vars(int maxVarSize){
        assert maxVarSize > 0;
        varSlots = new Slot[maxVarSize];
    }

    public void setInt(int index,int value){
        varSlots[index].setValue(value);
    }

    public int getInt(int index){
        return varSlots[index].getValue();
    }

    public void setFloat(int index,float value){
        varSlots[index].setValue(BasicTypeUtil.float2int(value));
    }

    public float getFloat(int index){
        return BasicTypeUtil.int2float(varSlots[index].getValue());
    }

    public void setLong(int index,long value){
        varSlots[index].setValue((int)value);
        varSlots[index+1].setValue((int)(value>>32));
    }

    public long getLong(int index){
        int low = varSlots[index].getValue();
        int high = varSlots[index+1].getValue();
        return (((long) high) <<32) | ((long)low & 0x0ffffffffL);
    }

    public void setDouble(int index,double value){
        setLong(index,Double.doubleToLongBits(value));
    }

    public double getDouble(int index){
        return Double.longBitsToDouble(getLong(index));
    }

    public void setObjectRef(int index, JObject ref){
        varSlots[index].setObject(ref);
    }

    public JObject getObjectRef(int index){
        return varSlots[index].getObject();
    }

    public void setSlot(int index,Slot slot){
        varSlots[index]=slot;
    }

    public Slot getSlot(int index){
        return varSlots[index];
    }
}
