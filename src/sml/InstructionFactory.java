package sml;

import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public final class InstructionFactory {
    private static final InstructionFactory INSTRUCTION_FACTORY = new InstructionFactory();

    private InstructionProvider provider;

    public static InstructionFactory getInstance() {
        return INSTRUCTION_FACTORY;
    }

    public InstructionProvider getInstructionProvider() {
        return provider;
    }

    private InstructionFactory() {
        Properties props = new Properties();

        try {
            try (InputStream config = InstructionFactory.class.getResourceAsStream("/beans.properties")) { //
                props.load(config);
            }

            String providerClass = props.getProperty("provider.class");
            provider = (InstructionProvider) newInstanceOf(providerClass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Object newInstanceOf(String className) throws ClassNotFoundException,
            NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        Class<?> classObject = Class.forName(className);
        Constructor<?> constructor = classObject.getDeclaredConstructor();
        return constructor.newInstance();
    }
}
