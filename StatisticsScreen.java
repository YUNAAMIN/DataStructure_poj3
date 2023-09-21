package application;

import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
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
import javafx.stage.FileChooser;

public class StatisticsScreen extends BorderPane {// this class is the statistics screen that show us using next and
													// previous the statistics of the martyr information.
	LocationList list;
	MartyrL r;
	TextField a, b, c, d, y;
	Node curr;
	TextArea ta;

	public StatisticsScreen(LocationList list) {
		this.list = list;
		this.r = new MartyrL();
		BorderPane pane = new BorderPane();
		VBox vbox = new VBox();
		vbox.setAlignment(Pos.CENTER);
		y = new TextField();
		a = new TextField();
		b = new TextField();
		c = new TextField();
		d = new TextField();
		vbox.setStyle("-fx-background-color: #F0E68C");
		Label welcomeLabel = new Label(" Statistics Screen...");
		welcomeLabel.setFont(new Font("Impact", 50));
		welcomeLabel.setTextFill(Color.web("#8B0000"));
		Image image = new Image("C:\\Users\\Yuna\\eclipse-workspace\\DataStructure3\\src\\application\\download.png");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(150);
		imageView.setFitHeight(150);
		Button prev = new Button("Previous");
		prev.setFont(new Font("Comic Sans MS", 15));
		prev.setStyle("-fx-background-color: #CD853F");
		Button next = new Button("Next");
		next.setFont(new Font("Comic Sans MS", 15));
		next.setStyle("-fx-background-color: #CD853F");
		HBox buttonBox = new HBox(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(y, prev, next);
		ta = new TextArea();

		vbox.getChildren().addAll(imageView, welcomeLabel, buttonBox, a, b, c, d, ta);
		setCenter(vbox);

		next.setOnAction(e -> {
			Next();
		});

		prev.setOnAction(e -> {
			Previous();
		});
	}

	private void Previous() {
		Alert alert;
		String val;
		String max;
		if (curr == null) {
			curr = list.getHead();
		} else {
			curr = curr.prev;
			if (curr == null) {
				curr = list.getTail();
			}
		}

		val = curr.data.getLocation();
		y.setText(val);
		a.setText("-->>THE NUMBER OF MARTYRS ON THIS LOCATION: " + curr.data.list_Martry.countN() + ".");
		b.setText("-->>THE HIEGHT OF THE MARTYR TREE IN LOCATION: " + curr.data.list_Martry.height() + ".");
		c.setText("-->>THE HIEGHT OF THE DATE TREE IN LOCATION: " + curr.data.list_Martry2.height() + ".");
		ta.setText("-->>THE TRAVERSE ORDER FOR THE DATE TREE:\n" + curr.data.list_Martry2.printBackward() + "\n"
				+ "-->>THE LEVELL BY LEVEL TRAVERSAL:\n" + levelByLevel());
		max = curr.data.list_Martry2.getMaxDate();
		if (max != null) {
			d.setText("-->>DATE WITH MAXIMUM MARTYRS: " + max.toString());
		} else {
			d.setText("-->>DATE WITH MAXIMUM MARTYRS: N/A");
		}

		if (val == null) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("No Previous val");
			alert.setHeaderText(null);
			alert.setContentText("There is no previous val!");
			alert.showAndWait();
		}
	}

	private void Next() {
		Alert alert;
		String val;
		String max;

		if (curr == null) {
			curr = list.getHead();
		} else {
			curr = curr.next;
			if (curr == null) {
				curr = list.getHead();
			}
		}

		val = curr.data.getLocation();
		y.setText(val);
		a.setText("-->>THE NUMBER OF MARTYRS ON THIS LOCATION: " + curr.data.list_Martry.countN() + ".");
		b.setText("-->>THE HEIGHT OF THE MARTYR TREE IN LOCATION: " + curr.data.list_Martry.height() + ".");
		c.setText("-->>THE HEIGHT OF THE DATE TREE IN LOCATION: " + curr.data.list_Martry2.height() + ".");
		ta.setText("-->>THE TRAVERSE ORDER FOR THE DATE TREE:\n" + curr.data.list_Martry2.printBackward() + "\n"
				+ "-->>THE LEVELL BY LEVEL TRAVERSAL:\n" + levelByLevel());
		max = curr.data.list_Martry2.getMaxDate();

		if (max != null) {
			d.setText("-->>DATE WITH MAXIMUM MARTYRS: " + max.toString());
		} else {
			d.setText("-->>DATE WITH MAXIMUM MARTYRS: N/A");
		}

		if (val == null) {
			alert = new Alert(Alert.AlertType.WARNING);
			alert.setTitle("No Next val");
			alert.setHeaderText(null);
			alert.setContentText("There is no next val!");
			alert.showAndWait();
		}
	}

	private void printLevel(TNode n, StringBuilder res, int i) {
		if (n == null) {
			return;
		}
		if (i > 1) {
			printLevel(n.getLeft(), res, i - 1);
			printLevel(n.getRight(), res, i - 1);
		}
		if (i == 1) {
			res.append(n.getData()).append(" ");
		}

	}

	public String levelByLevel() {
		StringBuilder res;
		TNode root;
		int h;
		root = curr.data.list_Martry.getRoot();
		res = new StringBuilder();
		if (root == null) {
			return res.toString();
		}
		h = curr.data.list_Martry.height();

		for (int i = 1; i <= h; i++) {
			printLevel(root, res, i);
			res.append("\n");
		}

		return res.toString();
	}

}
