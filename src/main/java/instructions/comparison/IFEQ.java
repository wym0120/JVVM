package instructions.comparison;

public class IFEQ extends IFCOND {
    @Override
    public boolean condition(int value) {
        return value == 0;
    }
}
