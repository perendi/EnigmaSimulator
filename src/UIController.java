import enigma.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TabPane;

public class UIController {
    // Variables

    // Spinners
    @FXML
    private Spinner<Integer> m3leftPosSpinner;
    @FXML
    private Spinner<Integer> m3middlePosSpinner;
    @FXML
    private Spinner<Integer> m3rightPosSpinner;
    @FXML
    private Spinner<Integer> m4firstPosSpinner;
    @FXML
    private Spinner<Integer> m4secondPosSpinner;
    @FXML
    private Spinner<Integer> m4thirdPosSpinner;
    @FXML
    private Spinner<Integer> m4fourthPosSpinner;

    // Buttons
    @FXML
    private Button setm3Button;

    // TabPane
    @FXML
    private TabPane tabPane;

    // Methods
    public void setEnigma() {
        System.out.println(this.m3leftPosSpinner.getValue());
        int currentTabIndex = this.tabPane.getSelectionModel().getSelectedIndex();

        // Wehrmacht M3
        if (currentTabIndex == 0) {

        }
    }

    // Initialise
    @FXML
    public void initialize() {
        // Numbers from 0 to 25 (indexing letters of the alphabet)
        SpinnerValueFactory<Integer> m3lposVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25, 0);
        SpinnerValueFactory<Integer> m3mposVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25, 0);
        SpinnerValueFactory<Integer> m3rposVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25, 0);
        SpinnerValueFactory<Integer> m4firstposVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25, 0);
        SpinnerValueFactory<Integer> m4secondposVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25, 0);
        SpinnerValueFactory<Integer> m4thirdposVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25, 0);
        SpinnerValueFactory<Integer> m4fourthposVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 25, 0);

        // Config Spinners
        this.m3leftPosSpinner.setValueFactory(m3lposVals);
        this.m3rightPosSpinner.setValueFactory(m3rposVals);
        this.m3middlePosSpinner.setValueFactory(m3mposVals);
        this.m4firstPosSpinner.setValueFactory(m4firstposVals);
        this.m4secondPosSpinner.setValueFactory(m4secondposVals);
        this.m4thirdPosSpinner.setValueFactory(m4thirdposVals);
        this.m4fourthPosSpinner.setValueFactory(m4fourthposVals);
    }

}
