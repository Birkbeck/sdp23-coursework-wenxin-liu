package sml;

import org.junit.jupiter.api.Test;
import sml.instruction.AddInstruction;
import sml.instruction.SubInstruction;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class TranslatorTest {
    Translator translator;

    Field lineField;

    Method getInstructionMethod;

    void setUp(String opcode, String result, String source) {
        try {
            translator = new Translator("test.txt");

            lineField = translator.getClass().getDeclaredField("line");
            lineField.setAccessible(true);
            lineField.set(translator, String.format("%s %s %s", opcode, result, source));

            getInstructionMethod = translator.getClass().getDeclaredMethod("getInstruction", String.class);
            getInstructionMethod.setAccessible(true);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void canCreateAddInstructionWithReflection() {
        try {
            setUp("add", "EAX", "EBX");

            Instruction addInstruction = (Instruction) getInstructionMethod.invoke(translator, "f1");

            assertTrue(addInstruction instanceof AddInstruction);
            assertEquals("add", addInstruction.opcode);
        } catch (Exception e) {
            fail(e);
        }
    }


    @Test
    void canCreateSubtractionInstructionWithReflection() {
        try {
            setUp("sub", "EAX", "EBX");

            Instruction subtractionInstruction = (Instruction) getInstructionMethod.invoke(translator, "f1");

            assertTrue(subtractionInstruction instanceof SubInstruction);
            assertEquals("sub", subtractionInstruction.opcode);
        } catch (Exception e) {
            fail(e);
        }
    }
}