package javafx;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/*
 * This program displays a GUI useful for teachers
 * when they are grading assignments. It has two text
 * fields and a drop down menu, making it very simple
 * and easy to use.
 * 
 * @author Chris Phillips
 * @version 1.0
 * @date March 9, 2017
 */

public class Main extends Application{
	
	@SuppressWarnings("static-access")
	public void start(Stage primaryStage) {
		
		GridPane pane = new GridPane(); //Creating a new GridPane
		
		//Create an image object and make it viewable
		Image img = new Image("imgs/download.png");
		ImageView imgView = new ImageView(img);
		
		//Create two lines with an increased thickness
	    Line ln = new Line(0, 25, 160, 25);
	    ln.setStrokeWidth(2.5);
	    Line ln2 = new Line(0, 25, 160, 25);
	    ln2.setStrokeWidth(2.5);
	    
	    //Create a drop-down menu with three choices
	    ObservableList<String> ol = FXCollections.observableArrayList("Assignment 1","Assignment2","Test 1");
	    ComboBox cb = new ComboBox(ol);
	    
	    //Create the various objects that need to be added to the overall design
		Button addGradeButton = new Button("Add Grade");
	    Text gradeSheet = new Text("Grade Sheet");
	    Label name = new Label("Name:");
	    Label grade = new Label("Grade:");
	    Label assignment = new Label("Assignment:");
	    TextField nameText = new TextField();
	    TextField gradeText = new TextField();

	    //Set fonts of all the labels and increase the size of the button
	    gradeSheet.setFont(Font.font(24));
	    name.setFont(Font.font(16));
	    grade.setFont(Font.font(16));
	    assignment.setFont(Font.font(16));
	    addGradeButton.setPrefSize(100, 30);
	    
	    //Set up the spacing for the pane
	    pane.setPadding(new Insets(30, 30, 30, 30));
	    pane.setVgap(8);
	    
	    pane.setHalignment(imgView, HPos.RIGHT); //Set the image to appear on the right side of it's panel
	    
	    //Add all the Buttons, Labels, and TextFields to the GridPane
	    pane.add(gradeSheet, 0, 0);
	    pane.add(imgView, 1, 0);
	    pane.add(ln, 0, 1);
	    pane.add(ln2, 1, 1);
	    pane.add(name, 0, 2);
	    pane.add(nameText, 1, 2);
	    pane.add(grade, 0, 3);
	    pane.add(gradeText, 1, 3);
	    pane.add(assignment, 0, 4);
	    pane.add(cb, 1, 4);
	    pane.add(addGradeButton, 0, 5);
	    
	    //Create a new scene, set the title of the stage, add the scene to the stage, and show the stage
	    Scene scene = new Scene(pane, 375, 300);
	    primaryStage.setTitle("Enter Grades"); 
	    primaryStage.setScene(scene); 
	    primaryStage.show();
	}
	
	public static void main(String[] args) { 
		launch(args);
	}
}

