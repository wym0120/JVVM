package memory.jclass;

import lombok.Data;

@Data
public abstract class ClassMember {
    public short accessFlags;
    public String name;
    public String descriptor;
    public JClass clazz;

    public boolean isPublic() {
        return 0 != (accessFlags & AccessFlags.ACC_PUBLIC);
    }

    public boolean isPrivate() {
        return 0 != (accessFlags & AccessFlags.ACC_PRIVATE);
    }

    public boolean isLongOrDouble() {
        return descriptor.equals("J") || descriptor.equals("D");
    }

    public boolean isStatic() {
        return 0 != (accessFlags & AccessFlags.ACC_STATIC);
    }

    public boolean isNative() {
        return 0 != (accessFlags & AccessFlags.ACC_NATIVE);
    }

    public boolean isFinal() {
        return 0 != (accessFlags & AccessFlags.ACC_FINAL);
    }

    public boolean isProtected() {
        return 0 != (accessFlags & AccessFlags.ACC_PROTECTED);
    }

    public boolean isAccessibleTo(JClass D) {
        if (isPublic()) return true;
        if (isProtected() && (clazz == D || D.getSuperClass() == clazz || clazz.getPackageName().equals(D.getPackageName())))
            return true;
        if ((isPublic() || isPrivate()) && clazz.getPackageName().equals(D.getPackageName())) return true;
        if (isPrivate() && clazz == D) return true;
        return false;
    }
}
