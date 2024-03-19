import javax.swing.SwingUtilities;
import model.NotesModel;
import view.NotesView;
import controller.NotesController;

/**
 * The entry point for the Notes application. This class is responsible for
 * initializing the application,
 * including setting up the model, view, and controller, and ensuring that they
 * are connected properly.
 */
public class Main {

    /**
     * The main method that serves as the entry point of the application.
     *
     * @param args The command line arguments passed to the application (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NotesModel model = NotesModel.getInstance(); // access the singleton model
            NotesView view = new NotesView();
            NotesController controller = new NotesController(model, view);
            view.setController(controller); // Link controller and view

            model.attach(view); // Attach the view as an observer to the model

            view.display();
        });
    }
}
