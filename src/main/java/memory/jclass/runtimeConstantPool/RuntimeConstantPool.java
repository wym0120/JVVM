package memory.jclass.runtimeConstantPool;

import classloader.constantpool.ConstantPool;
import classloader.constantpool.info.*;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.constant.Constant;
import memory.jclass.runtimeConstantPool.constant.wrapper.DoubleWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.FloatWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.IntWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.LongWrapper;

import static classloader.constantpool.info.ConstantPoolInfo.*;

public class RuntimeConstantPool {
    private JClass clazz;
    private Constant[] constants;

    public RuntimeConstantPool(ConstantPool constantPool, JClass clazz) {
        this.clazz = clazz;
        ConstantPoolInfo[] infos = constantPool.getInfos();
        constants = new Constant[infos.length];
        for (int i = 0; i < infos.length; i++) {
            ConstantPoolInfo info = infos[i];
            switch (info.getTag()) {
                case INTEGER:
                    IntegerInfo intInfo = (IntegerInfo) info;
                    constants[i] = new IntWrapper(intInfo.getValue());
                    break;

                case FLOAT:
                    FloatInfo floatInfo = (FloatInfo) info;
                    constants[i] = new FloatWrapper(floatInfo.getValue());
                    break;

                case LONG:
                    LongInfo longInfo = (LongInfo) info;
                    constants[i] = new LongWrapper(longInfo.getValue());
                    //long value takes two slots
                    i++;
                    break;

                case DOUBLE:
                    DoubleInfo doubleInfo = (DoubleInfo) info;
                    constants[i] = new DoubleWrapper(doubleInfo.getValue());
                    //double value takes two slots
                    i++;
                    break;

                case CLASS:
                    break;

                case FIELD_REF:
                    break;

                case METHOD_REF:
                    break;

                case INTERFACE_METHOD_REF:
                    break;

                default:
                    break;
            }
        }

    }

    public Constant getConstant(int index) {
        return constants[index];
        //todo:check whether need to handle exception
    }
}
