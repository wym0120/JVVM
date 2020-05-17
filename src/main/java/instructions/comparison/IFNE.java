package instructions.comparison;

public class IFNE extends IFCOND {
    @Override
    public boolean condition(int value) {
        return value != 0;
    }
}
