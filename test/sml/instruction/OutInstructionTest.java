package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static sml.Registers.Register.*;

public class OutInstructionTest {
    private Machine machine;
    private Registers registers;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();


    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();

        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;

        System.setOut(standardOut);
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        Instruction instruction = new OutInstruction(null, EAX);

        instruction.execute(machine);

        Assertions.assertEquals("5", outputStreamCaptor.toString().trim());
    }

    @Test
    void executeValidTwo() {
        registers.set(EBP, -250);
        Instruction instruction = new OutInstruction(null, EBP);

        instruction.execute(machine);

        Assertions.assertEquals("-250", outputStreamCaptor.toString().trim());
    }

    @Test
    void toStringValid() {
        registers.set(EBX, 10);
        Instruction instruction = new OutInstruction("L1", EBX);

        Assertions.assertEquals("L1: out EBX", instruction.toString());
    }

    @Test
    void equalsValid() {
        Instruction firstOutInstruction = new OutInstruction("f3", ESI);
        Instruction secondOutInstruction = new OutInstruction("f3", ESI);

        Assertions.assertEquals(firstOutInstruction, secondOutInstruction);
    }

    @Test
    void hashCodeValid() {
        Instruction firstOutInstruction = new OutInstruction("f3", ESI);
        Instruction secondOutInstruction = new OutInstruction("f3", ESI);

        Assertions.assertEquals(firstOutInstruction.hashCode(), secondOutInstruction.hashCode());
    }

    // TODO: handle when two instructions have the same label, exception should be thrown
}
