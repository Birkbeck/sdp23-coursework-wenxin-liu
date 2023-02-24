package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

class AddInstructionTest {
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
        Instruction instruction = new AddInstruction(null, EAX, EBX);

        instruction.execute(machine);

        Assertions.assertEquals(11, machine.getRegisters().get(EAX));
    }

    @Test
    void executeValidTwo() {
        registers.set(EAX, -5);
        registers.set(EBX, 6);
        Instruction instruction = new AddInstruction(null, EAX, EBX);

        instruction.execute(machine);

        Assertions.assertEquals(1, machine.getRegisters().get(EAX));
    }

    @Test
    void toStringValid() {
        registers.set(EBX, 10);
        registers.set(EDI, 2);
        Instruction instruction = new AddInstruction("L1", EBX, EDI);

        Assertions.assertEquals("L1: add EBX EDI", instruction.toString());
    }

    @Test
    void equalsValid() {
        Instruction firstAddInstruction = new AddInstruction("L1", EBX, EDI);
        Instruction secondAddInstruction = new AddInstruction("L1", EBX, EDI);

        Assertions.assertEquals(firstAddInstruction, secondAddInstruction);
    }

    @Test
    void hashCodeValid() {
        Instruction firstAddInstruction = new AddInstruction("L1", EBX, EDI);
        Instruction secondAddInstruction = new AddInstruction("L1", EBX, EDI);

        Assertions.assertEquals(firstAddInstruction.hashCode(), secondAddInstruction.hashCode());
    }
}