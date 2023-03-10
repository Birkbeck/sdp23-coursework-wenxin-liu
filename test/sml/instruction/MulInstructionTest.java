package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class MulInstructionTest {
    private Machine machine;
    private Registers registers;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        //...
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        registers.set(EAX, 5);
        registers.set(EBX, 6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, -6);
        Instruction instruction = new MulInstruction(null, EAX, EBX);
        instruction.execute(machine);
        Assertions.assertEquals(30, machine.getRegisters().get(EAX));
    }

    @Test
    void toStringValid() {
        registers.set(EAX, 2);
        registers.set(EBX, -3);
        Instruction instruction = new MulInstruction("f1", EAX, ECX);
        Assertions.assertEquals("f1: mul EAX ECX", instruction.toString());
    }

    @Test
    void equalsValid() {
        Instruction firstMultiplyInstruction = new MulInstruction(null, EBX, EBP);
        Instruction secondMultiplyInstruction = new MulInstruction(null, EBX, EBP);

        Assertions.assertEquals(firstMultiplyInstruction, secondMultiplyInstruction);
    }

    @Test
    void hashCodeValid() {
        Instruction firstMultiplyInstruction = new MulInstruction(null, EDI, ESI);
        Instruction secondMultiplyInstruction = new MulInstruction(null, EDI, ESI);

        Assertions.assertEquals(firstMultiplyInstruction.hashCode(), secondMultiplyInstruction.hashCode());
    }
}
