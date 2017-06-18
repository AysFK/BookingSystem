package com.fx.bookingsystem.model;

import javafx.beans.property.*;

public class Booking {
    private final StringProperty Name1;
    private final StringProperty Table;
    private final StringProperty Phone;
    private final StringProperty Time2;
    private final StringProperty Time1;
    private final StringProperty Name2;

    public Booking(){
        this(null,null);
    }

    public Booking(String Name1, String Table){
        this.Name1 = new SimpleStringProperty(Name1);
        this.Table = new SimpleStringProperty(Table);

        this.Phone = new SimpleStringProperty("15850501234");
        this.Time1  = new SimpleStringProperty("2017.1.2-11:30");
        this.Time2 = new SimpleStringProperty("2017.1.1");
        this.Name2 = new SimpleStringProperty("zhangsan");
    }

    /**Get the data by xProperty.get();*/
    public String getName1(){
        return Name1.get();
    }

    public String getTable(){
        return Table.get();
    }

    public String getTime1() {
        return Time1.get();
    }

    public String getTime2(){
        return Time2.get();
    }

    public String getPhone() {
        return Phone.get();
    }

    public String  getName2() {
        return Name2.get();
    }

    /**Get data by return xProperty;*/
    public StringProperty Name2Property(){
        return Name2;
    }

    public StringProperty PhoneProperty(){
        return Phone;
    }

    public StringProperty Time1Property() {
        return Time1;
    }

    public StringProperty Name1Property(){
        return Name1;
    }

    public StringProperty TableProperty(){
        return Table;
    }

    public StringProperty Time2Property() {
        return Time2;
    }

    /**Set data by this.xProperty.set();*/
    public void setName1(String Name1) {
        this.Name1.set(Name1);
    }

    public void setTable(String Table){
        this.Table.set(Table);
    }

    public void setPhone(String Phone) {
        this.Phone.set(Phone);
    }

    public void setTime2(String Time2){
        this.Time2.set(Time2);
    }

    public void setTime1(String Time1) {
        this.Time1.set(Time1);
    }

    public void setName2(String Name2){
        this.Name2.set(Name2);
    }

}
