package com.njuse.seecjvm.runtime.struct.array;

public class ArrayType {
    public static final int AT_BOOLEAN = 4;
    public static final int AT_CHAR = 5;
    public static final int AT_FLOAT = 6;
    public static final int AT_DOUBLE = 7;
    public static final int AT_BYTE = 8;
    public static final int AT_SHORT = 9;
    public static final int AT_INT = 10;
    public static final int AT_LONG = 11;

    public static String getName(int atype) {
        switch (atype) {
            case AT_BOOLEAN:
                return "boolean";
            case AT_CHAR:
                return "char";
            case AT_FLOAT:
                return "float";
            case AT_DOUBLE:
                return "double";
            case AT_BYTE:
                return "byte";
            case AT_SHORT:
                return "short";
            case AT_INT:
                return "int";
            case AT_LONG:
                return "long";
            default:
                return "";
        }
    }
}
