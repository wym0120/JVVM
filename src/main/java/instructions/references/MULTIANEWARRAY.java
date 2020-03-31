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
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

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
            Integer[] lenArr = popAndCheck(stack, dimensions);
            Queue<Integer> lenQueue = new LinkedList<>(Arrays.asList(lenArr));
            ArrayObject arr = createMultiDimensionArray(lenQueue, arrClass);
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
    private Integer[] popAndCheck(OperandStack stack, int dimensions) {
        Integer[] lenArr = new Integer[dimensions];
        for (int i = dimensions - 1; i >= 0; i--) {
            lenArr[i] = stack.popInt();
            if (lenArr[i] < 0) throw new NegativeArraySizeException();
        }
        return lenArr;
    }

    private ArrayObject createMultiDimensionArray(Queue<Integer> lenQueue, JClass arrClass) {
        int len = lenQueue.poll();
        ArrayObject arr = arrClass.newArrayObject(len);
        if (!(lenQueue.peek() == null)) {
            //todo: test whether arr is a ref array
            for (int i = 0; i < arr.getLen(); i++) {
                ((RefArrayObject) arr).getArray()[i] = createMultiDimensionArray(lenQueue, arrClass.getComponentClass());
            }
        }
        return arr;
    }

    @Override
    public void fetchOperands(ByteBuffer reader) {
        index = reader.getShort() & 0xFFFF;
        dimensions = reader.get() & 0xFF;
    }
}
