package sml;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static sml.Instruction.ERROR_PROGRAM_COUNTER_UPDATE;

// write a JavaDoc for the class

/**
 * Represents the label of the instruction
 * Label is used in jump instructions to change the order of execution
 *
 * @author Wenxin Liu
 */
public final class Labels {
	private final Map<String, Integer> labels = new HashMap<>();

	/**
	 * Adds a label with the associated address to the map.
	 *
	 * @param label the label
	 * @param address the address the label refers to
	 */
	public void addLabel(String label, int address) {
		Objects.requireNonNull(label);

		// Add a check that there are no label duplicates.
		if (labels.containsKey(label)) {
			throw new IllegalArgumentException("cannot have duplicate labels");
		}

		labels.put(label, address);
	}

	/**
	 * Returns the address associated with the label.
	 *
	 * @param label the label
	 * @return the address the label refers to
	 */
	public int getAddress(String label) {
		// Where can NullPointerException be thrown here?
		// This can happen when we try to get the value of a label that does not exist in the map

		// Add code to deal with non-existent labels.
		return labels.getOrDefault(label, ERROR_PROGRAM_COUNTER_UPDATE);
	}

	/**
	 * representation of this instance,
	 * in the form "[label -> address, label -> address, ..., label -> address]"
	 *
	 * @return the string representation of the labels map
	 */
	@Override
	public String toString() {
		return labels.entrySet().stream()
				.sorted(Map.Entry.comparingByKey())
				.map(e -> e.getKey() + " = " + e.getValue())
				.collect(Collectors.joining(", ", "[", "]")) ;
	}

	// Implement equals and hashCode (needed in class Machine).
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

		Labels other = (Labels) o;

		return this.labels.equals(other.labels);
	}

	@Override
	public int hashCode() {
		return labels.hashCode();
	}

	/**
	 * Removes the labels
	 */
	public void reset() {
		labels.clear();
	}
}
