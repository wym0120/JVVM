package memory.jclass.runtimeConstantPool.constant.ref;

import classloader.classfileparser.constantpool.info.InterfaceMethodrefInfo;
import lombok.Data;
import memory.jclass.JClass;
import memory.jclass.Method;
import memory.jclass.runtimeConstantPool.RuntimeConstantPool;

import java.util.Arrays;
import java.util.Optional;
import java.util.Stack;

@Data
public class InterfaceMethodRef extends MemberRef {
    private Method method;

    public InterfaceMethodRef(RuntimeConstantPool runtimeConstantPool, InterfaceMethodrefInfo interfaceMethodrefInfo) {
        super(runtimeConstantPool, interfaceMethodrefInfo);
        //method
    }

    public Method resolveInterfaceMethodRef(JClass clazz) {
        resolve(clazz);
        return method;
    }

    public Method resolveInterfaceMethodRef() {
        try {
            resolveClassRef();
            resolve(clazz);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return method;
    }

    public void resolve(JClass clazz) {
        assert clazz != null;

        Optional<Method> optionalMethod = Optional.empty();

        for (JClass currentClazz = clazz;
             currentClazz != null;
             currentClazz = currentClazz.getSuperClass()) {

            optionalMethod = currentClazz.resolveMethod(name, descriptor);

            if (optionalMethod.isPresent()) {
                method = optionalMethod.get();
                return;
            }

        }

        //if not found in class hierarchy
        JClass[] ifs = clazz.getInterfaces();
        Stack<JClass> interfaces = new Stack<>();
        interfaces.addAll(Arrays.asList(ifs));
        while (!interfaces.isEmpty()) {
            JClass clz = interfaces.pop();
            optionalMethod = clz.resolveMethod(name, descriptor);
            if (optionalMethod.isPresent()) {
                method = optionalMethod.get();
                return;
            }
            interfaces.addAll(Arrays.asList(clz.getInterfaces()));
        }
    }
}
