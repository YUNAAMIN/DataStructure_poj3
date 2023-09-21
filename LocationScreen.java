package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Optional;
import java.util.Scanner;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LocationScreen extends BorderPane {// this screen show the location information.
	LocationList list = Main.list;
	MartyrL l;
	TextField t;
	private Node curr;

	public LocationScreen(LocationList list) {
		this.list = list;
		BorderPane pane = new BorderPane();
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		vbox.setStyle("-fx-background-color: #F0E68C");
		Label welcomeLabel = new Label("Location Screen...");
		welcomeLabel.setFont(new Font("Impact", 50));
		welcomeLabel.setTextFill(Color.web("#8B0000"));
		Label l = new Label("Location :");
		l.setFont(new Font("Comic Sans MS", 15));
		t = new TextField();
		HBox b = new HBox(10);
		Button next = new Button("Next");
		next.setFont(new Font("Comic Sans MS", 15));
		next.setStyle("-fx-background-color: #CD853F");
		Button prev = new Button("Previus");
		prev.setFont(new Font("Comic Sans MS", 15));
		prev.setStyle("-fx-background-color: #CD853F");
		b.getChildren().addAll(l, t, next, prev);
		b.setAlignment(Pos.CENTER);
		Image image = new Image(
				"C:\\Users\\Yuna\\eclipse-workspace\\DataStructure_proj1\\src\\application\\image_processing20191230-31370-1w5tizz.gif");

		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(150);
		imageView.setFitHeight(150);
		TextArea a = new TextArea();
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

		Label lbMsg = new Label("");
		lbMsg.setTextFill(Color.RED);
		lbMsg.setFont(new Font(20));

		buttonBox.getChildren().addAll(insert, update, delete, search);
		vbox.getChildren().addAll(imageView, welcomeLabel, b, lbMsg, a, buttonBox);

		setCenter(vbox);
		setBottom(buttonBox);
		next.setOnAction(e -> {
			Next();
		});

		prev.setOnAction(e -> {
			Previous();
		});
		search.setOnAction(e -> {
			String brand;
			String city;
			Node persons;
			Alert alert;
			brand = t.getText();
			if (!brand.isEmpty() && brand.matches("[a-zA-Z_-]+")) {
				city = t.getText();
				System.out.println(city);
				persons = list.search(city);
				if (persons != null) {
					a.setText(persons.data.list_Martry.getAllMartyr());
				} else {
					alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("No Car Selected");
					alert.setHeaderText(null);
					alert.setContentText("No matching brand found!!!!");
					alert.showAndWait();
				}
			} else {
				alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Invalid Input");
				alert.setHeaderText(null);
				alert.setContentText("Please enter a valid brand name");
				alert.showAndWait();
			}
		});

		update.setOnAction(e -> {
			Node node;
			TextInputDialog dialog;
			String old;
			String newl;
			Optional<String> res;
			Alert alert;
			old = t.getText();
			if (!old.isEmpty()) {
				node = list.search(old);
				if (node != null) {
					dialog = new TextInputDialog(old);
					dialog.setTitle("Update Location");
					dialog.setHeaderText(null);
					dialog.setContentText("Enter the new location:");

					res = dialog.showAndWait();
					if (res.isPresent()) {
						newl = res.get();
						node.data.setLocation(newl);
						a.setText(
								"The location '" + old + "' has been updated to '" + newl + "' successfully!!yahooooo");
					}
				} else {
					alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Location Not Found");
					alert.setHeaderText(null);
					alert.setContentText("The location '" + old + "' does not exist!!!!");
					alert.showAndWait();
				}
			} else {
				alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Invalid Input.try again");
				alert.setHeaderText(null);
				alert.setContentText("Please enter a valid location!!!!");
				alert.showAndWait();
			}
		});

		insert.setOnAction(e -> {
			String location;
			Node node;
			MartyrL newL;
			Alert alert;
			location = t.getText();
			if (!location.isEmpty()) {
				node = list.search(location);
				if (node == null) {
					newL = new MartyrL(location);
					list.insertNode(newL);
					a.setText("The location '" + location + "' has been added successfully!!yahoooo");
				} else {
					alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Location Already Exists!!!!");
					alert.setHeaderText(null);
					alert.setContentText("The location '" + location + "' already exists!,,,");
					alert.showAndWait();
				}
			} else {
				alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Invalid Input!!!!");
				alert.setHeaderText(null);
				alert.setContentText("Please enter a valid location!!!");
				alert.showAndWait();
			}
		});

		delete.setOnAction(e -> {
			String location = t.getText();
			if (!location.isEmpty()) {
				Node node = list.search(location);
				if (node != null) {
					list.removeNode(node);
					a.setText("The location '" + location + "' has been deleted successfully!!");
				} else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Location Not Found");
					alert.setHeaderText(null);
					alert.setContentText("The location '" + location + "' does not exist!");
					alert.showAndWait();
				}
			} else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.setTitle("Invalid Input");
				alert.setHeaderText(null);
				alert.setContentText("Please enter a valid location!");
				alert.showAndWait();
			}
		});

	}

	private void Previous() {
		String val;
		Alert alert;
		if (curr == null) {
			curr = list.getHead();
		} else {
			curr = curr.prev;
			if (curr == null) {
				curr = list.getTail();
			}
		}

		val = curr.data.getLocation();
		t.setText(val);
		if (val == null) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("No Previous val,,,");
			alert.setHeaderText(null);
			alert.setContentText("There is no previous val!!!check out,,,");
			alert.showAndWait();
		}
	}

	private void Next() {
		String val;
		Alert alert;
		if (curr == null) {
			curr = list.getHead();
		} else {
			curr = curr.next;
			if (curr == null) {
				curr = list.getHead();
			}
		}

		val = curr.data.getLocation();
		t.setText(val);
		if (val == null) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("No Next val,,,");
			alert.setHeaderText(null);
			alert.setContentText("There is no next val!!!check out,,,");
			alert.showAndWait();
		}
	}

}
