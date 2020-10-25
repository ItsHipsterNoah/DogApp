package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Animal;
import model.Dog;

import java.io.IOException;

public class AnimalDetailsController {

    Stage stage;
    Parent scene;

    @FXML
    private Label vaccLbl;

    @FXML
    private Label animalIDLbl;

    @FXML
    private Label breedLbl;

    @FXML
    private Label lifespanLbl;

    @FXML
    private Label behaviorLbl;

    @FXML
    private Label priceLbl;

    @FXML
    private Label specialLbl;

    @FXML
    public void onActionDisplayMainMenu(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/DisplayAnimalsMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public void sendAnimal(Animal dog) {
        animalIDLbl.setText(String.valueOf(dog.getId()));
        breedLbl.setText(dog.getBreed());
        lifespanLbl.setText(String.valueOf(dog.getLifespan()));
        behaviorLbl.setText(dog.getBehavior());
        priceLbl.setText(String.valueOf(dog.getPrice()));
        if (dog.isVaccinated()) {
            vaccLbl.setText("Yes");
        }
        else {
            vaccLbl.setText("No");
        }
        if (dog instanceof Dog) {
            specialLbl.setText(((Dog) dog).getSpecial());
        }
    }
}
