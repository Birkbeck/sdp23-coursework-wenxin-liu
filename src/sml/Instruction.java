package sml;

// write a JavaDoc for the class

/**
 * Represents an abstract instruction.
 *
 * @author Wenxin Liu
 */
public abstract class Instruction {
	protected final String label;
	protected final String opcode;

	/**
	 * Constructor: an instruction with a label and an opcode
	 * (opcode must be an operation of the language)
	 *
	 * @param label optional label (can be null)
	 * @param opcode operation name
	 */
	public Instruction(String label, String opcode) {
		this.label = label;
		this.opcode = opcode;
	}

	/**
	 * Getter for label
	 *
	 * @return label the optional label (can be null)
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Getter for opcode
	 *
	 * @return opcode operation name
	 */
	public String getOpcode() {
		return opcode;
	}

	/**
	 * Value represents when instruction execution has been successful
	 */
	public static int NORMAL_PROGRAM_COUNTER_UPDATE = -1;

	/**
	 * Value represents when instruction execution has been unsuccessful
	 */
	public static int ERROR_PROGRAM_COUNTER_UPDATE = -2;

	/**
	 * Executes the instruction in the given machine.
	 *
	 * @param machine the machine the instruction runs on
	 * @return the new program counter (for jump instructions)
	 *          or NORMAL_PROGRAM_COUNTER_UPDATE to indicate that
	 *          the instruction with the next address is to be executed
	 */

	public abstract int execute(Machine machine);

	/**
	 * Returns a string value of label transformed to the format used in the implementation of toString(), for
	 * concrete classes that extend this class
	 * If the optional label is null, an empty string is returned
	 * If the optional label is not null, the label suffixed by ": " is returned
	 *
	 * @return the string representation of label according to the above conditions
	 */
	protected String getLabelString() {
		return (getLabel() == null) ? "" : getLabel() + ": ";
	}

	// What does abstract in the declaration below mean?
	// (Write a short explanation.)
	//
	// The purpose of using abstract methods is to provide a way to define a method signature or contract that is
	// required by the subclass without specifying the implementation. This allows the subclasses to provide their
	// own implementation for the abstract method. Here, the abstract base class Instruction does not provide an
	// implementation of toString(), this means that each concrete class that extends Instruction must provide their
	// own implementation of the toString() method.
	@Override
	public abstract String toString();


	// Make sure that subclasses also implement equals and hashCode (needed in class Machine)
	@Override
	public abstract boolean equals(Object o);

	@Override
	public abstract int hashCode();
}
