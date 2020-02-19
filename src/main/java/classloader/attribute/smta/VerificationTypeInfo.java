package classloader.attribute.smta;

import classloader.BuildInfo;

/**
 * Description:
 *
 * @author xxz
 * Created on 2020-02-18
 */
public class VerificationTypeInfo {
    public static final int ITEM_Top = 0;
    public static final int ITEM_Integer = 1;
    public static final int ITEM_Float = 2;
    public static final int ITEM_Long = 4;
    public static final int ITEM_Double = 3;
    public static final int ITEM_Null = 5;
    public static final int ITEM_UninitializedThis = 6;
    public static final int ITEM_Object = 7;
    public static final int ITEM_Uninitialized = 8;

    private final int tag;

    public VerificationTypeInfo(int tag) {
        this.tag = tag;
    }

    static VerificationTypeInfo read(BuildInfo buildInfo){
        byte tag = (byte) buildInfo.getU1();
        switch (tag) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                return new VerificationTypeInfo(tag);
            case 7:
                return new ObjectVariableInfo(buildInfo);
            case 8:
                return new UninitializedVariableInfo(buildInfo);
            default:
                throw new UnsupportedOperationException("unrecognized verification_type_info tag " + tag);
        }
    }
}
