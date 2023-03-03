package sml.instruction;

import sml.Instruction;
import sml.Machine;
import sml.RegisterName;

import java.util.Objects;

// write a JavaDoc for the class

/**
 * Represents an instruction used to add the values of two registers together
 *
 * @author Wenxin Liu
 */

public class AddInstruction extends Instruction {
    private final RegisterName result;
    private final RegisterName source;

    public static final String OP_CODE = "add";

    /**
     * Constructor: an add instruction with a label and two register names
     * (opcode must be an operation of the language)
     *
     * @param label optional label (can be null)
     * @param result register containing a value, and where the new sum after addition is saved
     * @param source register containing a value
     */
    public AddInstruction(String label, RegisterName result, RegisterName source) {
        super(label, OP_CODE);
        this.result = result;
        this.source = source;
    }

    /**
     * Executes the add instruction in the given machine.
     *
     * @param m the machine the instruction runs on
     * @return the NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
     *          the instruction with the next address is to be executed
     */
    @Override
    public int execute(Machine m) {
        int value1 = m.getRegisters().get(result);
        int value2 = m.getRegisters().get(source);
        m.getRegisters().set(result, value1 + value2);
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

        AddInstruction other = (AddInstruction) o;

        return Objects.equals(this.label, other.label)
                && Objects.equals(this.opcode, other.opcode)
                && Objects.equals(this.source.name(), other.source.name())
                && Objects.equals(this.result.name(), other.result.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, opcode, source, result);
    }
}
