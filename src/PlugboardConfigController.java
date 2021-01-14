import enigma.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;

public class PlugboardConfigController {

    // Variables

    // Plugboard
    public static Plugboard p = new Plugboard();

    // Pair list
    @FXML
    private ListView<String> pairs;

    // Choice boxes
    @FXML
    private ChoiceBox<String> l1Box;
    @FXML
    private ChoiceBox<String> l2Box;

    // Text area;
    @FXML
    private TextArea textArea;

    // Delete button
    @FXML
    private Button deleteBtn;

    // Functions

    /**
     * Back button click action
     */
    public void backClicked() {
        App.activeStage.setScene(App.homeScene);
        App.activeStage.show();
    }

    /**
     * Adds a new pair to the plugboard
     */
    public void addPair() {
        int first = Rotor.toIndex(l1Box.getValue().charAt(0));
        int second = Rotor.toIndex(l2Box.getValue().charAt(0));

        if (first == second) {
            textArea.setText(
                    "The two letters are the same, automatically all the letters are connected to themselves.");
        } else if (!p.checkNewWire(first, second)) {
            textArea.setText("One or both of the letters are already connected to another letter in the steckerboard!");
        } else {
            p.add(first, second);
            textArea.setText("The pair is successfully added!");
            pairs.getItems().setAll(p.getPairs());
        }
    }

    public void deletePair() {
        // Current selection
        String selection = pairs.getSelectionModel().getSelectedItem();
        // First letter index
        int f = Rotor.toIndex(selection.charAt(0));
        // Second letter index
        int s = Rotor.toIndex(selection.charAt(4));

        // Delete
        p.delete(f, s);

        // Refresh text area and list
        pairs.getItems().setAll(p.getPairs());
        textArea.setText("The pair has successfully been deleted!");
    }

    /**
     * Setting the new plugboard
     */
    public void setPlugboard() {
        UIController.pb = p;
        App.activeStage.setScene(App.homeScene);
        App.homeController.updatePlugboardList();
        App.activeStage.show();
    }

    // Initialise
    @FXML
    public void initialize() {
        p = UIController.pb;
        // Set letter box options
        l1Box.getItems().addAll("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));
        l2Box.getItems().addAll("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));

        // Set list
        pairs.getItems().setAll(p.getPairs());

        // Disable button
        deleteBtn.disableProperty().bind(pairs.getSelectionModel().selectedItemProperty().isNull());
    }
}
