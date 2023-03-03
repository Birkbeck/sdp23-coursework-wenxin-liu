package sml;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import sml.instruction.*;

public class InstructionFactoryTest {
    InstructionFactory instructionFactory = InstructionFactory.getInstance();
    InstructionProvider instructionProvider = instructionFactory.getInstructionProvider();

    @Test
    void canCreateAddInstruction() {
        Instruction addInstruction = instructionProvider.getInstruction("", "add", "EAX", "EBX");

        assertTrue(addInstruction instanceof AddInstruction);
    }

    @Test
    void canCreateSubtractInstruction() {
        Instruction subtractInstruction = instructionProvider.getInstruction("f1", "sub", "EAX", "EBX");

        assertTrue(subtractInstruction instanceof SubInstruction);
    }
}
