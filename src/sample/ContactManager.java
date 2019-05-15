package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactManager extends Application {

    // Declare components at class scope - you don't know where you'll need them

    Label lblContactNames, lblEmailAddress, lblAddress;

    ListView<String> lvContactNames;

    TextField tfEmailAddress;

    TextArea taAddress;

    Button btnClose;

    String name;
    String phoneNumber;
    String emailAddress;
    String address;

    int biggestCellSize;

    public ContactManager(){
        //TODO
    } // end of contact manager constructor

    @Override
    public void init(){

        //Instantiate components within the init method

        lblContactNames = new Label("Contact Names:");
        lblEmailAddress = new Label("Email Address:");
        lblAddress = new Label("Address:");

        lvContactNames = new ListView<>(); populateContacts(); // calling the populate contacts method here.

        lvContactNames.setOnMouseClicked(mouseEvent -> {
            populateCells(lvContactNames.getSelectionModel().getSelectedItem());
        });
        tfEmailAddress = new TextField();

        taAddress = new TextArea();

        btnClose = new Button("Close"); btnClose.setOnAction(actionEvent -> exit());




    } // end of init


    public void populateContacts(){

        String file = "/Users/darrenking/Desktop/HCI&GUI Exam Prep/ContactManager/src/sample/contacts.csv";


        File fileToBeRead = new File(file);

        try {
            Scanner sc = new Scanner(fileToBeRead);
            while (sc.hasNextLine()){
                sc.useDelimiter(":");
                name = sc.next();
                sc.nextLine();
                lvContactNames.getItems().add(name);
            }


        } catch (FileNotFoundException fnf){
            fnf.getMessage();
        }


    } // end of populate contacts



    public void populateCells(String namer){


        String file = "/Users/darrenking/Desktop/HCI&GUI Exam Prep/ContactManager/src/sample/contacts.csv";
        File fileToBeRead = new File(file);

        try {
            Scanner sc = new Scanner(fileToBeRead);
            while (sc.hasNextLine()){
                sc.useDelimiter(":");

                if (sc.hasNext(namer)){
                    name = sc.next();
                    emailAddress = sc.next();
                    tfEmailAddress.setText(emailAddress);
                    address = sc.next();
                    taAddress.setText(address);
                    break;
                }
                sc.nextLine();
            }


        } catch (FileNotFoundException fnf){
            fnf.getMessage();
        }


    }




    @Override
    public void start (Stage primaryStage) throws Exception{

        //Set the stage

        primaryStage = new Stage();
        primaryStage.setTitle("Contact Manager");
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);


        // Create a layout - two Vbox in a Hbox

        //Configure the listview so that it is always wide enough to show the full name of each contact

        // lvContactNames.setPrefWidth(biggestCellSize);


        VBox vb1 = new VBox();
        vb1.setPrefWidth(150);
        vb1.getChildren().addAll(lblContactNames, lvContactNames);
        vb1.setSpacing(10);
        vb1.setPadding(new Insets(13,0,13,13));

        VBox vb2 = new VBox();
        vb2.setPrefWidth(350);
        HBox hbClose = new HBox();
        hbClose.getChildren().add(btnClose);
        btnClose.setMinWidth(100);
        hbClose.setAlignment(Pos.BOTTOM_RIGHT);
        hbClose.setPadding(new Insets(40,0,0,0));
        vb2.getChildren().addAll(lblEmailAddress, tfEmailAddress, lblAddress,taAddress, hbClose);
        vb2.setSpacing(10);
        vb2.setPadding(new Insets(13));


        HBox hb = new HBox();
        hb.getChildren().addAll(vb1,vb2);


        //Create a scene

        Scene sc = new Scene(hb);

        //Set the scene on the stage

        primaryStage.setScene(sc);

        //Show the stage


        primaryStage.show();


    }

    public static void main(String args[]){
        launch(args);
    }

    public void exit(){
        Platform.exit();
    }


}