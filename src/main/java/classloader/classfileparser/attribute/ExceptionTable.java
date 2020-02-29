package classloader.classfileparser.attribute;

import lombok.Data;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
@Data
public class ExceptionTable {
    private int startPC;
    private int endPC;
    private int handlerPC;
    private int catchType;

    public ExceptionTable(int startPC, int endPC, int handlerPC, int catchType) {
        this.startPC = startPC;
        this.endPC = endPC;
        this.handlerPC = handlerPC;
        this.catchType = catchType;
    }
}
