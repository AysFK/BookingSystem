
package com.fx.bookingsystem.view;

import java.io.File;

import javafx.fxml.FXML;
import javafx.stage.FileChooser;

import com.fx.bookingsystem.MainApp;


public class RootViewController {

    private MainApp mainApp;


    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    @FXML
    private void handleNew() {
        mainApp.getBookingData().clear();
        mainApp.setBookingFilePath(null);
    }

    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadBookingDataFromFile(file);
        }
    }

    @FXML
    private void handleSave() {
        File BookingFile = mainApp.getBookingFilePath();
        if (BookingFile != null) {
            mainApp.saveBookingDataToFile(BookingFile);
        } else {
            handleSaveAs();
        }
    }

    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            mainApp.saveBookingDataToFile(file);
        }
    }

    @FXML
    private void handleAbout() {}

    @FXML
    private void handleExit() {
        System.exit(0);
    }

    @FXML
    private void handleShowStatistics() {
        mainApp.showStatistics();
    }
}