package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

public class SubtractInstructionTest {
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
        Instruction instruction = new SubtractInstruction(null, EAX, EBX);

        instruction.execute(machine);

        Assertions.assertEquals(-1, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new SubtractInstruction(null, EAX, EBX);

        instruction.execute(machine);

        Assertions.assertEquals(-11, machine.getRegisters().get(EAX));
    }

    @Test
    void toStringValid() {
        registers.set(EBX, 10);
        registers.set(EDI, 2);
        Instruction instruction = new SubtractInstruction("L1", EBX, EDI);

        Assertions.assertEquals("L1: sub EBX EDI", instruction.toString());
    }

    @Test
    void equalsValid() {
        Instruction firstSubtractInstruction = new SubtractInstruction("L1", EBX, EDI);
        Instruction secondSubtractInstruction = new SubtractInstruction("L1", EBX, EDI);

        Assertions.assertEquals(firstSubtractInstruction, secondSubtractInstruction);
    }

    @Test
    void hashCodeValid() {
        Instruction firstSubtractInstruction = new SubtractInstruction("L1", EBX, EDI);
        Instruction secondSubtractInstruction = new SubtractInstruction("L1", EBX, EDI);

        Assertions.assertEquals(firstSubtractInstruction.hashCode(), secondSubtractInstruction.hashCode());
    }

    // TODO: handle when two instructions have the same label, exception should be thrown
}
