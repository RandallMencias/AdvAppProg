package com.example.test;

import javafx.application.Application;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import javafx.application.Platform;

public class HelloApplication extends Application {

    private static final String[] COLUMN_NAMES = {"  Thread #  ", "  Process  ", "  RunTime  "};
    private int factorialof = 0;
    private int numThreads = 0;
    BigInteger results = BigInteger.ONE;
    long startTime = System.currentTimeMillis();
    long endTimeT;

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Create a new VBox to hold the initial scene
        VBox initialVBox = new VBox();
        initialVBox.setSpacing(10);
        initialVBox.setPadding(new Insets(10));
        initialVBox.setAlignment(Pos.CENTER);

        // Add a label to prompt the user to enter the number of threads to test
        Label numThreadsLabel = new Label("Enter the number of threads to test:");
        initialVBox.getChildren().add(numThreadsLabel);
        TextField numThreadsTextField = new TextField();
        initialVBox.getChildren().add(numThreadsTextField);

        Label factorialofLabel = new Label("Enter the factorial:");
        initialVBox.getChildren().add(factorialofLabel);
        TextField factorialTextField = new TextField();
        initialVBox.getChildren().add(factorialTextField);

        // Add a button to submit the user's choice and switch to the results scene
        Button submitButton = new Button("Submit");
        submitButton.setOnAction(event -> {
            try {
                numThreads = Integer.parseInt(numThreadsTextField.getText());
                factorialof = Integer.parseInt(factorialTextField.getText());
                primaryStage.setScene(createResultsScene());
            } catch (NumberFormatException e) {
                // If the user entered an invalid number, display an error message
                Label errorLabel = new Label("Please enter a valid integer");
                initialVBox.getChildren().add(errorLabel);
            }
        });

        initialVBox.getChildren().add(submitButton);

        // Create a new Scene with the initial VBox as its root node
        Scene initialScene = new Scene(initialVBox, 400, 300);

        // Set the title of the window and show it
        primaryStage.setTitle("Parallel Programming Task");
        primaryStage.setScene(initialScene);
        primaryStage.show();
    }

    private Scene createResultsScene() {

        // Create a new GridPane to display the results
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        // Add column labels to the top row of the table
        for (int i = 0; i < COLUMN_NAMES.length; i++) {
            Label label = new Label(COLUMN_NAMES[i]);
            gridPane.add(label, i, 0);
        }

        // Add a row for each thread
        for (int i = 0; i < numThreads; i++) {

            // Add a label to display the thread number
            Label threadLabel = new Label("  Thread #" + (i + 1) + "  ");
            gridPane.add(threadLabel, 0, i + 1);

            // Add labels to display the execution time and memory usage
            Label executionTimeLabel = new Label();
            Label memoryUsageLabel = new Label();
            gridPane.add(executionTimeLabel, 1, i + 1);
            gridPane.add(memoryUsageLabel, 2, i + 1);
        }
        gridPane.add(new Label("  Resultado:  "), 0, numThreads + 1);

        Divide divide = new Divide(factorialof, numThreads);
        Pairs[] pair = divide.getPairs();
        Thread[] threads = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            Factorial factorial = new Factorial(pair[i].getStart(), pair[i].getEnd());
            Label resultLabel = new Label();
            Label runTimeLabel = new Label();
            factorial.messageProperty().addListener((observable, oldValue, newValue) -> {
                endTimeT = System.currentTimeMillis();
                resultLabel.setText("  " + newValue.toString() + "  ");
                runTimeLabel.setText((endTimeT - startTime) + " ms");
            });

            threads[i] = new Thread(() -> {
                // Ejecutar el método call() en la instancia de MyClass y guardar el resultado en la lista de resultados
                BigInteger result = factorial.call();
                results = results.multiply(result);
            });

            //System.out.printf("Suma via Threads: %d toma: %d miliscd\n\n", counterThread.getTotalGeneral(), );
            threads[i].setDaemon(true);
            gridPane.add(resultLabel, 1, i + 1);
            gridPane.add(runTimeLabel, 2, i + 1);
            threads[i].start();

        }

        Label resultLabel = new Label();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            resultLabel.setText(results + "");
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        gridPane.add(resultLabel, 1, numThreads + 1);

        Scene resultsScene = new Scene(gridPane, 400, 300);
        return resultsScene;
    }

    public static void main(String[] args) {
        launch();
    }

}