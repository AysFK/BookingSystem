package com.fx.bookingsystem.view;

import com.fx.bookingsystem.MainApp;
import com.fx.bookingsystem.model.Booking;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class BookingOverviewController {

    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, String> tableColumn;
    @FXML
    private TableColumn<Booking, String> nameColumn;

    @FXML
    private Label nameLabel;
    @FXML
    private Label tableLabel;
    @FXML
    private Label phoneLabel;
    @FXML
    private Label time1Label;
    @FXML
    private Label time2Label;
    @FXML
    private Label name2Label;

    private MainApp mainApp;


    public BookingOverviewController(){}

    @FXML
    private void initialize(){
        tableColumn.setCellValueFactory(cellData -> cellData.getValue().TableProperty());
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().Name1Property());

        showBookingDetails(null);

        bookingTable.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showBookingDetails(newValue));
    }

    public void setMainApp(MainApp mainApp){
        this.mainApp = mainApp;
        bookingTable.setItems(mainApp.getBookingData());
    }

    private void showBookingDetails(Booking booking){
        if(booking != null){
            nameLabel.setText(booking.getName1());
            tableLabel.setText(booking.getTable());
            phoneLabel.setText(booking.getPhone());
            time1Label.setText(booking.getTime1());
            time2Label.setText(booking.getTime2());
            name2Label.setText(booking.getName2());

        } else {
            nameLabel.setText("");
            tableLabel.setText("");
            phoneLabel.setText("");
            time1Label.setText("");
            time2Label.setText("");
            name2Label.setText("");
        }
    }

    @FXML
    private void handleDeleteBooking() {
        int selectedIndex = bookingTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            bookingTable.getItems().remove(selectedIndex);
        } else {}
    }

    @FXML
    private void handleNewBooking() {
        Booking tempBooking = new Booking();
        boolean okClicked = mainApp.showBookingEditDialog(tempBooking);
        if (okClicked) {
            mainApp.getBookingData().add(tempBooking);
        }
    }

    @FXML
    private void handleEditBooking() {
        Booking selectedBooking = bookingTable.getSelectionModel().getSelectedItem();
        if (selectedBooking != null) {
            boolean okClicked = mainApp.showBookingEditDialog(selectedBooking);
            if (okClicked) {
                showBookingDetails(selectedBooking);
            }

        } else {}
    }
}