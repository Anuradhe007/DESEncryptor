package des;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.lingala.zip4j.exception.ZipException;

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
    private Button selectFolderBtn;

    @FXML
    private Button selectFolderToDecrypt;

    private boolean isFomFolder;

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
        DESEncryption desEncryption = new DESEncryption();
        String textInput1 = "";
        if (!inputText.getText().isEmpty()) {
            textInput1 = inputText.getText().trim();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Input text cannot be empty.");
            alert.showAndWait();
        }
        if (passwordField.getText() != null && passwordField.getText().length() >= 8) {
            String phraseKey = passwordField.getText().trim();
            desEncryption.cipherGenerator(phraseKey);
            if (!textInput1.isEmpty()) {
                String encryptedText = desEncryption.encrypt(textInput1);
                outPutText.clear();
                outPutText.setText(encryptedText);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Password length should be 8 characters.");
            alert.showAndWait();
        }
    }

    @FXML
    public void decryptTextBtnClick() {
        DESEncryption desEncryption = new DESEncryption();
        String textInput1 = "";
        if (!inputText.getText().isEmpty()) {
            textInput1 = inputText.getText().trim();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Input text cannot be empty.");
            alert.showAndWait();
        }
        if (passwordField.getText() != null && passwordField.getText().length() >= 8) {
            String phraseKey = passwordField.getText().trim();
            desEncryption.cipherGenerator(phraseKey);
            if (!textInput1.isEmpty()) {
                String decryptedText = desEncryption.decrypt(textInput1);
                outPutText.clear();
                outPutText.setText(decryptedText);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Password length should be 8 characters.");
            alert.showAndWait();
        }
    }

    @FXML
    public void encryptFileBtnClick() throws ZipException {

        FileEncryptor fileEncryptor = new FileEncryptor();
        String inputFilePath = fileLocations.getText();
        String password = passwordField.getText();
        if (inputFilePath != null) {
            if (password != null && password.length() >= 8) {
                Stage stage = new Stage();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Enrypted File");
                File fileDest = fileChooser.showSaveDialog(stage);
                if (fileDest != null) {
                    if (!isFomFolder) {
                        fileEncryptor.encryptFile(inputFilePath, fileDest.toString(), password.trim());
                    } else {
                        ZipUnZipDirectory zipUnZipDirectory = new ZipUnZipDirectory();
                        zipUnZipDirectory.zipFileWithPassword(inputFilePath, password.trim(), fileDest.toString());
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Password length should be 8 characters.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Please give file name.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File location cannot be empty.");
            alert.showAndWait();
        }
    }

    @FXML
    public void decryptFileBtnClick() {

        FileEncryptor fileEncryptor = new FileEncryptor();
        String inputFilePath = fileLocations.getText();
        String password = passwordField.getText();
        if (inputFilePath != null) {
            if (password != null && password.length() >= 8) {
                Stage stage = new Stage();
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Save Decrypted File");
                File fileDest = fileChooser.showSaveDialog(stage);
                if (fileDest != null) {
                    if (!isFomFolder) {
                        fileEncryptor.decryptFile(inputFilePath, fileDest.toString(), password);
                    } else {
                        ZipUnZipDirectory zipUnZipDirectory = new ZipUnZipDirectory();
                        zipUnZipDirectory.unzipFile(inputFilePath, fileDest.toString(), password);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Please give file name.");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Password length should be 8 characters.");
                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File location cannot be empty.");
            alert.showAndWait();
        }
    }

    @FXML
    public void clearBtnClick() {
        inputText.clear();
        outPutText.clear();
        passwordField.clear();
        fileLocations.clear();
        Image image = new Image(getClass().getResourceAsStream("views/drag-drop-files.png"));
        browseOrDragFiles.setImage(image);
    }

    @FXML
    public void copyTextBtnClick() {
        if (outPutText.getText() != null) {
            final Clipboard clipboard = Clipboard.getSystemClipboard();
            final ClipboardContent content = new ClipboardContent();
            String textToCopy = outPutText.getText().trim();
            content.putString(textToCopy);
            clipboard.setContent(content);
        }
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
    public void clickSelectFolderBtn() {
        isFomFolder = true;
        Stage stage = new Stage();
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Open Folder");
        File file = directoryChooser.showDialog(stage);
        if (file != null) {
            Image image = new Image(getClass().getResourceAsStream("views/file.jpg"));
            browseOrDragFiles.setImage(image);
            fileLocations.setText(file.toString());
        }
    }

    @FXML
    public void clickSelectFolderToDecryptBtn() {
        isFomFolder = true;
        Stage stage = new Stage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Ecrypted Folder");
        File file = fileChooser.showOpenDialog(stage);
        if (file != null) {
            Image image = new Image(getClass().getResourceAsStream("views/file.jpg"));
            browseOrDragFiles.setImage(image);
            fileLocations.setText(file.toString());
        }
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
