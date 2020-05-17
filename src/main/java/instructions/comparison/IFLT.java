package instructions.comparison;

public class IFLT extends IFCOND {
    @Override
    public boolean condition(int value) {
        return value < 0;
    }
}
