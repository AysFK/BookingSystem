package com.fx.bookingsystem.view;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import com.fx.bookingsystem.model.Booking;

public class BookingEditController {

    @FXML
    private TextField Name1Field;
    @FXML
    private TextField TableField;
    @FXML
    private TextField PhoneField;
    @FXML
    private TextField Time2Field;
    @FXML
    private TextField Time1Field;
    @FXML
    private TextField Name2Field;


    private Stage dialogStage;
    private Booking booking;
    private boolean okClicked = false;


    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;

        Name1Field.setText(booking.getName1());
        TableField.setText(booking.getTable());
        PhoneField.setText(booking.getPhone());
        Time2Field.setText(booking.getTime2());
        Time1Field.setText(booking.getTime1());
        Name2Field.setText(booking.getName2());
    }


    public boolean isOkClicked() {
        return okClicked;
    }

    @FXML
    private void handleOk() {
        if (/*isInputValid()*/true) {
            booking.setName1(Name1Field.getText());
            booking.setTable(TableField.getText());
            booking.setPhone(PhoneField.getText());
            booking.setTime2(Time2Field.getText());
            booking.setTime1(Time1Field.getText());
            booking.setName2(Name2Field.getText());

            okClicked = true;
            dialogStage.close();
        }
    }


    @FXML
    private void handleCancel() {
        dialogStage.close();
    }

    private void isInputValid() {}
}