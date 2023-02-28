//Juan Diego Venegas Barreto 00209856
//Randall Mencias 00321469
// clase: Programacion Avanzada de Apps
//Proyecto 2. programa que usa tasks para calcular factorial

package com.example.proyecto_2;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import javafx.application.Platform;



public class MultiThreadGui extends Application {
    //initialize variables
    List<Future<BigInteger>> futures = new ArrayList<>();
    private int factorialof = 0;
    private int numThreads = 0;
    private Pairs[] pairs;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Create a new VBox to hold the initial scene
        VBox initialVBox = new VBox();
        initialVBox.setSpacing(10);
        initialVBox.setPadding(new Insets(10));
        initialVBox.setAlignment(Pos.CENTER);
        GridPane gridPane = new GridPane();
        // Add a label to prompt the user to enter the number of threads to test
        Label numThreadsLabel = new Label("Enter the number of threads to test:");
        initialVBox.getChildren().add(numThreadsLabel);
        TextField numThreadsTextField = new TextField();
        initialVBox.getChildren().add(numThreadsTextField);
        // Add a label to prompt the user to enter the number to calculate the factorial
        Label factorialofLabel = new Label("Enter the factorial:");
        initialVBox.getChildren().add(factorialofLabel);
        TextField factorialTextField = new TextField();
        initialVBox.getChildren().add(factorialTextField);

        // Add a button to submit the user's input
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            try {
                numThreads = Integer.parseInt(numThreadsTextField.getText());
                factorialof = Integer.parseInt(factorialTextField.getText());
                Divide divide = new Divide(factorialof, numThreads);
                pairs = divide.getPairs();
            } catch (NumberFormatException e) {
                // If the user entered an invalid number, display an error message
                Label errorLabel = new Label("Please enter a valid integer");
                initialVBox.getChildren().add(errorLabel);
            }
        });
        //add button to calculate with given variables
        Button runButton = new Button("Run");
        initialVBox.getChildren().add(submitButton);
        initialVBox.getChildren().add(runButton);
        runButton.setOnAction(event -> {
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            //loop to create multiple tasks and their respective labels
            for (int i = 1; i < numThreads + 1; i++) {
                Factorial task = new Factorial(pairs[i - 1].getStart(), pairs[i - 1].getEnd());
                Future<BigInteger> future = (Future<BigInteger>) executor.submit(task::call);
                futures.add(future);
                Label label = new Label();
                label.textProperty().bind(task.messageProperty());
                gridPane.add(label, 0, i);
            }
            //get and show results with their respective labels
            executor.submit(() -> {
                Platform.runLater(() -> {
                    Factorial noThread = new Factorial(1, factorialof);
                    Label lb = new Label();
                    noThread.call().toString();
                    gridPane.add(new Label("Resultado sin threads:  " +  noThread.getRunTime()), 0, numThreads+2);
                    gridPane.add(resultLabel(), 0, numThreads + 1);
                });
            });

        });
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.add(new Label("Thread #"), 0, 0);

        // Add column labels to the top row of the table

        initialVBox.getChildren().add(gridPane);
        // Create a new Scene with the initial VBox as its root node
        Scene initialScene = new Scene(initialVBox, 400, 300);

        // Set the title of the window and show it
        primaryStage.setTitle("Parallel Programming Task");
        primaryStage.setScene(initialScene);
        primaryStage.show();

    }
    //function to calculate and show result in label
    private Label resultLabel() {
        BigInteger results = BigInteger.ONE;
        for (Future<BigInteger> future1 : futures) {
            try {
                results = results.multiply((BigInteger)future1.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        Label label = new Label("Resultado con Threads: "+results.toString());
        return label;
    }

    public static void main(String[] args) {
        launch();
    }
}

