package com.fx.bookingsystem;
/**
 * Created by Administrator on 2017/5/16.
 */

import com.fx.bookingsystem.model.Booking;
import com.fx.bookingsystem.model.BookingList;
import com.fx.bookingsystem.view.StatisticsController;
import com.fx.bookingsystem.view.BookingEditController;
import com.fx.bookingsystem.view.BookingOverviewController;
import com.fx.bookingsystem.view.RootViewController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.prefs.Preferences;

public class MainApp extends Application
{

    private Stage primaryStage;
    private BorderPane rootLayout;
    private ObservableList<Booking> BookingData = FXCollections.observableArrayList();

    public static void main(String[] args)
    {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("BookingSystem");
        this.primaryStage.centerOnScreen();

        initRootLayout();
        showBookingOverview();
    }


    public MainApp(){
        //Add sample data
        BookingData.add(new Booking("A", "1"));
        BookingData.add(new Booking("B", "2"));
        BookingData.add(new Booking("C", "3"));
        BookingData.add(new Booking("D", "4"));
        BookingData.add(new Booking("E", "5"));
        BookingData.add(new Booking("F", "6"));
        BookingData.add(new Booking("G", "7"));
        BookingData.add(new Booking("H", "8"));
        BookingData.add(new Booking("I", "9"));
        BookingData.add(new Booking("J", "1"));
        BookingData.add(new Booking("K", "1"));
    }

    public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/RootView.fxml"));
            rootLayout = (BorderPane) loader.load();

            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);

            RootViewController controller = loader.getController();
            controller.setMainApp(this);

            primaryStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showBookingOverview(){
        try
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BookingOverview.fxml"));
            AnchorPane BookingOverview = (AnchorPane) loader.load();

            rootLayout.setCenter(BookingOverview);

            BookingOverviewController controller = loader.getController();
            controller.setMainApp(this);

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Stage getPrimaryStage(){
        return primaryStage;
    }

    public ObservableList<Booking> getBookingData(){
        return BookingData;
    }

    public boolean showBookingEditDialog(Booking Booking) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/BookingEdit.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the Booking into the controller.
            BookingEditController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setBooking(Booking);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public File getBookingFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }

    public void setBookingFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("BookingSystem - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("AddressApp");
        }
    }

    public void loadBookingDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(BookingList.class);
            Unmarshaller um = context.createUnmarshaller();

            BookingList list = (BookingList) um.unmarshal(file);

            BookingData.clear();
            BookingData.addAll(list.getBookings());

            setBookingFilePath(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveBookingDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(BookingList.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our Booking data.
            BookingList list = new BookingList();
            list.setBookings(BookingData);

            // Marshalling and saving XML to the file.
            m.marshal(list, file);

            // Save the file path to the registry.
            setBookingFilePath(file);
        } catch (Exception e) { }
    }

    public void showStatistics() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("view/Statistics.fxml"));
            BorderPane page = (BorderPane) loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Booking Statistics");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            dialogStage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
