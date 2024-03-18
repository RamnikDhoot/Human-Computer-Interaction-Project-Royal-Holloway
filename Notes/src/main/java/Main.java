import javax.swing.SwingUtilities;
import model.NotesModel;
import view.NotesView;
import controller.NotesController;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            NotesModel model = new NotesModel();
            NotesView view = new NotesView();
            NotesController controller = new NotesController(model, view);
            view.display();
        });
    }
}
