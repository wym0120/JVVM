package vo;


import java.util.ArrayList;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-04-14
 */
public class FrameVO {
    //operandStack[0] is the bottom of this stack
    private ArrayList<String> operandStack;

    private ArrayList<String> localVars;

    //index into the list string
    private int nextPC;

    private ArrayList<String> code;
}
