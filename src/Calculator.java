package calculator;

import javafx.application.Application;

import javafx.scene.Scene;
import javafx.scene.Group;

import javafx.scene.control.Label;
import javafx.scene.control.Button;

import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;

import javafx.scene.paint.Color;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.stage.Stage;

import javafx.geometry.HPos;

import java.util.ArrayList;

import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;

public class Calculator extends Application{
	private boolean operatorAlreadyPressed = false;
	private boolean secondOperand = false;
	private boolean scriptExceptionOccurred = false;
	public static void main(String[] args){
			Application.launch(args);
	}
	public void start(Stage primaryStage) throws ScriptException{
		int windowWidth = 190;
		int windowHeight = 250;
		primaryStage.setTitle("Calculator");
		primaryStage.setWidth(windowWidth);
		primaryStage.setHeight(windowHeight);
//		primaryStage.setResizable(false);
		VBox root = new VBox();
		Scene scene = new Scene(root, windowWidth, windowHeight, Color.WHITE);

		Label expressionLabel = new Label();
//		expressionLabel.setEditable(false);

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

		ArrayList<Button> numberButtons = new ArrayList<>();
		ArrayList<Button> operatorButtons = new ArrayList<>();

		int buttonCounter = 0;
		int buttonWidth = 500;
		int buttonHeight = 500;
		for(int y = 0; y < 2; y++){
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(20);
			numberGrid.getRowConstraints().add(row);
			for(int x = 0; x < 5 && buttonCounter < 10; x++, buttonCounter++){
				if(y == 0){
					ColumnConstraints column = new ColumnConstraints();
					column.setPercentWidth(33);
					column.setHalignment(HPos.CENTER);
					numberGrid.getColumnConstraints().add(column);
				}
				Button button = new Button(String.valueOf(buttonCounter));
				button.setPrefWidth(buttonWidth);
				button.setPrefHeight(buttonHeight);
				numberButtons.add(button);
				numberGrid.add(button, x, y);
			}
		}
		String[][] operatorTextArr = {{"+", "-"}, {"*", "/"}, {"=", "c"}};
		for(int y = 0; y < operatorTextArr.length; y++){
			RowConstraints row = new RowConstraints();
			row.setPercentHeight(20);
			operatorGrid.getRowConstraints().add(row);
				for(int x = 0; x < operatorTextArr[y].length; x++){
					if(y == 0){
						ColumnConstraints column = new ColumnConstraints();
						column.setPercentWidth(33);
						column.setHalignment(HPos.CENTER);
						operatorGrid.getColumnConstraints().add(column);
					}
					Button button = new Button(operatorTextArr[y][x]);
					button.setPrefWidth(buttonWidth);
					button.setPrefHeight(buttonHeight);
					operatorButtons.add(button);
					operatorGrid.add(button, y, x);
				}
		}

		//Makes clicking the number buttons add to the expressionLabel
		for(int counter = 0; counter < numberButtons.size(); counter++){
			numberButtons.get(counter).setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e){
					Button temp = (Button)e.getSource();
					if(scriptExceptionOccurred){
						expressionLabel.setText("");
						scriptExceptionOccurred = false;
					}
					String newexpressionText = expressionLabel.getText() + temp.getText();
					/*
					Long test = Long.valueOf(newexpressionText);
					if(test < Integer.MAX_VALUE){
					}
					*/
					expressionLabel.setText(newexpressionText);
					System.out.println(newexpressionText);
					if(operatorAlreadyPressed && !secondOperand){
						secondOperand = true;
					}
				}
			});
		}

		for(int counter = 0; counter < operatorButtons.size(); counter++){
			operatorButtons.get(counter).setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e){
					Button temp = (Button)e.getSource();
					if(scriptExceptionOccurred){
						expressionLabel.setText("");
						scriptExceptionOccurred = false;
					}
					if(temp.getText().equals("c") && expressionLabel.getText().length() > 0){
						String text = expressionLabel.getText();
						if(text.charAt(text.length() - 1) == '+' || text.charAt(text.length() - 1) == '-'
							|| text.charAt(text.length() - 1) == '*' || text.charAt(text.length() - 1) == '/'){
							operatorAlreadyPressed = false;
						}
						expressionLabel.setText(expressionLabel.getText().substring(
							0, expressionLabel.getText().length() - 1));
					}
					else if(!operatorAlreadyPressed && expressionLabel.getText().length() > 0 && !temp.getText().equals("=")){
						String newexpressionText = expressionLabel.getText()
							+ temp.getText();
						expressionLabel.setText(newexpressionText);
						operatorAlreadyPressed = true;
					}
					else if(secondOperand || temp.getText().equals("=")){
						operatorAlreadyPressed = false;
						secondOperand = false;
						try{
							ScriptEngineManager mgr = new ScriptEngineManager();
					    ScriptEngine engine = mgr.getEngineByName("JavaScript");
							expressionLabel.setText(String.valueOf(engine.eval(
								expressionLabel.getText())));
							if(!temp.getText().equals("=") && !temp.getText().equals("c")){
								expressionLabel.setText(expressionLabel.getText()
								 + temp.getText());
								operatorAlreadyPressed = true;
							}
						}
						catch(ScriptException exc){
							expressionLabel.setText("Invalid operation.");
							scriptExceptionOccurred = true;
							secondOperand = false;
							operatorAlreadyPressed = false;
						}
					}
				}
			});
		}

		root.getChildren().addAll(expressionLabel, numberGrid, operatorGrid);

		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.show();
	}
}
