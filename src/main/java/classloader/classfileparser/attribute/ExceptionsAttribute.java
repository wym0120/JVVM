package classloader.classfileparser.attribute;

import classloader.classfileparser.BuildUtil;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-19
 */
public class ExceptionsAttribute extends AttributeInfo {
    private int numberOfExceptions;
    private int[] exceptionIndexTable;

    public ExceptionsAttribute(BuildUtil buildUtil, int index, int length) {
        super(index, length);
        numberOfExceptions = buildUtil.getU2();
        this.exceptionIndexTable = new int[numberOfExceptions];
        for (int i = 0; i < this.exceptionIndexTable.length; i++) {
            exceptionIndexTable[i] = buildUtil.getU2();
        }
    }
}
