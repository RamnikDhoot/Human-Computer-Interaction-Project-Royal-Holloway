import javax.swing.SwingUtilities;
import model.NotesModel;
import view.NotesView;
import controller.NotesController;

public class Main {
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

