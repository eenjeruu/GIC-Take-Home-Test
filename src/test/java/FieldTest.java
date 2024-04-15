import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FieldTest {

    @Test
    public void edgeCaseAtCorner() {
        Field field = new Field(10, 10);
        // Test corner bounds
        Assertions.assertTrue(field.isWithinBounds(0, 0));
        Assertions.assertTrue(field.isWithinBounds(9, 9));
    }

    @Test
    public void edgeCaseOutsideCorner() {
        Field field = new Field(10, 10);
        // Test just outside corner bounds
        Assertions.assertFalse(field.isWithinBounds(10, 10));
    }

    @Test
    public void sanityCheckWithinBounds() {
        Field field = new Field(10, 10);
        // Middle of the field should be within bounds
        Assertions.assertTrue(field.isWithinBounds(5, 5));
    }
}
