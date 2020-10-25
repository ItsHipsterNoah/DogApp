package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Animal;
import model.DataProvider;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class DisplayAnimalController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TableView<Animal> animalTableView;

    @FXML
    private TableColumn<Animal, Integer> animalIDCol;

    @FXML
    private TableColumn<Animal, String> breedCol;

    @FXML
    private TableColumn<Animal, Integer> lifespanCol;

    @FXML
    private TableColumn<Animal, Double> priceCol;

    @FXML
    public void onActionDisplayAnimalDetailsMenu(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/AnimalDetailsMenu.fxml"));
        loader.load();

        AnimalDetailsController ADMController = loader.getController();
        ADMController.sendAnimal(animalTableView.getSelectionModel().getSelectedItem());

        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    public void onActionDisplayMainMenu(ActionEvent actionEvent) throws IOException {
        stage = (Stage)((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainMenu.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    public boolean search(int id) {
        for (Animal dog : DataProvider.getAllAnimals()) {
            if (dog.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean update(int id, Animal animal) {
        int index = -1;
        for (Animal dog : DataProvider.getAllAnimals()) {
            index++;
            if (dog.getId() == id) {
                DataProvider.getAllAnimals().set(index, animal);
                return true;
            }
        }
        return false;
    }

    public boolean delete(int id) {
        for (Animal dog : DataProvider.getAllAnimals()) {
            if (dog.getId() == id) {
                DataProvider.getAllAnimals().remove(dog);
                return true;
            }
        }
        return false;
    }

    public Animal selectAnimal(int id) {
        for (Animal dog : DataProvider.getAllAnimals()) {
            if (dog.getId() == id) {
                return dog;
            }
        }
        return null;
    }

    public ObservableList<Animal> filter(String text) {
        if (!DataProvider.getAllFilteredAnimals().isEmpty()) {
            DataProvider.getAllFilteredAnimals().clear();
        }
        for (Animal dog : DataProvider.getAllAnimals()) {
            if (dog.getBreed().contains(text)) {
                DataProvider.getAllFilteredAnimals().add(dog);
            }
        }
        if (DataProvider.getAllFilteredAnimals().isEmpty()) {
            return DataProvider.getAllAnimals();
        }
        else {
            return DataProvider.getAllFilteredAnimals();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        animalTableView.setItems(DataProvider.getAllAnimals());
        // animalTableView.setItems(filter("sky"));

        animalIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        breedCol.setCellValueFactory(new PropertyValueFactory<>("breed"));
        lifespanCol.setCellValueFactory(new PropertyValueFactory<>("lifespan"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        animalTableView.getSelectionModel().select(selectAnimal(1));


    }

}
