import java.io.IOException;

import enigma.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;

public class UIController {
    // Variables

    // Alphabet in use
    public static Alphabet alphabet = new Alphabet("English");

    // Enigma types
    private final ObservableList<String> m3types = FXCollections.observableArrayList("I", "II", "III", "IV", "V");
    private final ObservableList<String> m4firstTypes = FXCollections.observableArrayList("BETA", "GAMMA");
    private final ObservableList<String> m4types = FXCollections.observableArrayList("I", "II", "III", "IV", "V", "VI",
            "VII", "VIII");

    // Enigma
    private Enigma e;

    // Plugboard
    public static Plugboard pb = new Plugboard();

    // Language picker
    @FXML
    private ChoiceBox<String> languagePicker;

    // Spinners
    @FXML
    private Spinner<Integer> m3leftPosSpinner;
    @FXML
    private Spinner<Integer> m3leftRingSpinner;
    @FXML
    private Spinner<String> m3leftTypeSpinner;
    @FXML
    private Spinner<Integer> m3middlePosSpinner;
    @FXML
    private Spinner<Integer> m3middleRingSpinner;
    @FXML
    private Spinner<String> m3middleTypeSpinner;
    @FXML
    private Spinner<Integer> m3rightPosSpinner;
    @FXML
    private Spinner<Integer> m3rightRingSpinner;
    @FXML
    private Spinner<String> m3rightTypeSpinner;
    @FXML
    private Spinner<Integer> m4firstPosSpinner;
    @FXML
    private Spinner<Integer> m4firstRingSpinner;
    @FXML
    private Spinner<String> m4firstTypeSpinner;
    @FXML
    private Spinner<Integer> m4secondPosSpinner;
    @FXML
    private Spinner<Integer> m4secondRingSpinner;
    @FXML
    private Spinner<String> m4secondTypeSpinner;
    @FXML
    private Spinner<Integer> m4thirdPosSpinner;
    @FXML
    private Spinner<Integer> m4thirdRingSpinner;
    @FXML
    private Spinner<String> m4thirdTypeSpinner;
    @FXML
    private Spinner<Integer> m4fourthPosSpinner;
    @FXML
    private Spinner<Integer> m4fourthRingSpinner;
    @FXML
    private Spinner<String> m4fourthTypeSpinner;

    // Plugboard list
    @FXML
    private ListView<String> plugboardList;

    // Buttons
    @FXML
    private ToggleButton themeToggle;

    @FXML
    private Button setm3Button;

    // TabPane
    @FXML
    private TabPane tabPane;

    // TextAreas
    @FXML
    private TextArea m3Settings;
    @FXML
    private TextArea m4Settings;
    @FXML
    private TextArea plaintext;
    @FXML
    private TextArea ciphertext;

    // Methods

    /**
     * Sets the Enigma machine according to the settings specified in the settings
     * area
     */
    public void setEnigma() {
        // Current tab index
        int currentTabIndex = this.tabPane.getSelectionModel().getSelectedIndex();

        // Wehrmacht M3
        if (currentTabIndex == 0) {
            String left = this.m3leftTypeSpinner.getValue();
            String middle = this.m3middleTypeSpinner.getValue();
            String right = this.m3rightTypeSpinner.getValue();

            if (left.equals(middle) || left.equals(right) || middle.equals(right)) {
                // Throw error
                Alert alert = new Alert(AlertType.ERROR, "No two rotors can be of the same type!");
                alert.showAndWait();
            } else {
                Rotor lr = new Rotor(left, alphabet);
                lr.setPosition(m3leftPosSpinner.getValue());
                lr.setRingSetting(m3leftRingSpinner.getValue());
                Rotor mr = new Rotor(middle, alphabet);
                mr.setPosition(m3middlePosSpinner.getValue());
                mr.setRingSetting(m3middleRingSpinner.getValue());
                Rotor rr = new Rotor(right, alphabet);
                rr.setPosition(m3rightPosSpinner.getValue());
                rr.setRingSetting(m3rightRingSpinner.getValue());
                e = new Enigma(lr, mr, rr, pb, alphabet);
                m3Settings.setText("Wehrmacht M3\n" + e.getSettings());
                m4Settings.setText("Wehrmacht M3\n" + e.getSettings());
            }
        }
        // Naval 4 rotor enigma
        else {
            String first = this.m4firstTypeSpinner.getValue();
            String second = this.m4secondTypeSpinner.getValue();
            String third = this.m4thirdTypeSpinner.getValue();
            String fourth = this.m4fourthTypeSpinner.getValue();

            if (first.equals(second) || first.equals(third) || first.equals(fourth) || second.equals(third)
                    || second.equals(fourth) || third.equals(fourth)) {
                // Throw error
                Alert alert = new Alert(AlertType.ERROR, "No two rotors can be of the same type!");
                alert.showAndWait();
            } else {
                Rotor fi = new Rotor(first, alphabet);
                fi.setPosition(m4firstPosSpinner.getValue());
                fi.setRingSetting(m4firstRingSpinner.getValue());
                Rotor s = new Rotor(second, alphabet);
                s.setPosition(m4secondPosSpinner.getValue());
                s.setRingSetting(m4secondRingSpinner.getValue());
                Rotor t = new Rotor(third, alphabet);
                t.setPosition(m4thirdPosSpinner.getValue());
                t.setRingSetting(m4thirdRingSpinner.getValue());
                Rotor fo = new Rotor(fourth, alphabet);
                fo.setPosition(m4fourthPosSpinner.getValue());
                fo.setRingSetting(m4fourthRingSpinner.getValue());
                e = new Enigma(fi, s, t, fo, pb, alphabet);
                m3Settings.setText("Naval 4-Rotor Enigma\n" + e.getSettings());
                m4Settings.setText("Naval 4-Rotor Enigma\n" + e.getSettings());
            }
        }
    }

    /**
     * Encrypts the message specified in the plaintext text area and displays the
     * ciphertext in the ciphertext text area
     */
    public void encrypt() {
        String input = plaintext.getText();
        input = e.formatInput(input);
        plaintext.setText(input);
        String output = e.encrypt(input);
        ciphertext.setText(output);

        // Current tab index
        int currentTabIndex = this.tabPane.getSelectionModel().getSelectedIndex();
        // Display current settings
        if (currentTabIndex == 0) {
            m3Settings.setText("Wehrmacht M3\n" + e.getSettings());
            m4Settings.setText("Wehrmacht M3\n" + e.getSettings());
        } else {
            m3Settings.setText("Naval 4-Rotor Enigma\n" + e.getSettings());
            m4Settings.setText("Naval 4-Rotor Enigma\n" + e.getSettings());
        }
    }


    /**
     * Opens the plugboard configuration window
     */
    public void openPlugboardConfig() throws IOException {
        Parent plugboardParent = FXMLLoader.load(getClass().getResource("PlugboardConfig.fxml"));
        Scene plugboardScene = new Scene(plugboardParent);
        // Set style
        plugboardScene.getStylesheets().setAll(App.activeStage.getScene().getStylesheets());

        App.activeStage.setScene(plugboardScene);
        App.activeStage.show();
    }


    /**
     * Update plugboardlist
     */
    public void updatePlugboardList() {
        plugboardList.getItems().setAll(pb.getPairs());
    }


    /**
     * Changes the language according to its parameter
     * 
     * @param newLanguage the new language
     */
    private void changeLanguage(String newLanguage) {
        alphabet = new Alphabet(newLanguage);
        
        // Reset plugboard
        pb = new Plugboard();
        pb.setAlphabet(alphabet);
        updatePlugboardList();
        
        // Update spinners
        updateSpinners();
    }

    private void updateSpinners(){
        // Alphabet size
        int aSize = alphabet.alphabet.length()-1;

        // Numbers from 0 to aSize (indexing letters of the alphabet)
        SpinnerValueFactory<Integer> m3lposVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m3mposVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m3rposVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m4firstPosVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m4secondPosVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m4thirdPosVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m4fourthPosVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);

        SpinnerValueFactory<Integer> m3lringVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m3mringVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m3rringVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m4firstRingVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m4secondRingVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m4thirdRingVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);
        SpinnerValueFactory<Integer> m4fourthRingVals = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, aSize, 0);

        // Types
        SpinnerValueFactory<String> m3ltypeVals = new SpinnerValueFactory.ListSpinnerValueFactory<String>(m3types);
        SpinnerValueFactory<String> m3mtypeVals = new SpinnerValueFactory.ListSpinnerValueFactory<String>(m3types);
        SpinnerValueFactory<String> m3rtypeVals = new SpinnerValueFactory.ListSpinnerValueFactory<String>(m3types);
        SpinnerValueFactory<String> m4firstTypeVals = new SpinnerValueFactory.ListSpinnerValueFactory<String>(
                m4firstTypes);
        SpinnerValueFactory<String> m4secondTypeVals = new SpinnerValueFactory.ListSpinnerValueFactory<String>(m4types);
        SpinnerValueFactory<String> m4thirdTypeVals = new SpinnerValueFactory.ListSpinnerValueFactory<String>(m4types);
        SpinnerValueFactory<String> m4fourthTypeVals = new SpinnerValueFactory.ListSpinnerValueFactory<String>(m4types);

        // Config Spinners
        this.m3leftTypeSpinner.setValueFactory(m3ltypeVals);
        this.m3leftPosSpinner.setValueFactory(m3lposVals);
        this.m3leftRingSpinner.setValueFactory(m3lringVals);
        this.m3middleTypeSpinner.setValueFactory(m3mtypeVals);
        this.m3middlePosSpinner.setValueFactory(m3mposVals);
        this.m3middleRingSpinner.setValueFactory(m3mringVals);
        this.m3rightTypeSpinner.setValueFactory(m3rtypeVals);
        this.m3rightPosSpinner.setValueFactory(m3rposVals);
        this.m3rightRingSpinner.setValueFactory(m3rringVals);

        this.m4firstTypeSpinner.setValueFactory(m4firstTypeVals);
        this.m4firstPosSpinner.setValueFactory(m4firstPosVals);
        this.m4firstRingSpinner.setValueFactory(m4firstRingVals);
        this.m4secondTypeSpinner.setValueFactory(m4secondTypeVals);
        this.m4secondPosSpinner.setValueFactory(m4secondPosVals);
        this.m4secondRingSpinner.setValueFactory(m4secondRingVals);
        this.m4thirdTypeSpinner.setValueFactory(m4thirdTypeVals);
        this.m4thirdPosSpinner.setValueFactory(m4thirdPosVals);
        this.m4thirdRingSpinner.setValueFactory(m4thirdRingVals);
        this.m4fourthTypeSpinner.setValueFactory(m4fourthTypeVals);
        this.m4fourthPosSpinner.setValueFactory(m4fourthPosVals);
        this.m4fourthRingSpinner.setValueFactory(m4fourthRingVals);
    }

    /**
     * Toggles between light and dark theme
     */
    public void toggleTheme() {
        if(themeToggle.isSelected()){
            App.activeStage.getScene().getStylesheets().setAll("DarkTheme.css");
            themeToggle.setText("Light mode");
        }
        else{
            App.activeStage.getScene().getStylesheets().setAll("LightTheme.css");
            themeToggle.setText("Dark mode");
        }
    }

    // Initialise
    @FXML
    public void initialize() {
        // Default Enigma
        e = new Enigma(new Rotor("I", alphabet), new Rotor("II", alphabet),
                new Rotor("III", alphabet), new Plugboard(), alphabet);
        m3Settings.setText("Wehrmacht M3\n" + e.getSettings());
        m4Settings.setText("Wehrmacht M3\n" + e.getSettings());

        // Initialise Spinners
        updateSpinners();

        // Languages
        ObservableList<String> languageOptions = FXCollections.observableArrayList("English", "Hungarian");
        languagePicker.setItems(languageOptions);
        languagePicker.setValue(languageOptions.get(0));
        // Add a listener
        languagePicker.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

            @Override
            // if the item of the list is changed 
            public void changed(ObservableValue ov, Number value, Number new_value) 
            { 
  
                // Calls change language method
                changeLanguage(languageOptions.get(new_value.intValue()));
            }
        });

        

        
    }

}
