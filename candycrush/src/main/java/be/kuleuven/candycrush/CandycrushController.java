package be.kuleuven.candycrush;

import java.net.URL;
import java.util.ResourceBundle;

import be.kuleuven.candycrush.model.CandycrushModel;
import be.kuleuven.candycrush.model.Position;
import be.kuleuven.candycrush.view.CandycrushView;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class CandycrushController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label Label;

    @FXML
    private Label scoreLable;
    @FXML
    private Button btn;

    @FXML
    private AnchorPane paneel;

    @FXML
    private AnchorPane speelbord;

    @FXML
    private TextField textInput;

    private CandycrushModel model;
    private CandycrushView view;
    @FXML
    void initialize() {
        assert Label != null : "fx:id=\"Label\" was not injected: check your FXML file 'candycrush-view.fxml'.";
        assert btn != null : "fx:id=\"btn\" was not injected: check your FXML file 'candycrush-view.fxml'.";
        assert paneel != null : "fx:id=\"paneel\" was not injected: check your FXML file 'candycrush-view.fxml'.";
        assert speelbord != null : "fx:id=\"speelbord\" was not injected: check your FXML file 'candycrush-view.fxml'.";
        assert textInput != null : "fx:id=\"textInput\" was not injected: check your FXML file 'candycrush-view.fxml'.";
        model = new CandycrushModel("Test");
        view = new CandycrushView(model);
        speelbord.getChildren().add(view);
        btn.setOnMouseClicked(this::handleStartButtonClicked);
    }



    public void update(){
        scoreLable.setText("Score = "+ model.getScore());
        view.update();
    }

    public void onCandyClicked(MouseEvent me){

        int candyIndex = view.getIndexOfClicked(me);
        model.candyWithIndexSelected(Position.fromIndex(candyIndex,model.getBoardSize()));
        update();
    }
    public void handleStartButtonClicked(MouseEvent e) {
        if (!textInput.getText().isEmpty()) {
            model = new CandycrushModel(textInput.getText());
            view = new CandycrushView(model);
            speelbord.getChildren().clear();
            speelbord.getChildren().add(view);
            speelbord.setVisible(true);
            btn.setText("retry");
            view.setOnMouseClicked(this::onCandyClicked);

            update();
        }
    }
}
