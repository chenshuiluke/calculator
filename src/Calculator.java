package calculator;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Calculator extends Application{
	public static void main(String[] args){
			Application.launch(args);
	}
	public void start(Stage primaryStage){
		primaryStage.setTitle("Calculator");
		primaryStage.setWidth(400);
		primaryStage.setHeight(600);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 400, 600, Color.WHITE);
		TextField equationTextField = new TextField();
		equationTextField.setEditable(false);
		root.setTop(equationTextField);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
