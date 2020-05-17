import classloader.classfilereader.ClassFileReader;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommandlineUtilTest {

    @DisplayName("Should read args correct no matter where arg is")
    @Test
    void testReadArgs() {
        String programInput = "arg0 -h arg1 -Xjre xxx.xxx arg2 -cp xxx.xxx";
        CommandlineUtil.parseInput(programInput.split(" "));
        String[] args = CommandlineUtil.readArgs();
        Arrays.stream(args).forEach(System.out::println);
        assertEquals(args.length, 3);
    }

    @DisplayName("Help option")
    @Disabled
    @Test
    void testHelp() {
        String programInput = "-h";
        CommandlineUtil.parseInput(programInput.split(" "));
        CommandlineUtil.handleOptions();
    }

    @DisplayName("set different path")
    @Test
    void testSetBootAndExt() {
        String programInput = "-cp 123.456 -Xjre abc.efg";
        CommandlineUtil.parseInput(programInput.split(" "));
        CommandlineUtil.handleOptions();
        assertEquals(String.join(File.separator, "abc.efg", "lib", "ext", "*"), ClassFileReader.getXjrePath());
        assertEquals(String.join(File.separator, "abc.efg", "lib", "*"), ClassFileReader.getXbootPath());
        assertEquals(String.join(File.separator, "123.456"), ClassFileReader.getUserClasspath());
    }

    @DisplayName("Print Version")
    @Disabled
    @Test
    void testVersion() {
        String programInput = "-v";
        CommandlineUtil.parseInput(programInput.split(" "));
        CommandlineUtil.handleOptions();
    }

    @DisplayName("Report invalid input")
    @Test
    void testInvalidInput() {
        String programInput = "arg0 -h arg1 -Xjre xxx.xxx arg2 -cp xxx.xxx -invalid";
        assertDoesNotThrow(() -> CommandlineUtil.parseInput(programInput.split(" ")));
    }
}
