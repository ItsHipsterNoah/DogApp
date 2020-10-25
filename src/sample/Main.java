package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.DataProvider;
import model.Dog;

import javax.xml.crypto.Data;

public class Main extends Application {

    @Override
    public void init() throws Exception {
        System.out.println("Starting...");
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../view/MainMenu.fxml"));
        primaryStage.setTitle("The Dog App");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.out.println("Exiting...");
    }

    public static void main(String[] args) {
        Dog dog1 = new Dog(1, "Bulldog", 1000, "Adorable", 100000.00, true, "Eating");
        Dog dog2 = new Dog(2, "Husky", 1000, "Silent", 100000.00, true, "Karate");
        Dog dog3 = new Dog(3, "Bulldog", 1000, "Old", 100000.00, true, "Barks at kids to get off his lawn");

        DataProvider.addAnimal(dog1);
        DataProvider.addAnimal(dog2);
        DataProvider.addAnimal(dog3);

        launch(args);
    }
}
