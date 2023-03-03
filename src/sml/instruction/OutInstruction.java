package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// write a JavaDoc for the class

/**
 * Represents an instruction used to print the values of a register to standard out
 *
 * @author Wenxin Liu
 */

public class OutInstruction extends Instruction {
    private final RegisterName source;

    public static final String OP_CODE = "out";

    /**
     * Constructor: an out instruction with a label and a register name
     * (opcode must be an operation of the language)
     *
     * @param label optional label (can be null)
     * @param source register containing a value to be printed to standard out
     */
    public OutInstruction(String label, RegisterName source) {
        super(label, OP_CODE);
        this.source = source;
    }

    /**
     * Executes the out instruction in the given machine.
     *
     * @param m the machine the instruction runs on
     * @return the NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
     *          the instruction with the next address is to be executed
     */
    @Override
    public int execute(Machine m) {
        int value = m.getRegisters().get(source);
        System.out.println(value);

        return NORMAL_PROGRAM_COUNTER_UPDATE;
    }

    @Override
    public String toString() {
        return getLabelString() + getOpcode() + " " + source;
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

        OutInstruction other = (OutInstruction) o;

        return Objects.equals(this.label, other.label)
                && Objects.equals(this.opcode, other.opcode)
                && Objects.equals(this.source.name(), other.source.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, opcode, source);
    }
}
