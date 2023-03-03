package sml;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import sml.instruction.*;

public class InstructionFactoryTest {
    InstructionFactory instructionFactory = InstructionFactory.getInstance();
    InstructionProvider instructionProvider = instructionFactory.getInstructionProvider();

    @Test
    void canCreateAddInstruction() {
        Instruction addInstruction = instructionProvider.getInstruction(null, "add", "EAX", "EBX");

        assertTrue(addInstruction instanceof AddInstruction);
    }

    @Test
    void canCreateSubtractInstruction() {
        Instruction subtractInstruction = instructionProvider.getInstruction("f1", "sub", "EAX", "EBX");

        assertTrue(subtractInstruction instanceof SubInstruction);
    }

    @Test
    void canCreateMultiplyInstruction() {
        Instruction multiplyInstruction = instructionProvider.getInstruction(null, "mul", "EAX", "EBX");

        assertTrue(multiplyInstruction instanceof MulInstruction);
        assertEquals("mul EAX EBX", multiplyInstruction.toString());
    }

    @Test
    void canCreateDivideInstruction() {
        Instruction divideInstruction = instructionProvider.getInstruction("L2", "div", "EAX", "EBX");

        assertTrue(divideInstruction instanceof DivInstruction);
        assertEquals("L2: div EAX EBX", divideInstruction.toString());
    }

    @Test
    void canCreateOutInstruction() {
        Instruction outInstruction = instructionProvider.getInstruction(null, "out", "EAX", null);

        assertTrue(outInstruction instanceof OutInstruction);
        assertEquals("out EAX", outInstruction.toString());
    }

    @Test
    void canCreateMoveInstruction() {
        Instruction moveInstruction = instructionProvider.getInstruction("L2", "mov", "EAX", "2");

        assertTrue(moveInstruction instanceof MovInstruction);
        assertEquals("L2: mov EAX 2", moveInstruction.toString());
    }

    @Test
    void canCreateJumpInstruction() {
        Instruction jumpInstruction = instructionProvider.getInstruction("L1", "jnz", "EAX", "L3");

        assertTrue(jumpInstruction instanceof JnzInstruction);
        assertEquals("L1: jnz EAX L3", jumpInstruction.toString());
    }
}
