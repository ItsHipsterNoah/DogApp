package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.DataProvider;
import model.Dog;

import java.io.IOException;
import java.util.Optional;

public class CreateAnimalController {

    Stage stage;
    Parent scene;

    @FXML
    private TextField animalIDTxt;

    @FXML
    private TextField breedTtx;

    @FXML
    private TextField lifespanTxt;

    @FXML
    private TextField behaviorTxt;

    @FXML
    private TextField priceTxt;

    @FXML
    private RadioButton vaccYesRBtn;

    @FXML
    private RadioButton VaccNoRBtn;

    @FXML
    public void onActionSaveAnimal(ActionEvent actionEvent) throws IOException {
        try {
            int id = Integer.parseInt(animalIDTxt.getText());
            String breed = breedTtx.getText();
            int lifespan = Integer.parseInt(lifespanTxt.getText());
            String behavior = behaviorTxt.getText();
            double price = Double.parseDouble(priceTxt.getText());
            boolean isVaccinated = vaccYesRBtn.isSelected();
            String special = null;

            DataProvider.addAnimal(new Dog(id, breed, lifespan, behavior, price, isVaccinated, special));
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch (NumberFormatException e) {
//            Alert alert = new Alert(Alert.AlertType.ERROR);
//            alert.setTitle("Error dialog");
//            alert.setContentText("Please enter a valid value for each text field.");
//            alert.showAndWait();
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error dialog");
            alert.setContentText("Please enter a valid value for each text field.");
            alert.showAndWait();
        }
    }


    @FXML
    public void onActionDisplayMainMenu(ActionEvent actionEvent) throws IOException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to cancel?\nYou will lose any unsaved changes.");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }


}
