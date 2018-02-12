package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

//    @Override
//    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("../../resources/sample.fxml"));
//        primaryStage.setTitle("CantoTag - v0.0.1");
//        primaryStage.setScene(new Scene(root, 1300, 900));
//        primaryStage.show();
//    }
//
//
//    public static void main(String[] args) {
//        launch(args);
//    }

    //Static global variable for the controller (where MyController is the name of your controller class
    public static Controller myControllerHandle;

    @Override
    public void start(Stage stage) throws Exception {
        //Set up instance instead of using static load() method
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/sample.fxml"));
        Parent root = loader.load();

        //Now we have access to getController() through the instance... don't forget the type cast
        myControllerHandle = (Controller)loader.getController();

        Scene scene = new Scene(root, 1300, 900);

        stage.setTitle("CantoTag - v0.0.2");
        stage.setScene(scene);
        stage.show();
    }

}
