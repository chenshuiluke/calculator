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
		int windowWidth = 95;
		int windowHeight = 250;
		primaryStage.setTitle("Calculator");
		primaryStage.setWidth(windowWidth);
		primaryStage.setHeight(windowHeight);
		primaryStage.setResizable(false);
		BorderPane root = new BorderPane();
		Scene scene = new Scene(root, windowWidth, windowHeight, Color.WHITE);

		TextField equationTextField = new TextField();
		equationTextField.setEditable(false);
		root.setTop(equationTextField);

		GridPane numberGrid = new GridPane();
		numberGrid.setHgap(5);
		numberGrid.setVgap(5);

		GridPane operatorGrid = new GridPane();
		operatorGrid.setHgap(5);
		operatorGrid.setVgap(5);
/*
		numberGrid.setGridLinesVisible(true);
		operatorGrid.setGridLinesVisible(true);
*/


		int buttonCounter = 0;
		int buttonWidth = 500;
		int buttonHeight = 500;
		for(int y = 0; y < 3; y++){
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(20);
			numberGrid.getRowConstraints().add(row);


			for(int x = 0; x < 3 && buttonCounter < 10; x++, buttonCounter++){
				if(y == 0){
					ColumnConstraints column = new ColumnConstraints();
					column.setPercentWidth(33);
					column.setHalignment(HPos.CENTER);
				}

				Button button = new Button(new String().valueOf(buttonCounter));
				numberGrid.add(button, x, y);
			}
			if(buttonCounter == 9){
				Button button = new Button(new String().valueOf(buttonCounter));
				numberGrid.add(button, 0, y+1);
			}
		}
		String[][] operatorTextArr = {{"=", "+"}, {"-", "*"}, {"/", "c"}};
		for(int y = 0; y < operatorTextArr.length; y++){
				for(int x = 0; x < operatorTextArr[y].length; x++){
					Button button = new Button(operatorTextArr[y][x]);
					operatorGrid.add(button, y, x);
				}
		}

		root.setCenter(numberGrid);
		root.setBottom(operatorGrid);

		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
