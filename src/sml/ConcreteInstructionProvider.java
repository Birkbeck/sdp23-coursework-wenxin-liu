package sml;

import sml.instruction.*;

public class ConcreteInstructionProvider implements InstructionProvider {
    @Override
    public Instruction getInstruction(String label, String opcode, String result, String source) {
        switch (opcode) {
            case "add" -> {
                return getAddInstruction(label, Registers.Register.valueOf(result), Registers.Register.valueOf(source));
            }

            case "mul" -> {
                return getMultiplyInstruction(label, Registers.Register.valueOf(result), Registers.Register.valueOf(source));
            }

            case "sub" -> {
                return getSubtractionInstruction(label, Registers.Register.valueOf(result), Registers.Register.valueOf(source));
            }

            case "div" -> {
                return getDivideInstruction(label, Registers.Register.valueOf(result), Registers.Register.valueOf(source));
            }

            case "out" -> {
                return getOutInstruction(label, Registers.Register.valueOf(result));
            }

            case "mov" -> {
                return getMoveInstruction(label, Registers.Register.valueOf(result), Integer.valueOf(source));
            }

            case "jnz" -> {
                return getJumpInstruction(label, Registers.Register.valueOf(result), source);
            }
            default -> {
                System.out.println("Unknown instruction: " + opcode);
            }
        }
        return null;
    }

    private Instruction getAddInstruction(String label, RegisterName result, RegisterName source) {
        return new AddInstruction(label, result, source);
    }

    private Instruction getSubtractionInstruction(String label, RegisterName result, RegisterName source) {
        return new SubInstruction(label, result, source);
    }

    private Instruction getMultiplyInstruction(String label, RegisterName result, RegisterName source) {
        return new MulInstruction(label, result, source);
    }

    private Instruction getDivideInstruction(String label, RegisterName result, RegisterName source) {
        return new DivInstruction(label, result, source);
    }

    private Instruction getOutInstruction(String label, RegisterName result) {
        return new OutInstruction(label, result);
    }

    private Instruction getMoveInstruction(String label, RegisterName result, Integer integer) {
        return new MovInstruction(label, result, integer);
    }

    private Instruction getJumpInstruction(String label, RegisterName result, String jumpToLabel) {
        return new JnzInstruction(label, result, jumpToLabel);
    }
}
