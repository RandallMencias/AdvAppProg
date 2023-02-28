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
    private static final String[] COLUMN_NAMES = {"  Thread #  ", "  Process  ", "  RunTime  "};
    List<Future<BigInteger>> futures = new ArrayList<>();
    private int factorialof = 0;
    private int numThreads = 0;

    Pairs[] pairs;

    //variables for runtime
//    private Factorial task;
    Thread[] threads;
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

        // Add a button to submit the user's choice and switch to the results scene
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
        Button runButton = new Button("Run");
        initialVBox.getChildren().add(submitButton);
        initialVBox.getChildren().add(runButton);


        runButton.setOnAction(event -> {
            ExecutorService executor = Executors.newFixedThreadPool(numThreads);
            for (int i = 1; i < numThreads + 1; i++) {
                Factorial task = new Factorial(pairs[i - 1].getStart(), pairs[i - 1].getEnd());
                Future<BigInteger> future = (Future<BigInteger>) executor.submit(task::call);
                futures.add(future);
                Label label = new Label("Thread " + i);
                label.textProperty().bind(task.messageProperty());
                gridPane.add(label, 0, i);
            }
            executor.submit(() -> {
                Platform.runLater(() -> {
                    Factorial noThread = new Factorial(1, factorialof);
                    gridPane.add(new Label("  Resultado sin threads:  "+ noThread.call().toString()), 0, numThreads+2);
                    gridPane.add(resultLabel(), 0, numThreads + 1);
                });
            });
//        try {
//                executor.awaitTermination(13, TimeUnit.SECONDS);{
//                    gridPane.add(resultLabel(), 0, numThreads + 1);
//            }
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


        });

//                    for (Future<BigInteger> future : futures) {
//                        try {
////                            results = results.multiply((BigInteger)future.get());
//                            System.out.println(future.get());
//                        } catch (InterruptedException | ExecutionException e) {
//                            throw new RuntimeException(e);
//                        }
//                    }
//                        gridPane.add(new Label(results.toString()), 1, numThreads + 1);





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
    private Label resultLabel() {
        BigInteger results = BigInteger.ONE;
        for (Future<BigInteger> future1 : futures) {
            try {
                results = results.multiply((BigInteger)future1.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        }
        Label label = new Label("Result:"+results.toString());
        return label;
    }
//    private void function(GridPane gridPane){
//        for (int i = 0; i < COLUMN_NAMES.length; i++) {
//            Label label = new Label(COLUMN_NAMES[i]);
//            gridPane.add(label, i, 0);
//        }
//
//        // Add a row for each thread
//        for (int i = 0; i < numThreads; i++) {
//            Factorial task = new Factorial(factorialof, numThreads);
//
//            // Add a label to display the thread number
//            Label threadLabel = new Label("  Thread #" + (i + 1));
//            gridPane.add(threadLabel, 0, i + 1);
//
//            // Add labels to display the execution time and memory usage
//            Label executionTimeLabel = new Label();
//            Label memoryUsageLabel = new Label();
//            gridPane.add(executionTimeLabel, 1, i + 1);
//            gridPane.add(memoryUsageLabel, 2, i + 1);
//        }
//        //place nodes on GridPane
//        gridPane.add(new Label("  Resultado:  "), 0, numThreads + 1);
//
//        Divide divide = new Divide(factorialof, numThreads);
//        Pairs[] pair = divide.getPairs();
//        //create multiple threads and a respective factorial object
//        threads = new Thread[numThreads];
//        List<Text> textList = new ArrayList<>();
//        for(int i = 0; i<numThreads;i++){
//            textList.add(new Text(""));
//            gridPane.add(textList.get(i),1,i+1);
//        }
//
//        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
//
//        for (int i = 0; i < numThreads; i++) {
//            // create task
//            Task task = new Factorial(pair[i].getStart(), pair[i].getEnd());
//            executor.execute(task);
//            results = results.multiply(((Factorial) task).call());
//            Text runTimeText = new Text();
//            //label to show the process
//            textList.get(i).textProperty().bind(task.messageProperty());
//
//            //label to get the result
////            threads[i] = new Thread(() -> {
////                // Ejecutar el método call() en la instancia de MyClass y guardar el resultado en la lista de resultados
////                BigInteger result = task.call();
////                runTimeText.setText(task.getRunTime() + " ms");
////                results = results.multiply(result);
////            });
//            //add labels to GridPane
////            threads[i].setDaemon(true);
////            gridPane.add(runTimeText, 2, i + 1);
////            threads[i].start();
////            //wait for threads to finish
////            try {
////                threads[i].join();
////            } catch (Exception e) {
////            }
//        }
//        //set resultext
//
//
//            if (executor.isTerminated()) {
//                System.out.println("Finished all threads");
//                Text resultText = new Text();
//
//                resultText.setText(results + "");
//                gridPane.add(resultText, 1, numThreads + 1);
//
//
//
//
//
//        //Labels for factorial without task
//        Label title2 = new Label("Sin usar Tasks");
//        Label lb = new Label("Factorial de: " + factorialof);
//        Label result2 = new Label();
//        //place labels
//        gridPane.add(title2, 1, numThreads + 2);
//        gridPane.add(lb, 0, numThreads + 3);
//
//
//        //initialize ONE thread to determine a factorial
//        BigInteger resultNoTask;
//        Factorial fac2 = new Factorial(1, factorialof);
//        Thread T1 = new Thread(fac2);
//        T1.start();
//        resultNoTask = fac2.call();
//        try {
//            T1.join();
//        } catch (Exception e) {
//        }
//        //edit and place labels
//        result2.setText("  " + resultNoTask + "  ");
//        Label runTimeLabel2 = new Label();
//        runTimeLabel2.setText(fac2.getRunTime() + " ms");
//        gridPane.add(runTimeLabel2, 2, numThreads + 3);
//        gridPane.add(result2, 1, numThreads + 3);
//
//    }}

    //scene for the result
//    private Scene createResultsScene() {
//
//        // Create a new GridPane to display the results
//        GridPane gridPane = new GridPane();
//        gridPane.setPadding(new Insets(10));
//        gridPane.setHgap(10);
//        gridPane.setVgap(10);
//
//        // Add column labels to the top row of the table
//        for (int i = 0; i < COLUMN_NAMES.length; i++) {
//            Label label = new Label(COLUMN_NAMES[i]);
//            gridPane.add(label, i, 0);
//        }
//
//        // Add a row for each thread
//        for (int i = 0; i < numThreads; i++) {
//
//            // Add a label to display the thread number
//            Label threadLabel = new Label("  Thread #" + (i + 1) + "  ");
//            gridPane.add(threadLabel, 0, i + 1);
//
//            // Add labels to display the execution time and memory usage
//            Label executionTimeLabel = new Label();
//            Label memoryUsageLabel = new Label();
//            gridPane.add(executionTimeLabel, 1, i + 1);
//            gridPane.add(memoryUsageLabel, 2, i + 1);
//        }
//        //place nodes on GridPane
//        gridPane.add(new Label("  Resultado:  "), 0, numThreads + 1);
//
//        Divide divide = new Divide(factorialof, numThreads);
//        Pairs[] pair = divide.getPairs();
//        //create multiple threads and a respective factorial object
//        threads = new Thread[numThreads];
//        List<Text> textList = new ArrayList<>();
//        for(int i = 0; i<numThreads;i++){
//            textList.add(new Text(""));
//            gridPane.add(textList.get(i),1,i+1);
//        }
//
//        ExecutorService executor = Executors.newFixedThreadPool(numThreads);
//
//        for (int i = 0; i < numThreads; i++) {
//            // create task
//            task = new Factorial(pair[i].getStart(), pair[i].getEnd());
//            executor.execute(task);
//            Text runTimeText = new Text();
//            //label to show the process
//            textList.get(i).textProperty().bind(task.messageProperty());
//
//            //label to get the result
////            threads[i] = new Thread(() -> {
////                // Ejecutar el método call() en la instancia de MyClass y guardar el resultado en la lista de resultados
////                BigInteger result = task.call();
////                runTimeText.setText(task.getRunTime() + " ms");
////                results = results.multiply(result);
////            });
//            //add labels to GridPane
////            threads[i].setDaemon(true);
////            gridPane.add(runTimeText, 2, i + 1);
////            threads[i].start();
////            //wait for threads to finish
////            try {
////                threads[i].join();
////            } catch (Exception e) {
////            }
//        }
//        //set resultext
//
//        executor.shutdown();
//        try {
//            if (executor.awaitTermination(1, TimeUnit.MINUTES)) {
//                System.out.println("Finished all threads");
//                Text resultText = new Text();
//
//                resultText.setText(results + "");
//                gridPane.add(resultText, 1, numThreads + 1);
//            } else {
//                System.out.println("Timed out waiting for threads to finish");
//            }
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//
//        //Labels for factorial without task
//        Label title2 = new Label("Sin usar Tasks");
//        Label lb = new Label("Factorial de: " + factorialof);
//        Label result2 = new Label();
//        //place labels
//        gridPane.add(title2, 1, numThreads + 2);
//        gridPane.add(lb, 0, numThreads + 3);
//
//
//        //initialize ONE thread to determine a factorial
//        BigInteger resultNoTask;
//        Factorial fac2 = new Factorial(1, factorialof);
//        Thread T1 = new Thread(fac2);
//        T1.start();
//        resultNoTask = fac2.call();
//        try {
//            T1.join();
//        } catch (Exception e) {
//        }
//        //edit and place labels
//        result2.setText("  " + resultNoTask + "  ");
//        Label runTimeLabel2 = new Label();
//        runTimeLabel2.setText(fac2.getRunTime() + " ms");
//        gridPane.add(runTimeLabel2, 2, numThreads + 3);
//        gridPane.add(result2, 1, numThreads + 3);
//
//        Scene resultsScene = new Scene(gridPane, 400, 300);
//        return resultsScene;
//    }

    public static void main(String[] args) {
        launch();
    }
}

