package instructions.references;

import classloader.ClassLoader;
import classloader.classfilereader.classpath.EntryType;
import instructions.base.Instruction;
import memory.jclass.JClass;
import runtime.OperandStack;
import runtime.StackFrame;
import runtime.struct.JObject;
import runtime.struct.array.ArrayType;

import java.nio.ByteBuffer;

public class NEWARRAY extends Instruction {
    private int atype;//uint8
    @Override
    public void execute(StackFrame frame) {
        OperandStack stack = frame.getOperandStack();
        int len = stack.popInt();
        if(len<0)throw new NegativeArraySizeException();
        JClass arrClass = getPrimitiveArrayClass(atype,frame.getMethod().getClazz().getLoadEntryType());
        JObject arrObject = arrClass.newArrayObject(len);
        stack.pushObjectRef(arrObject);
    }

    @Override
    public void fetchOperands(ByteBuffer reader) {
        atype = reader.get() & 0xFF;
    }

    private JClass getPrimitiveArrayClass(int atype, EntryType initiatingEntry){
        try {
            ClassLoader loader = ClassLoader.getInstance();
            switch (atype){
                case ArrayType.AT_BOOLEAN: return loader.loadClass("[Z",initiatingEntry);
                case ArrayType.AT_BYTE: return loader.loadClass("[B",initiatingEntry);
                case ArrayType.AT_CHAR: return loader.loadClass("[C",initiatingEntry);
                case ArrayType.AT_SHORT: return loader.loadClass("[S",initiatingEntry);
                case ArrayType.AT_INT: return loader.loadClass("[I",initiatingEntry);
                case ArrayType.AT_LONG: return loader.loadClass("[J",initiatingEntry);
                case ArrayType.AT_FLOAT: return loader.loadClass("F",initiatingEntry);
                case ArrayType.AT_DOUBLE: return loader.loadClass("D",initiatingEntry);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Invalid atype!");
    }
}
