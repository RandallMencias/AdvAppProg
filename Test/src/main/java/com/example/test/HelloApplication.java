//Juan Diego Venegas Barreto 00209856
//Randall Mencias 00321469
// clase: Programacion Avanzada de Apps
//Proyecto 2. programa que usa tasks para calcular factorial

package com.example.test;

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
import javafx.scene.text.Text;


public class HelloApplication extends Application {
    //initialize variables
    private static final String[] COLUMN_NAMES = {"  Thread #  ", "  Process  ", "  RunTime  "};
    private int factorialof = 0;
    private int numThreads = 0;
    BigInteger results = BigInteger.ONE;
    //variables for runtime
    private Factorial task;
    Thread[] threads;
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
        // Add a label to prompt the user to enter the number to calculate the factorial
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
    //scene for the result
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
        //place nodes on GridPane
        gridPane.add(new Label("  Resultado:  "), 0, numThreads + 1);

        Divide divide = new Divide(factorialof, numThreads);
        Pairs[] pair = divide.getPairs();
        //create multiple threads and a respective factorial object
        threads = new Thread[numThreads];
        List<Text> textList = new ArrayList<>();
        for(int i = 0; i<numThreads;i++){
            textList.add(new Text(""));
            gridPane.add(textList.get(i),1,i+1);
        }
        
        for (int i = 0; i < numThreads; i++) {
             // create task
            task = new Factorial(pair[i].getStart(), pair[i].getEnd());
            Text runTimeText = new Text();
            //label to show the process
            textList.get(i).textProperty().bind(task.messageProperty());
            //label to get the result
            threads[i] = new Thread(() -> {
                // Ejecutar el método call() en la instancia de MyClass y guardar el resultado en la lista de resultados
                BigInteger result = task.call();
                runTimeText.setText(task.getRunTime() + " ms");
                results = results.multiply(result);
            });
            //add labels to GridPane
            threads[i].setDaemon(true);
            gridPane.add(runTimeText, 2, i + 1);
            threads[i].start();
            //wait for threads to finish
            try {
                threads[i].join();
            } catch (Exception e) {
            }
        }
        //set resulttext
        Text resultText = new Text();
        
        resultText.setText(results + "");
        gridPane.add(resultText, 1, numThreads + 1);

        //Labels for factorial without task
        Label title2 = new Label("Sin usar Tasks");
        Label lb = new Label("Factorial de: " + factorialof);
        Label result2 = new Label();
        //place labels
        gridPane.add(title2, 1, numThreads + 2);
        gridPane.add(lb, 0, numThreads + 3);
        

        //initialize ONE thread to determine a factorial
        BigInteger resultNoTask;
        Factorial fac2 = new Factorial(1, factorialof);
        Thread T1 = new Thread(fac2);
        T1.start();
        resultNoTask = fac2.call();
        try {
            T1.join();
        } catch (Exception e) {
        }
        //edit and place labels
        result2.setText("  " + resultNoTask + "  ");
        Label runTimeLabel2 = new Label();
        runTimeLabel2.setText(fac2.getRunTime() + " ms");
        gridPane.add(runTimeLabel2, 2, numThreads + 3);
        gridPane.add(result2, 1, numThreads + 3);

        Scene resultsScene = new Scene(gridPane, 400, 300);
        return resultsScene;
    }

    public static void main(String[] args) {
        launch();
    }
}