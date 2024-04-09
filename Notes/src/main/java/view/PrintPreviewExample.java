package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.print.*;

/**
 * Provides a graphical user interface for print preview functionality. This class extends {@link JDialog}
 * and implements {@link ActionListener} to handle user interactions. It displays a preview of the content
 * that will be printed, along with options to customize the print job, such as selecting a printer,
 * specifying the number of copies, and setting the print quality.
 */
public class PrintPreviewExample extends JDialog implements ActionListener {

    /**
     * Constructs a new {@code PrintPreviewExample} dialog with the specified owner.
     *
     * @param owner the {@code Frame} from which the dialog is displayed and centered upon
     */
    public PrintPreviewExample(Frame owner) {
        super(owner, "Print Preview", true); // 'true' for modal dialog
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window

        // Assuming createPreviewPanel and createOptionsPanel are correctly implemented
        JPanel previewPanel = createPreviewPanel();
        JPanel optionsPanel = createOptionsPanel(this);

        getContentPane().add(previewPanel, BorderLayout.CENTER);
        getContentPane().add(optionsPanel, BorderLayout.EAST);
    }

    /**
     * Handles action events, typically from user interaction with UI components like buttons.
     *
     * @param e the {@code ActionEvent} containing details about the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        // Create and display the print preview dialog
        JDialog printPreviewDialog = new JDialog(this, "Print Preview", true);
        printPreviewDialog.setLayout(new BorderLayout());

        // Create the options panel
        JPanel optionsPanel = createOptionsPanel(printPreviewDialog);

        // Create the preview panel
        JPanel previewPanel = createPreviewPanel();

        printPreviewDialog.add(previewPanel, BorderLayout.CENTER);
        printPreviewDialog.add(optionsPanel, BorderLayout.EAST);
        printPreviewDialog.pack();
        printPreviewDialog.setLocationRelativeTo(this);
        printPreviewDialog.setVisible(true);
    }

    /**
     * Creates and returns the panel that displays the print preview.
     *
     * @return the {@code JPanel} that displays a preview of what will be printed
     */
    public JPanel createPreviewPanel() {
        JPanel previewPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(Color.WHITE);
                g2d.fillRect(10, 10, this.getWidth() - 200, this.getHeight() - 20);
                g2d.setColor(Color.BLACK);
                g2d.drawRect(10, 10, this.getWidth() - 200, this.getHeight() - 20);

                Font font = new Font("Serif", Font.BOLD, 24);
                g2d.setFont(font);
                FontMetrics metrics = g2d.getFontMetrics(font); // Obtain font measurement information for the font
                int x = (this.getWidth() - 200 - metrics.stringWidth("Example")) / 2;
                int y = (this.getHeight() - metrics.getHeight()) / 2 + metrics.getAscent();
                g2d.drawString("Example", x, y);
            }
        };
        previewPanel.setPreferredSize(new Dimension(500, 700));
        return previewPanel;
    }

    /**
     * Creates and returns the panel containing print options such as printer selection, number of copies,
     * page range, page size, print quality, and orientation.
     *
     * @param printPreviewDialog the {@code JDialog} to which this options panel is attached
     * @return the {@code JPanel} containing controls to adjust print options
     */
    public JPanel createOptionsPanel(JDialog printPreviewDialog) {
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        optionsPanel.setPreferredSize(new Dimension(200, 700));
        optionsPanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 30));

        // Print Options Label
        optionsPanel.add(new JLabel("Print Options"));
        optionsPanel.add(Box.createVerticalStrut(10)); // Spacer

        // Printer selection
        JComboBox<String> printerSelection = new JComboBox<>(new String[] { "Printer 1", "Printer 2", "Printer 3" });
        optionsPanel.add(new JLabel("Printer:"));
        optionsPanel.add(printerSelection);

        // Number of copies
        SpinnerNumberModel numberModel = new SpinnerNumberModel(1, 1, 100, 1);
        JSpinner copiesSpinner = new JSpinner(numberModel); // Create a spinner to show loading
        optionsPanel.add(new JLabel("Number of copies:"));
        optionsPanel.add(copiesSpinner);

        // Page range
        JTextField fromPage = new JTextField("1", 5);
        JTextField toPage = new JTextField("9999", 5);
        JPanel rangePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rangePanel.add(new JLabel("Pages from:"));
        rangePanel.add(fromPage);
        rangePanel.add(new JLabel("to:"));
        rangePanel.add(toPage);
        optionsPanel.add(new JLabel("Print Range:"));
        optionsPanel.add(rangePanel);

        // Page size
        JComboBox<String> pageSizeSelection = new JComboBox<>(new String[] { "A4", "Letter", "Legal" });
        optionsPanel.add(new JLabel("Page Size:"));
        optionsPanel.add(pageSizeSelection);

        // Print quality
        JComboBox<String> qualitySelection = new JComboBox<>(new String[] { "High", "Medium", "Low" });
        optionsPanel.add(new JLabel("Print Quality:"));
        optionsPanel.add(qualitySelection);

        // Orientation
        JComboBox<String> orientationSelection = new JComboBox<>(new String[] { "Portrait", "Landscape" });
        optionsPanel.add(new JLabel("Orientation:"));
        optionsPanel.add(orientationSelection);

        // Add the print button at the end
        JButton printButton = new JButton("Print");
        printButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Handle the print action
                handlePrintAction(printPreviewDialog);
            }
        });
        optionsPanel.add(Box.createVerticalGlue());
        optionsPanel.add(printButton);

        return optionsPanel;
    }

    /**
     * Handles the print action when the print button is pressed. This method sets up the printing job
     * and initiates printing based on the selected options.
     *
     * @param printPreviewDialog the dialog from which the print action was initiated
     */
    public void handlePrintAction(JDialog printPreviewDialog) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new Printable() {
            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
                if (pageIndex > 0) {
                    return NO_SUCH_PAGE;
                }
                // The actual printing code would go here, this is just for demonstation so i
                // just return a priview label
                return PAGE_EXISTS;
            }
        });

        if (job.printDialog()) {
            try {
                printPreviewDialog.dispose();
                job.print();
            } catch (PrinterException ex) {
                ex.printStackTrace();
            }
        }
    }

    /**
     * Adjusts the dialog to fit its contents and displays it, centered over the owner window.
     */
    public void displayPrintPreview() {
        this.pack(); // Adjusts the dialog to fit its contents
        this.setLocationRelativeTo(this.getOwner()); // To center it over the owner window
        this.setVisible(true);
    }

}
