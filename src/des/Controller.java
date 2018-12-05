package des;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class Controller {

    @FXML
    private TextArea inputText;

    @FXML
    private TextArea outPutText;

    @FXML
    private Button copyTextOutputBtn;

    @FXML
    private ImageView browseOrDragFiles;

    @FXML
    private JFXPasswordField passwordField;

    @FXML
    private JFXTextField fileLocations;

    @FXML
    private JFXButton encryptTxtBtn;

    @FXML
    private JFXButton decryptTxtBtn;

    @FXML
    private JFXButton encryptFileBtn;

    @FXML
    private JFXButton decryptFileBtn;

    @FXML
    private JFXButton clearBtn;

    @FXML
    public void initialize() {
        encryptTxtBtn.setStyle("-fx-background-color: #66B2FF;");
        decryptTxtBtn.setStyle("-fx-background-color: #FF99FF;");
        encryptFileBtn.setStyle("-fx-background-color: #66B2FF;");
        decryptFileBtn.setStyle("-fx-background-color: #FF99FF;");
        clearBtn.setStyle("-fx-background-color: #33FF99;");
    }

    @FXML
    public void encryptTextBtnClick() {
    }

    @FXML
    public void decryptTextBtnClick() {
    }

    @FXML
    public void encryptFileBtnClick() {
    }

    @FXML
    public void decryptFileBtnClick() {
    }

    @FXML
    public void clearBtnClick() {
    }

    @FXML
    public void copyTextBtnClick() {
    }

    @FXML
    public void browseFileClick() {
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(getClass().getResourceAsStream("views/file.jpg"));
            browseOrDragFiles.setImage(image);
            fileLocations.setText(file.toString());
        }
    }

    @FXML
    public void dragFiles(DragEvent event) {
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()) {
            File file = db.getFiles().get(0);
            Image image = new Image(getClass().getResourceAsStream("views/file.jpg"));
            browseOrDragFiles.setImage(image);
            fileLocations.setText(file.toString());
            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }

    @FXML
    private void dragEntered(DragEvent event) {
        Dragboard db = event.getDragboard();
        if (db.hasFiles()) {
            event.acceptTransferModes(TransferMode.COPY);
        }
        event.consume();
    }
}
