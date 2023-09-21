package application;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.io.BufferedWriter;
import javafx.stage.FileChooser;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class SaveScreen extends BorderPane {// this screen is save screen that save all the thing i do in the file i
											// choose by file chooser.
	LocationList list = Main.list;
	Stage primaryStage;
	AVL1 avl;
	TextArea ta;
	MartyrL r;

	public SaveScreen(LocationList list, Stage primaryStage) throws Exception {
		this.primaryStage = primaryStage;
		this.avl = new AVL1();
		this.r = new MartyrL();
		BorderPane pane = new BorderPane();
		VBox vbox = new VBox();
		ta = new TextArea();
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #F0E68C");
		Label welcomeLabel = new Label("Save Screen...");
		welcomeLabel.setFont(new Font("Impact", 50));
		welcomeLabel.setTextFill(Color.web("#8B0000"));
		Label l = new Label("Export file to save :");
		l.setFont(new Font("Comic Sans MS", 15));
		TextField t = new TextField();
		HBox b = new HBox(10);
		b.setAlignment(Pos.CENTER);
		final Image labImage = new Image(
				"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj1\\src\\application\\images.png");

		ImageView labImageView = new ImageView(labImage);
		labImageView.setFitWidth(200);
		labImageView.setPreserveRatio(true);
		Button export = new Button("ExportChooser ");
		export.setFont(new Font("Comic Sans MS", 15));
		export.setStyle("-fx-background-color: #CD853F");
		Button export2 = new Button("Export ");
		export2.setFont(new Font("Comic Sans MS", 15));
		export2.setStyle("-fx-background-color: #CD853F");
		b.getChildren().addAll(l, t, export2);
		vbox.getChildren().addAll(labImageView, welcomeLabel, b, ta);
		setCenter(vbox);

		export2.setOnAction(e -> {
			String s;
			FileChooser fileChooser;
			File file;
			FileWriter fileWriter;
			s = list.getDataAsString();
			ta.setText(s);

			fileChooser = new FileChooser();
			fileChooser.setTitle("Save List");
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Text Files (*.txt)", "*.txt");
			fileChooser.getExtensionFilters().add(extFilter);

			file = fileChooser.showSaveDialog(new Stage());
			if (file != null) {
				try {
					fileWriter = new FileWriter(file);
					fileWriter.write(list.getDataAsString());
					fileWriter.close();
					System.out.println("List saved to file successfully.yahoooo!!!!");
				} catch (IOException ex) {
					System.out.println("Error saving list to file.check out!!!");
					ex.printStackTrace();
				}
			}
		});

	}

}
