package instructions.comparison;

public class IFLE extends IFCOND {
    @Override
    public boolean condition(int value) {
        return value <= 0;
    }
}
