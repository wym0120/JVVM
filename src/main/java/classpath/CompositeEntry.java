package classpath;

public class CompositeEntry extends Entry{
    public CompositeEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClass(String className) {
        return new byte[0];
    }
}
