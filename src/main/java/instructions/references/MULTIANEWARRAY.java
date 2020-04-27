package instructions.references;

import instructions.base.Instruction;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;
import memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.ArrayObject;
import runtime.struct.array.RefArrayObject;

import java.nio.ByteBuffer;

public class MULTIANEWARRAY extends Instruction {
    private int index;//uint16
    private int dimensions;//uint8

    @Override
    public void execute(StackFrame frame) {
        RuntimeConstantPool runtimeConstantPool = frame.getMethod().getClazz().getRuntimeConstantPool();
        ClassRef ref = (ClassRef) runtimeConstantPool.getConstant(index);
        try {
            JClass arrClass = ref.getResolvedClass();
            OperandStack stack = frame.getOperandStack();
            int[] lenArr = popAndCheck(stack, dimensions);
            ArrayObject arr = createMultiDimensionArray(0, lenArr, arrClass);
            stack.pushObjectRef(arr);
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
            //todo: test whether arr is a ref array
            for (int i = 0; i < arr.getLen(); i++) {
                ((RefArrayObject) arr).getArray()[i] = createMultiDimensionArray(index, lenArray, arrClass.getComponentClass());
            }
        }
        return arr;
    }

    @Override
    public void fetchOperands(ByteBuffer reader) {
        index = reader.getShort() & 0xFFFF;
        dimensions = reader.get() & 0xFF;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " index : " + index + "dimension : " + dimensions;
    }
}
