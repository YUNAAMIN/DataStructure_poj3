package application;

import java.io.BufferedReader;
import java.util.GregorianCalendar;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.Scanner;

public class Main extends Application {//
	public static LocationList list = new LocationList();
	private FileChooser fileChooser;
	public static File file = new File("C:\\Users\\Yuna\\eclipse-workspace\\DataStructure3\\martyrs.txt");

	@Override
	public void start(Stage primaryStage) throws Exception {// the main scene

		BorderPane pane = new BorderPane();
		Button chooseFileBtn = new Button("Choose CSV file");
		chooseFileBtn.setFont(new Font("Comic Sans MS", 15));
		chooseFileBtn.setStyle("-fx-background-color: #CD853F");

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #F0E68C");

		Image labImage = new Image(
				"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj1\\src\\application\\images.jpg");
		ImageView ImageView = new ImageView(labImage);
		ImageView.setFitWidth(250);
		ImageView.setPreserveRatio(true);
		vbox.getChildren().addAll(ImageView, chooseFileBtn);
		MenuItem menuItem2 = new MenuItem("Location ");
		MenuItem menuItem3 = new MenuItem("Martyrs");
		MenuItem menuItem4 = new MenuItem("Statistics");
		MenuItem menuItem5 = new MenuItem("Save");
		Menu menu = new Menu("operation");
		menu.getItems().addAll(menuItem2, menuItem3, menuItem4, menuItem5);
		MenuBar menuBar = new MenuBar(menu);
		pane.setTop(menuBar);
		menuItem2.setOnAction(e -> {// location screen
			LocationScreen f = new LocationScreen(list);
			pane.setCenter(f);
			primaryStage.show();
		});
		menuItem3.setOnAction(e -> {// martry screen
			infoscreen m = new infoscreen(list);
			pane.setCenter(m);
			primaryStage.show();

		});
		menuItem4.setOnAction(e -> {// statistics screen
			StatisticsScreen p;
			try {
				p = new StatisticsScreen(list);
				pane.setCenter(p);
				primaryStage.show();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
		menuItem5.setOnAction(e -> {// save screen
			SaveScreen p;
			try {
				p = new SaveScreen(list, primaryStage);
				pane.setCenter(p);
				primaryStage.show();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

		});
		pane.setCenter(vbox);
		chooseFileBtn.setOnAction(e -> { // here we can choose the file that we will work on it
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle("Open CSV file");
			FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("CSV files (*.csv)", "*.csv");
			fileChooser.getExtensionFilters().add(extFilter);
			File selectedFile = fileChooser.showOpenDialog(primaryStage);
			if (selectedFile != null) {
				System.out.println(selectedFile.getAbsolutePath());
			}

		});

		Scene scene = new Scene(pane, 800, 700);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Martyr System :)");
		primaryStage.show();
	}

	public static void main(String args[]) throws Exception {// the main method
		csvFile(file);

		launch(args);
		// System.out.println("hey");
		System.out.println(list.getDataAsString());
//		return;
	}

	public static void csvFile(File file) throws Exception, ParseException {
		Scanner in = new Scanner(file);
		String line = in.nextLine();

		while (in.hasNextLine()) {
			line = in.nextLine();
			String[] martyr = line.trim().split(",");
			SimpleDateFormat dateFormat = new SimpleDateFormat("M/d/yyyy");

			if (martyr.length == 5) {
				String name = martyr[0].trim();
				String ageStr = martyr[1].trim();
				int age = 0;

				if (!ageStr.isEmpty()) {
					try {
						age = Integer.parseInt(ageStr);
					} catch (NumberFormatException e) {
					}
				}

				String city = martyr[2].trim();
				String dateStr = martyr[3].trim();
				char gender = martyr[4].charAt(0);

				if (!dateStr.equals("Date of death")) {
					Date date;
					try {
						date = dateFormat.parse(dateStr);
					} catch (ParseException e) {
						continue;
					}

					GregorianCalendar calendar = new GregorianCalendar();
					calendar.setTime(date);

					Node temp = list.search(city);

					if (temp == null) {
						MartyrL obj = new MartyrL(city);
						MartyrD m = new MartyrD(name, age, city, calendar, gender);
						Datestack d = new Datestack(calendar);
						obj.list_Martry.insert(m);
						obj.list_Martry2.insert(d);
						d.getStack().push(m);
						list.insertSorted1(obj);
					} else {
						MartyrD m = new MartyrD(name, age, city, calendar, gender);
						Datestack d = temp.data.list_Martry2.findNode(new Datestack(calendar));
						if (d == null) {
							d = new Datestack(calendar);
							temp.data.list_Martry2.insert(d);
						}
						d.getStack().push(m);
						temp.data.list_Martry.insert(m);
					}
				}
			} else {
				continue;
			}
		}
	}

}
