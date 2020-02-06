package classpath;

public class ZipEntry extends Entry{
    public ZipEntry(String classpath) {
        super(classpath);
    }

    @Override
    public byte[] readClass(String className) {
        return new byte[0];
    }
}
