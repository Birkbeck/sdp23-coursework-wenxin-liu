package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// write a JavaDoc for the class

/**
 * Represents an instruction used to move the values of an integer into a register
 *
 * @author Wenxin Liu
 */

public class MovInstruction extends Instruction {
    private final RegisterName result;
    private final Integer source;

    public static final String OP_CODE = "mov";

    /**
     * Constructor: a move instruction with a label, a register name and an integer to be stored
     * (opcode must be an operation of the language)
     *
     * @param label optional label (can be null)
     * @param result register where the integer will be stored in
     * @param source integer to be stored
     */
    public MovInstruction(String label, RegisterName result, Integer source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    /**
     * Executes the move instruction in the given machine.
     *
     * @param m the machine the instruction runs on
     * @return the NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
     *          the instruction with the next address is to be executed
     */
    @Override
    public int execute(Machine m) {
        m.getRegisters().set(result, this.source);

        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + result + " " + source;
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

        MovInstruction other = (MovInstruction) o;

        return Objects.equals(this.label, other.label)
                && Objects.equals(this.opcode, other.opcode)
                && Objects.equals(this.source, other.source)
                && Objects.equals(this.result.name(), other.result.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, opcode, source, result);
    }
}
