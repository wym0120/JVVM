package memory.jclass.runtimeConstantPool;

import classloader.classfileparser.constantpool.ConstantPool;
import classloader.classfileparser.constantpool.info.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import memory.jclass.JClass;
import memory.jclass.runtimeConstantPool.constant.Constant;
import memory.jclass.runtimeConstantPool.constant.ref.ClassRef;
import memory.jclass.runtimeConstantPool.constant.ref.FieldRef;
import memory.jclass.runtimeConstantPool.constant.ref.InterfaceMethodRef;
import memory.jclass.runtimeConstantPool.constant.ref.MethodRef;
import memory.jclass.runtimeConstantPool.constant.wrapper.DoubleWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.FloatWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.IntWrapper;
import memory.jclass.runtimeConstantPool.constant.wrapper.LongWrapper;

import static classloader.classfileparser.constantpool.info.ConstantPoolInfo.*;

@Setter
@Getter
public class RuntimeConstantPool {
    private JClass clazz;
    private Constant[] constants;

    public RuntimeConstantPool(ConstantPool constantPool, JClass clazz) {
        this.clazz = clazz;
        ConstantPoolInfo[] infos = constantPool.getInfos();
        int length = infos.length - 1;//real num of constant pool item is length-1
        constants = new Constant[length];
        for (int i = 0; i < length; i++) {
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
                    ClassInfo classInfo = (ClassInfo) info;
                    constants[i] = new ClassRef(this, classInfo);
                    break;

                case FIELD_REF:
                    FieldrefInfo fieldrefInfo = (FieldrefInfo) info;
                    constants[i] = new FieldRef(this, fieldrefInfo);
                    break;

                case METHOD_REF:
                    MethodrefInfo methodrefInfo = (MethodrefInfo) info;
                    constants[i] = new MethodRef(this, methodrefInfo);
                    break;

                case INTERFACE_METHOD_REF:
                    InterfaceMethodrefInfo interfaceMethodrefInfo = (InterfaceMethodrefInfo) info;
                    constants[i] = new InterfaceMethodRef(this, interfaceMethodrefInfo);
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
