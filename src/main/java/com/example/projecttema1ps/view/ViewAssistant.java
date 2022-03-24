package com.example.projecttema1ps.view;

import com.example.projecttema1ps.HelloApplication;
import com.example.projecttema1ps.model.MedicalFile;
import com.example.projecttema1ps.model.PersistenceMedicalFile;
import com.example.projecttema1ps.presenter.PresenterAssistant;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewAssistant implements Initializable, IViewAssistant {

    // first tab
    @FXML
    public TableView animalTableView;
    @FXML
    public TextField tfUpdateName;
    @FXML
    public TextField tfUpdateSpecies;
    @FXML
    public TextField tfUpdateWeight;
    @FXML
    public TableColumn animalColumn;
    @FXML
    public TableColumn idColumn;
    @FXML
    public TableColumn speciesColumn;
    @FXML
    public TableColumn weightColumn;
    @FXML
    public TextField tfChooseAnimalId;

    // second tab
    @FXML
    public TextField tfFilterAnimalByDoctor;
    @FXML
    public TextField tfFilterAnimalByDiagnose;
    @FXML
    public TextField tfFilterAnimalBySpecies;
    @FXML
    public TableColumn animalFilterColumn;
    @FXML
    public TableColumn idFilterColumn;
    @FXML
    public TableColumn weightFilterColumn;
    @FXML
    public TableColumn speciesFilterColumn;
    @FXML
    public TableView filterAnimalTableView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PersistenceMedicalFile persistenceMedicalFile = new PersistenceMedicalFile();
        List<MedicalFile> medicalFileList;
        medicalFileList = persistenceMedicalFile.readXML();
        //first tab
        animalColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        speciesColumn.setCellValueFactory(new PropertyValueFactory<>("Species"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("Weight"));

        for (MedicalFile mf : medicalFileList) {
            animalTableView.getItems().add(mf.getAnimal());
        }
        // second tab
        animalFilterColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        idFilterColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        weightFilterColumn.setCellValueFactory(new PropertyValueFactory<>("Weight"));
        speciesFilterColumn.setCellValueFactory(new PropertyValueFactory<>("Species"));
    }

    public void updateAnimalClick(ActionEvent actionEvent) throws IOException {
        PresenterAssistant presenterAssistant = new PresenterAssistant(this);
        presenterAssistant.updateAnimal();
        HelloApplication main = new HelloApplication();
        main.changeScene("assistant-view.fxml", "Assistant");
    }

    public void logoutClick(ActionEvent actionEvent) throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("login-view.fxml", "Login");
    }

    public void searchClick(ActionEvent actionEvent) {
        PresenterAssistant presenterAssistant = new PresenterAssistant(this);
        presenterAssistant.filterAnimal();
    }

    public void saveCSV(ActionEvent actionEvent) throws IOException {
        PresenterAssistant presenterAssistant = new PresenterAssistant(this);
        presenterAssistant.writeCSV();
    }

    public void saveJSON(ActionEvent actionEvent) throws IOException {
        PresenterAssistant presenterAssistant = new PresenterAssistant(this);
//        presenterAssistant.writeJSON();
    }

    @Override
    public String getTfEditAnimalName() {
        return tfUpdateName.getText();
    }

    @Override
    public String getTfEditSpecies() {
        return tfUpdateSpecies.getText();
    }

    @Override
    public String getTfEditWeight() {
        return tfUpdateWeight.getText();
    }

    @Override
    public String getTfChooseIdAnimal() {
        return tfChooseAnimalId.getText();
    }

    @Override
    public TableView getAnimalTableView() {
        return animalTableView;
    }

    @Override
    public TableView getFilterAnimalTableView() {
        return filterAnimalTableView;
    }

    @Override
    public String getTfFilterAnimalByDoctor() {
        return tfFilterAnimalByDoctor.getText();
    }

    @Override
    public String getTfFilterAnimalByDiagnose() {
        return tfFilterAnimalByDiagnose.getText();
    }

    @Override
    public String getTfFilterAnimalBySpecies() {
        return tfFilterAnimalBySpecies.getText();
    }

    @Override
    public void setTfFilterAnimalByDoctor() {
        tfFilterAnimalByDoctor.setText(tfFilterAnimalByDoctor.getPromptText());
    }

    @Override
    public void setTfFilterAnimalByDiagnose() {
        tfFilterAnimalByDiagnose.setText(tfFilterAnimalByDiagnose.getPromptText());
    }

    @Override
    public void setTfFilterAnimalBySpecies() {
        tfFilterAnimalBySpecies.setText(tfFilterAnimalBySpecies.getPromptText());
    }
}


