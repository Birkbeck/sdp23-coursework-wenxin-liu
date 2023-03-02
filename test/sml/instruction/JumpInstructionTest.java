package sml.instruction;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import sml.Instruction;
import sml.Machine;
import sml.Registers;

import java.util.List;

import static sml.Registers.Register.*;

// TODO: add comments to test scenarios to better explain set up
// jnz s L	If the contents of register s is not zero, then make the statement labeled L the next statement to execute
public class JumpInstructionTest {
    private Machine machine;
    private Registers registers;
    private List<Instruction> program;

    @BeforeEach
    void setUp() {
        machine = new Machine(new Registers());
        registers = machine.getRegisters();
        program = machine.getProgram();
    }

    @AfterEach
    void tearDown() {
        machine = null;
        registers = null;
    }

    @Test
    void executeValid() {
        Instruction addInstruction = new AddInstruction("L1", EAX, EBX);

        registers.set(ECX, 5);
        Instruction jumpInstruction = new JumpInstruction("f3", ECX, "L1");

        program.add(addInstruction);
        program.add(jumpInstruction);

        int actualProgramCounter = jumpInstruction.execute(machine);
        int expectedProgramCounter = 0;

        Assertions.assertEquals(expectedProgramCounter, actualProgramCounter);
    }

    @Test
    void executeValidTwo() {
        Instruction subtractInstruction = new SubInstruction("L1", EAX, EBX);
        Instruction multiplyInstruction = new MulInstruction("L2", EAX, EBX);
        Instruction addInstruction = new AddInstruction("L3", EAX, EBX);

        registers.set(ECX, 5);
        Instruction jumpInstruction = new JumpInstruction("f3", ECX, "L3");

        program.add(subtractInstruction);
        program.add(multiplyInstruction);
        program.add(addInstruction);
        program.add(jumpInstruction);

        int actualProgramCounter = jumpInstruction.execute(machine);
        int expectedProgramCounter = 2;

        Assertions.assertEquals(expectedProgramCounter, actualProgramCounter);
    }


    @Test
    void executeInvalid() {
        registers.set(ECX, 5);
        Instruction jumpInstruction = new JumpInstruction("f3", ECX, "L1");

        program.add(jumpInstruction);

        int actualErrorProgramCounter = jumpInstruction.execute(machine);
        int expectedErrorProgramCounter = -2;

        Assertions.assertEquals(expectedErrorProgramCounter, actualErrorProgramCounter);
    }


    @Test
    void toStringValid() {
        registers.set(EBX, 10);
        Instruction instruction = new JumpInstruction("L1", EBX, "L2");

        Assertions.assertEquals("L1: jnz EBX L2", instruction.toString());
    }

    @Test
    void equalsValid() {
        Instruction firstJumpInstruction = new JumpInstruction("L1", EBX, "L2");
        Instruction secondJumpInstruction = new JumpInstruction("L1", EBX, "L2");

        Assertions.assertEquals(firstJumpInstruction, secondJumpInstruction);
    }

    @Test
    void hashCodeValid() {
        Instruction firstJumpInstruction = new JumpInstruction("L1", EBX, "L2");
        Instruction secondJumpInstruction = new JumpInstruction("L1", EBX, "L2");

        Assertions.assertEquals(firstJumpInstruction.hashCode(), secondJumpInstruction.hashCode());
    }
}
