package darktestcase;

import minimal.TestUtil;

public class DarkHardObjectInst {
    private static final int FINAL_A = 2;
    private static final int FINAL_B = 4;
    public static int staticC = 8;
    public int D;

    public static void main(String[] args) {
        TestUtil.equalInt(2,FINAL_A);
        TestUtil.equalInt(4,FINAL_B);
        DarkHardObjectInst tmp = new DarkHardObjectInst();
        TestUtil.equalInt(8, DarkHardObjectInst.staticC);
        DarkHardObjectInst.staticC = 16;
        TestUtil.equalInt(16, DarkHardObjectInst.staticC);
        tmp = new DarkHardObjectInst();
        TestUtil.equalInt(0,tmp.D);
        tmp.D = 32;
        TestUtil.equalInt(32,tmp.D);
        ChildHardObjectInst child = new ChildHardObjectInst();
        int num = 0;
        if(child instanceof DarkHardObjectInst){
            num = 1;
        }
        TestUtil.equalInt(1,num);
        if(child instanceof InterfaceInst){
            num = 2;
        }
        TestUtil.equalInt(2,num);
        int[] array = new int[10];
        if(array instanceof Object){
            num = 3;
        }
        TestUtil.equalInt(3,num);
        if(array instanceof Cloneable){
            num = 4;
        }
        TestUtil.equalInt(4,num);
    }

    static class ChildHardObjectInst extends DarkHardObjectInst implements InterfaceInst{

    }

    interface InterfaceInst{}
}
