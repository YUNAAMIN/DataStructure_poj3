package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class infoscreen extends BorderPane {// this is the information screen that it can show the martyr information
											// and also can search ,insert,delete and update on the martyr information .
	                                         //that the information screen extends the border pane an
	LocationList list;
	MartyrL r = new MartyrL();
	GregorianCalendar datee;
	TextArea ta;

	public infoscreen(LocationList list) {
		this.list = list;
		BorderPane pane = new BorderPane();
		GridPane grid = new GridPane();
		TextField name = new TextField();
		TextField age = new TextField();
		TextField location = new TextField();
		TextField date = new TextField();
		TextField gender = new TextField();
		Label lbAdded = new Label("enter number ");

		Label namel = new Label("Name:");
		Label agel = new Label("Age:");
		Label locationl = new Label("Location:");
		Label datel = new Label("Date:");
		Label genderl = new Label("Gender:");
		namel.setFont(new Font("Comic Sans MS", 15));
		agel.setFont(new Font("Comic Sans MS", 15));
		locationl.setFont(new Font("Comic Sans MS", 15));
		datel.setFont(new Font("Comic Sans MS", 15));
		genderl.setFont(new Font("Comic Sans MS", 15));
		grid.setVgap(10);

		grid.add(namel, 0, 0);
		grid.add(name, 1, 0);
		grid.add(agel, 0, 2);
		grid.add(age, 1, 2);
		grid.add(locationl, 0, 3);
		grid.add(location, 1, 3);
		grid.add(datel, 0, 4);
		grid.add(date, 1, 4);
		grid.add(genderl, 0, 5);
		grid.add(gender, 1, 5);

		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #F0E68C");
		final Image labImage = new Image(
				"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj1\\src\\application\\giphy.gif");

		ImageView labImageView = new ImageView(labImage);
		labImageView.setFitWidth(200);
		labImageView.setPreserveRatio(true);
		Label welcomeLabel = new Label("Matryr Screen...");
		welcomeLabel.setFont(new Font("Impact", 50));
		welcomeLabel.setTextFill(Color.web("#8B0000"));
		ta = new TextArea();
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		Button insert = new Button("Insert ");
		insert.setFont(new Font("Comic Sans MS", 15));
		insert.setStyle("-fx-background-color: #CD853F");
		Button search = new Button("Search ");
		search.setFont(new Font("Comic Sans MS", 15));
		search.setStyle("-fx-background-color: #CD853F");
		Button update = new Button("Update");
		update.setFont(new Font("Comic Sans MS", 15));
		update.setStyle("-fx-background-color: #CD853F");
		Button delete = new Button("Delete");
		delete.setFont(new Font("Comic Sans MS", 15));
		delete.setStyle("-fx-background-color: #CD853F");

		buttonBox.getChildren().addAll(insert, delete, update, search);
		grid.setAlignment(Pos.CENTER);
		Label lbMsg = new Label();
		vbox.getChildren().addAll(labImageView, welcomeLabel, grid, lbMsg, ta, buttonBox);
		ta.setVisible(false);
		setCenter(vbox);
		setBottom(buttonBox);

		insert.setOnAction(e -> {
			String nameV, ageV, locationV, dateV, genderV;
			GregorianCalendar calendar;
			MartyrD record;
			Node locationNode;
			Datestack datestack;
			AVL2 avl2;
			TNode2 avl2Node;
			nameV = name.getText();
			ageV = age.getText();
			locationV = location.getText().toLowerCase();
			dateV = date.getText();
			genderV = gender.getText();

			if (nameV.isEmpty() || ageV.isEmpty() || locationV.isEmpty() || dateV.isEmpty() || genderV.isEmpty()) {
				lbMsg.setText("you miss an empty fields please check and fill them up please>>>.");
				lbMsg.setTextFill(Color.RED);
				return;
			}
			if (!genderV.equalsIgnoreCase("m") && !genderV.equalsIgnoreCase("f")) {
				lbMsg.setText("Invalid gender. Please enter 'F' or 'M' to accept ur value,,,,.");
				lbMsg.setTextFill(Color.RED);
				return;
			}
			if (!ageV.matches("\\d+")) {
				lbMsg.setText("Invalid age. Please enter a numeric to accept ur value,,,.");
				lbMsg.setTextFill(Color.RED);
				return;
			}
			calendar = parseDate(dateV);
			if (calendar == null) {
				lbMsg.setText("Invalid date format u should write the date in this format->>\"M/d/yyyy.");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			record = new MartyrD(nameV, Integer.parseInt(ageV), locationV, calendar, genderV.charAt(0));

			locationNode = list.search(locationV);
			if (locationNode != null) {
				locationNode.data.list_Martry.insert(record);
				locationNode.data.s.push(record);
			} else {
				lbMsg.setText("Location not found!!.");
				lbMsg.setTextFill(Color.RED);
				return;
			}
			datestack = new Datestack(calendar);
			avl2 = locationNode.data.list_Martry2;
			avl2Node = avl2.search(datestack);
			if (avl2Node != null) {
				avl2Node.getData().getStack().push(record);
			} else {
				avl2.insert(datestack);
				avl2Node = avl2.search(datestack);
				avl2Node.getData().getStack().push(record);
			}

			lbMsg.setText("Record inserted successfully.yahoooo!!!");
			lbMsg.setTextFill(Color.GREEN);
		});

		delete.setOnAction(e -> {
			Node locationNode;
			String nameV, locationV;
			MartyrD martyr;
			Alert alert;
			Datestack datestack;
			AVL2 avl2;
			TNode2 avl2Node;
			boolean deleted;
			nameV = name.getText();
			locationV = location.getText().toLowerCase();

			if (nameV.isEmpty() || locationV.isEmpty()) {
				lbMsg.setText("Please fill in all fields. There is an empty field!!!!!");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			locationNode = list.search(locationV);
			if (locationNode == null) {
				lbMsg.setText("Location not found!!. Please try entering another location...");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			martyr = locationNode.data.list_Martry.searchN(nameV, locationNode);
			if (martyr == null) {
				alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Record Not Found");
				alert.setHeaderText(null);
				alert.setContentText("The record you are trying to delete does not exist.try another one.!!!");
				alert.showAndWait();
				lbMsg.setText("Record not found.");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			locationNode.data.list_Martry.deleteN(nameV);
			datestack = new Datestack(martyr.getDate());
			avl2 = locationNode.data.list_Martry2;
			avl2Node = avl2.search(datestack);
			if (avl2Node != null) {
				if (avl2Node.getData().getStack().getSize() > 1) {
					avl2Node.getData().getStack().remove(martyr);
				} else {
					avl2.delete(datestack);
					avl2Node.getData().getStack().pop();
				}
			}
			deleted = locationNode.data.list_Martry.searchN(nameV, locationNode) == null;

			if (deleted) {
				alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Node Already Deleted");
				alert.setHeaderText(null);
				alert.setContentText("The node has been deleted.checkout!!.");
				alert.showAndWait();
			} else {
				lbMsg.setText("Record deleted successfully!");
				lbMsg.setTextFill(Color.GREEN);
			}
		});

		update.setOnAction(e -> {
			Node locationNode;
			String nameV, locationV;
			MartyrD martyr;
			Alert alert;
			Datestack datestack;
			AVL2 avl2;
			TNode2 avl2Node;
			MartyrD updatedRecord;
			GregorianCalendar calendar;
			nameV = name.getText();
			locationV = location.getText().toLowerCase();

			if (nameV.isEmpty() || locationV.isEmpty()) {
				lbMsg.setText("Please fill in all fields!");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			locationNode = list.search(locationV);
			if (locationNode == null) {
				lbMsg.setText("Location not found!");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			martyr = locationNode.data.list_Martry.searchN(nameV, locationNode);
			if (martyr == null) {
				alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Record Not Found");
				alert.setHeaderText(null);
				alert.setContentText("The record you are trying to update does not exist!");
				alert.showAndWait();

				lbMsg.setText("Record not found!");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			locationNode.data.list_Martry.deleteN(nameV);
			datestack = new Datestack(martyr.getDate());
			avl2 = locationNode.data.list_Martry2;
			avl2Node = avl2.search(datestack);
			if (avl2Node != null) {
				if (avl2Node.getData().getStack().getSize() > 1) {
					avl2Node.getData().getStack().remove(martyr);
				} else {
					avl2.delete(datestack);
					avl2Node.getData().getStack().pop();
				}
			}

			String ageV = age.getText();
			String dateV = date.getText();
			String genderV = gender.getText();

			if (!genderV.equalsIgnoreCase("m") && !genderV.equalsIgnoreCase("f")) {
				lbMsg.setText("Invalid gender. Please enter 'F' or 'M'.");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			if (!ageV.matches("\\d+")) {
				lbMsg.setText("Invalid age. Please enter a numeric value!");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			calendar = parseDate(dateV);
			if (calendar == null) {
				lbMsg.setText("Invalid date format!");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			updatedRecord = new MartyrD(nameV, Integer.parseInt(ageV), locationV, calendar, genderV.charAt(0));
			locationNode.data.list_Martry.insert(updatedRecord);
			datestack = new Datestack(updatedRecord.getDate());
			avl2 = locationNode.data.list_Martry2;
			avl2Node = avl2.search(datestack);
			if (avl2Node != null) {
				avl2Node.getData().getStack().push(updatedRecord);
			} else {
				avl2.insert(datestack);
				avl2Node = avl2.search(datestack);
				avl2Node.getData().getStack().push(updatedRecord);
			}

			lbMsg.setText("Record updated successfully!");
			lbMsg.setTextFill(Color.GREEN);
		});

		search.setOnAction(e -> {
			String nameV, locationV;
			nameV = name.getText();
			Node locationNode;
			StringBuilder sb;
			locationV = location.getText().toLowerCase();

			if (nameV.isEmpty() || locationV.isEmpty()) {
				lbMsg.setText("Please fill in all fields.");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			locationNode = list.search(locationV);
			if (locationNode == null) {
				lbMsg.setText("Location not found.");
				lbMsg.setTextFill(Color.RED);
				return;
			}

			sb = new StringBuilder();
			searchM(nameV, sb, locationNode);

			if (sb.length() == 0) {
				lbMsg.setText("No records found.try another thing,,,");
				lbMsg.setTextFill(Color.RED);
			} else {
				ta.setText(sb.toString());
				ta.setVisible(true);
			}
		});
	}

	private GregorianCalendar parseDate(String dateS) {
		SimpleDateFormat dateFormat;
		Date date;
		GregorianCalendar calendar;
		dateFormat = new SimpleDateFormat("M/d/yyyy");
		try {
			date = dateFormat.parse(dateS);
			calendar = new GregorianCalendar();
			calendar.setTime(date);
			return calendar;
		} catch (ParseException e) {
			return null;
		}
	}

	private void searchM(String nam, StringBuilder sb, Node loc) {
		AVL1 avl;
		if (loc != null) {
			avl = loc.data.list_Martry;
			searchM(nam, sb, avl.getRoot());
		}
	}

	private void searchM(String nam, StringBuilder sb, TNode node) {
		if (node != null) {
			if (node.getData().getName().startsWith(nam)) {
				sb.append(node.getData().toString()).append("\n");
			}
			searchM(nam, sb, node.getLeft());
			searchM(nam, sb, node.getRight());
		}
	}
}
