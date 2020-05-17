package com.njuse.seecjvm.instructions.references;

import com.njuse.seecjvm.instructions.base.Instruction;
import com.njuse.seecjvm.memory.JHeap;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import com.njuse.seecjvm.memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.ArrayObject;
import com.njuse.seecjvm.runtime.struct.array.RefArrayObject;

import java.nio.ByteBuffer;

public class MULTIANEWARRAY extends Instruction {
    private int index;//uint16
    private int dimensions;//uint8

    @Override
    public void fetchOperands(ByteBuffer reader) {
        index = reader.getShort() & 0xFFFF;
        dimensions = reader.get() & 0xFF;
    }

    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef classRef = (ClassRef) runtimeConstantPool.getConstant(index);
        try {
            JClass arrClass = classRef.getResolvedClass();
            OperandStack stack = frame.getOperandStack();
            int[] lenArr = popAndCheck(stack, dimensions);
            ArrayObject ref = createMultiDimensionArray(0, lenArr, arrClass);
            //add to heap
            JHeap.getInstance().addObj(ref);
            stack.pushObjectRef(ref);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * get the len of each dimension nad check it whether invalid
     *
     * @param stack      operand stack
     * @param dimensions dimension of array
     * @return array of length
     */
    private int[] popAndCheck(OperandStack stack, int dimensions) {
        int[] lenArr = new int[dimensions];
        for (int i = dimensions - 1; i >= 0; i--) {
            lenArr[i] = stack.popInt();
            if (lenArr[i] < 0) throw new NegativeArraySizeException();
        }
        return lenArr;
    }

    private ArrayObject createMultiDimensionArray(int index, int[] lenArray, JClass arrClass) {
        int len = lenArray[index];
        index++;
        ArrayObject arr = arrClass.newArrayObject(len);
        if (index <= lenArray.length - 1) {
            assert arr instanceof RefArrayObject;
            for (int i = 0; i < arr.getLen(); i++) {
                ((RefArrayObject) arr).getArray()[i] = createMultiDimensionArray(index, lenArray, arrClass.getComponentClass());
            }
        }
        return arr;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " index : " + index + "dimension : " + dimensions;
    }
}
