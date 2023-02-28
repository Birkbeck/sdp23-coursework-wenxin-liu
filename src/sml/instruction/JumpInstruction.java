package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.List;
import java.util.Objects;

// TODO: write a JavaDoc for the class

/**
 * @author
 */

// jnz s L	If the contents of register s is not zero, then make the statement labeled L the next statement to execute
public class JumpInstruction extends Instruction {
    private final RegisterName result;
    private final String jumpToLabel;

    public static final String OP_CODE = "jnz";

    public JumpInstruction(String label, RegisterName result, String jumpToLabel) {
        super(label, OP_CODE);
        this.result = result;
        this.jumpToLabel = jumpToLabel;
    }

    // TODO: Can we jump to our own instruction? If the label references the same instruction itself, fail
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

        JumpInstruction other = (JumpInstruction) o;

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
