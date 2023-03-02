package sml;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LabelsTest {
    @Test
    public void canAddUniqueLabels() {
        Labels labels = new Labels();

        labels.addLabel("f1", 0);
        assertDoesNotThrow(() -> labels.addLabel("f2", 1));
    }

    @Test
    public void cannotAddDuplicateLabels() {
        Labels labels = new Labels();

        labels.addLabel("f1", 0);
        assertThrows(IllegalArgumentException.class, () -> labels.addLabel("f1", 1));
    }
}