package calculator;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.Group;

import javafx.scene.control.TextField;
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;

import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.HPos;

public class Calculator extends Application{
	public static void main(String[] args){
			Application.launch(args);
	}
	public void start(Stage primaryStage){
		primaryStage.setTitle("Calculator");
		primaryStage.setWidth(100);
		primaryStage.setHeight(200);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, 100, 200, Color.WHITE);

		TextField equationTextField = new TextField();
		equationTextField.setEditable(false);
		root.setTop(equationTextField);

		GridPane calculatorPane = new GridPane();
		calculatorPane.setHgap(5);
		calculatorPane.setVgap(5);
	//	calculatorPane.setGridLinesVisible(true);


		int buttonCounter = 1;
		for(int y = 0; y < 3; y++){
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(33);
			calculatorPane.getRowConstraints().add(row);

			ColumnConstraints column = new ColumnConstraints();
			column.setPercentWidth(33);
			column.setHalignment(HPos.CENTER);
			calculatorPane.getColumnConstraints().add(column);

			for(int x = 0; x < 3; x++, buttonCounter++){
				Button button = new Button(new String().valueOf(buttonCounter));
				button.setPrefWidth(500);
				button.setPrefHeight(500);
				calculatorPane.add(button, x, y);
			}
		}

		root.setCenter(calculatorPane);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
