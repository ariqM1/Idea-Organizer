// I worked on the homework assignment alone, using only course materials and StackOverflow

package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * This class is StarterUpper, my GUI
 * @author Ariq Mukul
 * @version 1.0
 */

public class StarterUpper extends Application {
    //targetFields
    private TextField whatsProblem = new TextField();
    private TextField targetCustomer = new TextField();
    private TextField howBad = new TextField();
    private TextField howMany = new TextField();
    private TextField targetMarket = new TextField();
    private TextField whoComp = new TextField();

    //labels
    private Label bigLabel;
    private Label labProblem = new Label("What is the problem?");
    private Label labCustomer = new Label("Who is the target customer?");
    private Label labBad = new Label("How badly does the customer NEED this problem fixed (1-10)?");
    private Label labMany = new Label("How many people do you know who might experience this problem?");
    private Label labTargetMark = new Label("How big is the target market?");
    private Label labComp = new Label("Who are the competitors/existing solutions?");

    //buttons
    private Button addIdea = new Button();
    private Button sortIdeaPotential = new Button();
    private Button resetForm = new Button();
    private Button saveIdea = new Button();
    private Button yourMom = new Button();

    //ArrayList
    private List<StartUpIdea> ideaStorage = new ArrayList<StartUpIdea>();
    //Adding a ListView and ObservableList
    private ObservableList<String> ideaList = FXCollections.<String>observableArrayList();
    private ListView<String> ideaListView = new ListView<>(ideaList);

    //TextField input
    private String whatProblemInput;
    private String targetCustomerInput;
    private int howBadInput;
    private int howManyInput;
    private int howBigInput;
    private String whoCompInput;


    //File
    protected File ideaFile = new File("ideas.txt");

    @Override
    /**
     * start method that shows stage
     * @param stage that shows stage
     */
    public void start(Stage primaryStage) throws Exception {
        GridPane root = new GridPane();
        root.setAlignment(Pos.CENTER);
        root.setHgap(5);
        root.setVgap(5);
        root.setPadding(new Insets(25, 25, 25, 25));
        root.setStyle("-fx-background-color: #C0C0C0;"); //~~~~~~~~~~~~~~~~~~~~~Adding gray colored to background
        // this allows input fields to be placed in the window

        primaryStage.setTitle("Problem Ideation Form");

        //setting spaces for TF
        whatsProblem.setPrefColumnCount(14);
        targetCustomer.setPrefColumnCount(14);
        howBad.setPrefColumnCount(14);
        howMany.setPrefColumnCount(14);
        targetMarket.setPrefColumnCount(14);
        whoComp.setPrefColumnCount(14);

        //creating labels
        bigLabel = new Label("Organize Your Ideas!");
        bigLabel.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));

        //button
        addIdea.setText("Add idea");
        addIdea.setMaxSize(100, 100);
        sortIdeaPotential.setText("Sort ideas");
        sortIdeaPotential.setMaxSize(100, 100);
        resetForm.setText("Reset form");
        resetForm.setMaxSize(100, 100);
        saveIdea.setText("Save idea");
        saveIdea.setMaxSize(100, 100);

        //adding to pane
        root.add(bigLabel, 0, 0);
        root.add(labProblem, 0, 1);
        root.add(whatsProblem, 1, 1);
        root.add(labCustomer, 0, 2);
        root.add(targetCustomer, 1, 2);
        root.add(labBad, 0, 3);
        root.add(howBad, 1, 3);
        root.add(labMany, 0, 4);
        root.add(howMany, 1, 4);
        root.add(labTargetMark, 0, 5);
        root.add(targetMarket, 1, 5);
        root.add(labComp, 0, 6);
        root.add(whoComp, 1, 6);
        root.add(addIdea, 2, 2);
        root.add(sortIdeaPotential, 2, 3);
        root.add(resetForm, 2, 4);
        root.add(saveIdea, 2, 5);

        //verification of proper input
        addIdea.setOnAction(actionEvent -> {
                try {
                    whatProblemInput = whatsProblem.getText();
                    targetCustomerInput = targetCustomer.getText();
                    howBadInput = Integer.parseInt(howBad.getText());
                    howManyInput = Integer.parseInt(howMany.getText());
                    howBigInput = Integer.parseInt(targetMarket.getText());
                    whoCompInput = whoComp.getText();

                    Alert myAlert = new Alert(Alert.AlertType.WARNING);
                    myAlert.setTitle("Error");
                    myAlert.setHeaderText("Error");
                    myAlert.setContentText("Fields are invalid");

                    if (whatProblemInput.isEmpty() || targetCustomerInput.isEmpty()
                            || howBad.getText().isEmpty() || howMany.getText().isEmpty()
                                || targetMarket.getText().isEmpty() || whoCompInput.isEmpty()) {
                        throw new Exception();
                    } else if (howBadInput < 0 || howBadInput > 10) {
                        myAlert.setContentText("Must be a number 1 through 10");
                        myAlert.show();
                    } else {
                        StartUpIdea idea = new StartUpIdea(whatProblemInput, targetCustomerInput, howBadInput,
                                howManyInput, howBigInput, whoCompInput); //checking if this is proper way to add idea
                        ideaStorage.add(idea); //adding idea in arrayList
                        ideaList.add(idea.toString());

                        whatsProblem.clear();
                        targetCustomer.clear();
                        howBad.clear();
                        howMany.clear();
                        targetMarket.clear();
                        whoComp.clear();
                    }

                    //how to store StartUpIdea in arrayList
                } catch (NumberFormatException e) {

                    Alert myAlert = new Alert(Alert.AlertType.WARNING);
                    myAlert.setTitle("Error");
                    myAlert.setHeaderText("Error");
                    myAlert.setContentText("Fields are invalid");

                    myAlert.show();
                } catch (Exception e) {
                    System.out.println(e.getMessage());

                    Alert myAlert = new Alert(Alert.AlertType.WARNING);
                    myAlert.setTitle("Error");
                    myAlert.setHeaderText("Error");
                    myAlert.setContentText("Fields are invalid");

                    myAlert.show();
                }
            });

        EventHandler<ActionEvent> handler = new EventHandler<>() {
            /**
             * Anonymous Inner Class method
             * @param event EVENT PARAMETER
             */
            public void handle(ActionEvent event) {
                Collections.sort(ideaList);
            }
        };
        sortIdeaPotential.setOnAction(handler);

        resetForm.setOnAction(actionEvent -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Reset Form Alert");
                alert.setHeaderText("Are you sure?");
                alert.setResizable(false);
                alert.setContentText("Select okay to delete everything or cancel.");

                Optional<ButtonType> result = alert.showAndWait();
                ButtonType button = result.orElse(ButtonType.CANCEL);

                if (button == ButtonType.OK) {
                    System.out.println("Ok pressed");
                    if (ideaFile.exists()) {
                        ideaFile.delete();
                    }

                    ideaStorage.clear(); //clears arrayList
                    ideaList.clear(); //clears observableList
                    ideaListView.getItems().clear(); //clear ideaList

                    //clear textFields
                    whatsProblem.clear();
                    targetCustomer.clear();
                    howBad.clear();
                    howMany.clear();
                    targetMarket.clear();
                    whoComp.clear();
                }
            }
        );

        saveIdea.setOnAction(actionEvent -> {
                FileUtil.saveIdeasToFile(ideaStorage, ideaFile);
            }
        );

        yourMom.setOnAction(actionEvent -> {
            System.out.println("your mom");
        });

        //Adding an image ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        Image picture = new Image(getClass().getResourceAsStream("gtpic.png"));
        ImageView imageView = new ImageView(picture);
        imageView.setX(25);
        imageView.setY(15);
        imageView.setFitWidth(270);
        imageView.setPreserveRatio(true);
        root.add(imageView, 3, 0);

        //Adding ListView ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        root.add(ideaListView, 0, 7);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * MAIN METHOD - COOL STUFF GOING DOWN!
     * @param args arguments
     */

    public static void main(String[] args) {
        launch(args);
    }
}