package des;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
        fileLocations.setText(file.toString());
    }

    @FXML
    public void dragFiles(DragEvent event) {
        System.out.println("onDragDrop");
        Object obj;
        Dragboard db = event.getDragboard();
        boolean success = false;
        if (db.hasFiles()){
            success = true;
            System.out.println("onDragDrop");
        }
    /* let the source know whether the string was successfully
     * transferred and used */
        event.setDropCompleted(success);
        event.consume();
    }

    @FXML
    private void handleDragDetected(MouseEvent event) {
        System.out.println("onDragDetected");
        Dragboard db=browseOrDragFiles.startDragAndDrop(TransferMode.MOVE);
        event.consume();
    }
}
