import com.njuse.seecjvm.runtime.OperandStack;
import com.njuse.seecjvm.runtime.Vars;
import com.njuse.seecjvm.runtime.struct.NullObject;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;

public class RuntimeEnvTest {

    @DisplayName("operand stack handle normal options correctly")
    @Test
    void operandStackValid() {
        int maxSize = 8;
        OperandStack stack = new OperandStack(maxSize);
        stack.pushInt(1);
        stack.pushLong(21474836491264879L);
        assertEquals(stack.getTop(), 3);
        stack.pushDouble(1.5d);
        assertEquals(stack.getTop(), 5);
        stack.pushFloat(2.1f);
        stack.pushObjectRef(new NullObject());
        assertTrue(stack.popObjectRef().isNull());
        assertEquals(2.1f, stack.popFloat());
        assertEquals(1.5d, stack.popDouble());
        assertEquals(21474836491264879L, stack.popLong());
        assertEquals(1, stack.popInt());
    }

    @DisplayName("operand stack handle invalid options correctly")
    @Test
    void operandStackInvalid() {
        int maxSize = 6;
        OperandStack stack = new OperandStack(maxSize);
        for (int i = 0; i < maxSize / 3; i++) {
            stack.pushInt(i);
            stack.pushLong(i + 1);
        }
        assertThrows(StackOverflowError.class, () -> stack.pushInt(maxSize));
        for (int i = 0; i < maxSize; i++) {
            stack.popInt();
        }
        assertThrows(EmptyStackException.class, stack::popInt);
    }

    @DisplayName("vars handle normal options correctly")
    @Test
    void VarValid() {
        int maxSize = 5;
        Vars localVars = new Vars(maxSize);
        localVars.setDouble(0, 1.2d);
        localVars.setFloat(2, 1.5f);
        assertDoesNotThrow(() -> System.out.println(localVars.getDouble(0)));
        assertDoesNotThrow(() -> System.out.println(localVars.getFloat(2)));
    }

    @DisplayName("vars handle invalid options correctly")
    @Test
    void VarInvalid() {
        int maxSize = 5;
        Vars localVars = new Vars(maxSize);
        assertThrows(IndexOutOfBoundsException.class, () -> localVars.setDouble(-1, 1.2d));
        assertThrows(IndexOutOfBoundsException.class, () -> localVars.setFloat(-2, 1.5f));
        assertThrows(IndexOutOfBoundsException.class, () -> System.out.println(localVars.getDouble(4)));
        assertThrows(IndexOutOfBoundsException.class, () -> System.out.println(localVars.getFloat(5)));
    }
}
