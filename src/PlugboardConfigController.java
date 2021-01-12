import javafx.fxml.FXML;

public class PlugboardConfigController {
    public void backClicked() {
        App.activeStage.setScene(App.homeScene);
        App.activeStage.show();
    }
}
