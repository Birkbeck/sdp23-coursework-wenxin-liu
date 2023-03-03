package sml;

public interface InstructionProvider {
    Instruction getInstruction(String label, String opcode, String result, String source);
}
