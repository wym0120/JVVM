package classloader.classfileparser.attribute;

import classloader.classfileparser.BuildUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BootstrapMethodInfo {
    private int bootstrapMethodRef;
    private int numBootstrapArguments;
    private int[] bootstrapArguments;

    public BootstrapMethodInfo(BuildUtil buildUtil) {
        bootstrapMethodRef = buildUtil.getU2();
        numBootstrapArguments = buildUtil.getU2();
        bootstrapArguments = new int[numBootstrapArguments];
        for (int i = 0; i < bootstrapArguments.length; i++) {
            bootstrapArguments[i] = buildUtil.getU2();
        }
    }
}
