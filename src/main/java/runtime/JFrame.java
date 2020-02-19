package runtime;

import lombok.Data;

@Data
public class JFrame {
    private OperandStack operandStack;
    private LocalVars localVars;
    private JFrame lower;

    /**
     * 这两个值是编译器预先计算好的，储存在method_info的code属性中
     *
     * @param maxStackSize 最大栈深度
     * @param maxVarSize   最大局部变量表槽位
     */
    public JFrame(int maxStackSize, int maxVarSize){
        operandStack = new OperandStack(maxStackSize);
        localVars = new LocalVars(maxVarSize);
    }
}
