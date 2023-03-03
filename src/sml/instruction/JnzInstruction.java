package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.List;
import java.util.Objects;

// write a JavaDoc for the class

/**
 * Represents an instruction used to jump to another instruction specified by
 * the jump to label
 *
 * @author Wenxin Liu
 */

// jnz s L
public class JnzInstruction extends Instruction {
    private final RegisterName result;
    private final String jumpToLabel;

    public static final String OP_CODE = "jnz";

    /**
     * Constructor: a jump instruction with a label, a register name and another label for the instruction
     * to jump to
     * (opcode must be an operation of the language)
     *
     * @param label optional label (can be null)
     * @param result register a value if greater than jump, will result in an attempted jump
     * @param jumpToLabel label of the instruction to jump to
     */
    public JnzInstruction(String label, RegisterName result, String jumpToLabel) {
        super(label, OP_CODE);
        this.result = result;
        this.jumpToLabel = jumpToLabel;
    }

    /**
     * If the contents of register result is not zero
     * then make the statement labeled jumpToLabel the next statement to execute
     *
     * @param m the machine the instruction runs on
     * @return the new program counter for a successful jump
     *         or ERROR_PROGRAM_COUNTER_UPDATE if jump is unsuccessful in the case there
     *         is no instruction matching the jumpToLabel value
     */
    @Override
    public int execute(Machine m) {
        if (m.getRegisters().get(result) > 0) {
            List<Instruction> program = m.getProgram();

            for (int newProgramCounter = 0; newProgramCounter < program.size(); newProgramCounter++) {
                if (program.get(newProgramCounter).getLabel().equals(jumpToLabel)) {
                    return newProgramCounter;
                }
            }
        }

        return ERROR_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + jumpToLabel;
    }

    @Override
    public boolean equals(Object o) {
        // self check
        if (o == this) {
            return true;
        }

        // null check
        if (o == null) {
            return false;
        }

        // type check and cast
        if (getClass() != o.getClass()) {
            return false;
        }

        JnzInstruction other = (JnzInstruction) o;

        return Objects.equals(this.label, other.label)
                && Objects.equals(this.opcode, other.opcode)
                && Objects.equals(this.jumpToLabel, other.jumpToLabel)
                && Objects.equals(this.result.name(), other.result.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, opcode, jumpToLabel, result);
    }
}
