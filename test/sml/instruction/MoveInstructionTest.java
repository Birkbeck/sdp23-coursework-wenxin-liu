package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import static sml.Registers.Register.*;

// mov r x	Store integer x in register r
public class MoveInstructionTest {
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
        registers.set(ECX, 5);
        Instruction instruction = new MoveInstruction("f3", ECX, 50);

        instruction.execute(machine);

        Assertions.assertEquals(50, machine.getRegisters().get(ECX));
    }

    @Test
    void executeValidTwo() {
        registers.set(ESP, -10);
        Instruction instruction = new MoveInstruction(null, ESP, -40);

        instruction.execute(machine);

        Assertions.assertEquals(-40, machine.getRegisters().get(ESP));
    }


    @Test
    void toStringValid() {
        registers.set(EBX, 10);
        Instruction instruction = new MoveInstruction("L1", EBX, 25);

        Assertions.assertEquals("L1: mov EBX 25", instruction.toString());
    }

    @Test
    void equalsValid() {
        Instruction firstMoveInstruction = new MoveInstruction("L1", EDI, 400);
        Instruction secondMoveInstruction = new MoveInstruction("L1", EDI, 400);

        Assertions.assertEquals(firstMoveInstruction, secondMoveInstruction);
    }

    @Test
    void hashCodeValid() {
        Instruction firstMoveInstruction = new MoveInstruction("L1", EBX, -340);
        Instruction secondMoveInstruction = new MoveInstruction("L1", EBX, -340);

        Assertions.assertEquals(firstMoveInstruction.hashCode(), secondMoveInstruction.hashCode());
    }
}
