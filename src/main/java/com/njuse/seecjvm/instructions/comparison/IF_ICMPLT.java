package com.njuse.seecjvm.instructions.comparison;

public class IF_ICMPLT extends IF_ICMPCOND {
    @Override
    protected boolean condition(int v1, int v2) {
        return v1 < v2;
    }
}
