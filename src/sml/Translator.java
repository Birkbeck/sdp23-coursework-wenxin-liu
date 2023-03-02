package sml;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class ....
 * <p>
 * The translator of a <b>S</b><b>M</b>al<b>L</b> program.
 *
 * @author ...
 */
public final class Translator {

    private final String fileName; // source file of SML code

    // line contains the characters in the current line that's not been processed yet
    private String line = "";

    public Translator(String fileName) {
        this.fileName = fileName;
    }

    // translate the small program in the file into lab (the labels) and
    // prog (the program)
    // return "no errors were detected"

    public void readAndTranslate(Labels labels, List<Instruction> program) throws IOException {
        try (var sc = new Scanner(new File(fileName), StandardCharsets.UTF_8)) {
            labels.reset();
            program.clear();

            // Each iteration processes line and reads the next input line into "line"
            while (sc.hasNextLine()) {
                line = sc.nextLine();
                String label = getLabel();

                Instruction instruction = getInstruction(label);
                if (instruction != null) {
                    if (label != null)
                        labels.addLabel(label, program.size());
                    program.add(instruction);
                }
            }
        }
    }

    /**
     * Translates the current line into an instruction with the given label
     *
     * @param label the instruction label
     * @return the new instruction
     * <p>
     * The input line should consist of a single SML instruction,
     * with its label already removed.
     */
    private Instruction getInstruction(String label) {
        if (line.isEmpty())
            return null;

        String opcode = scan();
//        switch (opcode) {
//            case AddInstruction.OP_CODE -> {
//                String r = scan();
//                String s = scan();
//                return new AddInstruction(label, Register.valueOf(r), Register.valueOf(s));
//            }
//
//            // add code for all other types of instructions
//            case MultiplyInstruction.OP_CODE -> {
//                String resultRegister = scan();
//                String sourceRegister = scan();
//                return new MultiplyInstruction(label, Register.valueOf(resultRegister), Register.valueOf(sourceRegister));
//            }
//
//            case SubtractInstruction.OP_CODE -> {
//                String resultRegister = scan();
//                String sourceRegister = scan();
//                return new SubtractInstruction(label, Register.valueOf(resultRegister), Register.valueOf(sourceRegister));
//            }
//
//            case DivideInstruction.OP_CODE -> {
//                String resultRegister = scan();
//                String sourceRegister = scan();
//                return new DivideInstruction(label, Register.valueOf(resultRegister), Register.valueOf(sourceRegister));
//            }
//
//            case OutInstruction.OP_CODE -> {
//                String targetRegister = scan();
//                return new OutInstruction(label, Register.valueOf(targetRegister));
//            }
//
//            case MoveInstruction.OP_CODE -> {
//                String targetRegister = scan();
//                String sourceRegister = scan();
//                return new MoveInstruction(label, Register.valueOf(targetRegister), Integer.valueOf(sourceRegister));
//            }
//
//            case JumpInstruction.OP_CODE -> {
//                String register = scan();
//                String jumpToLabel = scan();
//                return new JumpInstruction(label, Register.valueOf(register), jumpToLabel);
//            }
//            default -> {
//                System.out.println("Unknown instruction: " + opcode);
//            }
//        }

        // Then, replace the switch by using the Reflection API
        try {

            Class<?> classObject = Class.forName(
                    String.format("sml.instruction.%sInstruction",
                            opcode.substring(0, 1).toUpperCase() + opcode.substring(1)
                    )
            );

            Constructor<?> constructor = classObject.getConstructors()[0];

            ArrayList<String> argumentsList = new ArrayList<>();
            argumentsList.add(label);
            scan();
            for (int i = 1; i < constructor.getParameterCount(); i++) {
                argumentsList.add(scan());
            }

            Object[] parameterObjects = new Object[constructor.getParameterCount()];

            Class<?>[] constructorParameterTypes = constructor.getParameterTypes();

            for (int i = 0; i < constructor.getParameterCount(); i++) {
                Class<?> constructionParameterTypeClass = constructorParameterTypes[i];

                if (constructionParameterTypeClass.isInterface()) {
                    Method method = Registers.Register.class.getMethod("valueOf", String.class);

                    parameterObjects[i] = method.invoke(null, argumentsList.get(i));

                } else {
                    parameterObjects[i] = constructionParameterTypeClass
                            .getConstructor(String.class)
                            .newInstance(argumentsList.get(i));
                }
            }

            return (Instruction) constructor.newInstance(parameterObjects);

        } catch (Exception e) {
            e.printStackTrace();
        }

        // TODO: Next, use dependency injection to allow this machine class
        //       to work with different sets of opcodes (different CPUs)

        return null;
    }


    private String getLabel() {
        String word = scan();
        if (word.endsWith(":"))
            return word.substring(0, word.length() - 1);

        // undo scanning the word
        line = word + " " + line;
        return null;
    }

    /*
     * Return the first word of line and remove it from line.
     * If there is no word, return "".
     */
    private String scan() {
        line = line.trim();

        for (int i = 0; i < line.length(); i++)
            if (Character.isWhitespace(line.charAt(i))) {
                String word = line.substring(0, i);
                line = line.substring(i);
                return word;
            }

        return line;
    }
}