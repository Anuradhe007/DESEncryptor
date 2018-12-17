package des;

import javafx.scene.control.Alert;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.File;
import java.time.LocalDateTime;

public class ZipUnZipDirectory {

    public void unzipFile(String encryptedFilePath, String unzippedFile, String password) {
        LocalDateTime localDateTime = LocalDateTime.now();
        int timeHash = localDateTime.hashCode();
        String folderName = "_tmp" + timeHash + "_";
        File dir = new File(folderName);
        boolean isDirCreated = dir.mkdir();

        if (isDirCreated) {
            String zippedFilePath = folderName + "/" + timeHash + ".zip";
            FileEncryptor fileEncryptor = new FileEncryptor();
            fileEncryptor.decryptFile(encryptedFilePath, zippedFilePath, password);
            try {
                ZipFile zipFile = new ZipFile(zippedFilePath);
                if (zipFile.isEncrypted()) {
                    zipFile.setPassword(password);
                }
                zipFile.extractAll(unzippedFile);
            } catch (ZipException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Please make sure you have provided correct details.");
                alert.showAndWait();
            }
            File index = new File(folderName);
            String[] entries = index.list();
            if (entries != null && entries.length > 0) {
                for (String s : entries) {
                    File currentFile = new File(index.getPath(), s);
                    currentFile.delete();
                }
            }
            dir.delete();
        }
    }

    public void zipFileWithPassword(String fileToZipPath, String password, String encryptedFilePath) throws ZipException {
        LocalDateTime localDateTime = LocalDateTime.now();
        int timeHash = localDateTime.hashCode();
        String folderName = "_tmp" + timeHash + "_";
        File dir = new File(folderName);
        boolean isDirCreated = dir.mkdir();
        if (isDirCreated) {
            String zippedFilePath = folderName + "/" + timeHash + ".zip";
            ZipFile zipFile = new ZipFile(zippedFilePath);
            ZipParameters parameters = new ZipParameters();
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
            File fileToZip = new File(fileToZipPath);
            zipFile.addFolder(fileToZip, parameters);
            FileEncryptor fileEncryptor = new FileEncryptor();
            fileEncryptor.encryptFile(zippedFilePath, encryptedFilePath, password);
            File index = new File(folderName);
            String[] entries = index.list();
            if (entries != null && entries.length > 0) {
                for (String s : entries) {
                    File currentFile = new File(index.getPath(), s);
                    currentFile.delete();
                }
            }
            dir.delete();
        }
    }
}
