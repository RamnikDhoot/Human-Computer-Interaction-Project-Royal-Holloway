import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import view.PrintPreviewExample;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@link PrintPreviewExample} class.
 */
public class PrintPreviewExampleTest {

    private PrintPreviewExample printPreviewExample;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    public void setUp() {
        printPreviewExample = new PrintPreviewExample(null);
    }

    /**
     * Tests if the preview panel creation is not null.
     */
    @Test
    public void testCreatePreviewPanelNotNull() {
        assertNotNull(printPreviewExample.createPreviewPanel());
    }

    /**
     * Tests if the options panel creation is not null.
     */
    @Test
    public void testCreateOptionsPanelNotNull() {
        assertNotNull(printPreviewExample.createOptionsPanel(null));
    }

    /**
     * Tests handling print action does not throw any exception.
     */
    @Test
    public void testHandlePrintAction() {
        assertDoesNotThrow(() -> printPreviewExample.handlePrintAction(null));
    }

    /**
     * Tests displaying print preview does not throw any exception.
     */
    @Test
    public void testDisplayPrintPreview() {
        assertDoesNotThrow(() -> printPreviewExample.displayPrintPreview());
    }

    /**
     * Tests if the constructor initializes the object properly.
     */
    @Test
    public void testConstructorNotNull() {
        assertNotNull(printPreviewExample);
    }
}
