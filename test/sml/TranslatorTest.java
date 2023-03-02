package sml;

import org.junit.jupiter.api.Test;
import sml.instruction.*;

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

            // e.g. "add EAX EBX"
            lineField.set(translator, String.format("%s %s %s", opcode, result, source));

            getInstructionMethod = translator.getClass().getDeclaredMethod("getInstruction", String.class);
            getInstructionMethod.setAccessible(true);
        } catch (Exception e) {
            fail(e);
        }
    }

    void setUp(String opcode, String source) {
        try {
            translator = new Translator("test.txt");

            lineField = translator.getClass().getDeclaredField("line");
            lineField.setAccessible(true);

            // e.g. "out EAX"
            lineField.set(translator, String.format("%s %s", opcode, source));

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
            assertEquals("f1: add EAX EBX", addInstruction.toString());
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
            assertEquals("f1: sub EAX EBX", subtractionInstruction.toString());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void canCreateMultiplicationInstructionWithReflection() {
        try {
            setUp("mul", "EAX", "EBX");

            Instruction multiplyInstruction = (Instruction) getInstructionMethod.invoke(translator, "f1");

            assertTrue(multiplyInstruction instanceof MulInstruction);
            assertEquals("mul", multiplyInstruction.opcode);
            assertEquals("f1: mul EAX EBX", multiplyInstruction.toString());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void canCreateDivisionInstructionWithReflection() {
        try {
            setUp("div", "EAX", "EBX");

            Instruction divisionInstruction = (Instruction) getInstructionMethod.invoke(translator, "f1");

            assertTrue(divisionInstruction instanceof DivInstruction);
            assertEquals("div", divisionInstruction.opcode);
            assertEquals("f1: div EAX EBX", divisionInstruction.toString());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void canCreateOutInstructionWithReflection() {
        try {
            setUp("out", "EAX");

            Instruction outInstruction = (Instruction) getInstructionMethod.invoke(translator, "f1");

            assertTrue(outInstruction instanceof OutInstruction);
            assertEquals("out", outInstruction.opcode);
            assertEquals("f1: out EAX", outInstruction.toString());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void canCreateMoveInstructionWithReflection() {
        try {
            setUp("mov", "EAX", "10");

            Instruction moveInstruction = (Instruction) getInstructionMethod.invoke(translator, "f1");

            assertTrue(moveInstruction instanceof MovInstruction);
            assertEquals("mov", moveInstruction.opcode);
            assertEquals("f1: mov EAX 10", moveInstruction.toString());
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void canCreateJumpInstructionWithReflection() {
        try {
            setUp("jnz", "EAX", "f2");

            Instruction jnzInstruction = (Instruction) getInstructionMethod.invoke(translator, "f1");

            assertTrue(jnzInstruction instanceof JnzInstruction);
            assertEquals("jnz", jnzInstruction.opcode);
            assertEquals("f1: jnz EAX f2", jnzInstruction.toString());
        } catch (Exception e) {
            fail(e);
        }
    }
}