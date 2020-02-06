package classpath;

public class DirEntry extends Entry{
    public DirEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClass(String className) {
        return new byte[0];
    }
}
