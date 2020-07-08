package com.njuse.seecjvm.instructions.references;

import com.njuse.seecjvm.classloader.ClassLoader;
import com.njuse.seecjvm.classloader.classfilereader.classpath.EntryType;
import com.njuse.seecjvm.instructions.base.Instruction;
import com.njuse.seecjvm.memory.JHeap;
import com.njuse.seecjvm.memory.jclass.JClass;
import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.StackFrame;
import com.njuse.seecjvm.runtime.struct.ArrayObject;
import com.njuse.seecjvm.runtime.struct.array.ArrayType;

import java.nio.ByteBuffer;

public class NEWARRAY extends Instruction {
    private int atype;//uint8

    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int len = stack.popInt();
        if (len < 0) throw new NegativeArraySizeException();
        JClass arrClass = getPrimitiveArrayClass(atype, frame.getMethod().getClazz().getLoadEntryType());
        ArrayObject ref = arrClass.newArrayObject(len);
        //add to heap
        JHeap.getInstance().addObj(ref);
        stack.pushObjectRef(ref);
    }

    @Override
    public void fetchOperands(ByteBuffer reader) {
        atype = reader.get() & 0xFF;
    }

    private JClass getPrimitiveArrayClass(int atype, EntryType initiatingEntry) {
        try {
            ClassLoader loader = ClassLoader.getInstance();
            switch (atype) {
                case ArrayType.AT_BOOLEAN:
                    return loader.loadClass("[Z", initiatingEntry);
                case ArrayType.AT_BYTE:
                    return loader.loadClass("[B", initiatingEntry);
                case ArrayType.AT_CHAR:
                    return loader.loadClass("[C", initiatingEntry);
                case ArrayType.AT_SHORT:
                    return loader.loadClass("[S", initiatingEntry);
                case ArrayType.AT_INT:
                    return loader.loadClass("[I", initiatingEntry);
                case ArrayType.AT_LONG:
                    return loader.loadClass("[J", initiatingEntry);
                case ArrayType.AT_FLOAT:
                    return loader.loadClass("[F", initiatingEntry);
                case ArrayType.AT_DOUBLE:
                    return loader.loadClass("[D", initiatingEntry);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Invalid atype!");
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " array type : " + ArrayType.getName(atype);
    }
}
