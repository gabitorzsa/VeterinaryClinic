package com.example.projecttema1ps.view;

import com.example.projecttema1ps.HelloApplication;
import com.example.projecttema1ps.model.*;
import com.example.projecttema1ps.presenter.PresenterDoctor;
import com.example.projecttema1ps.presenter.PresenterLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ViewDoctor implements Initializable, IViewDoctor {

    // first tab
    @FXML
    public TextField tfEditSymptoms;
    @FXML
    public TextField tfEditDiagnose;
    @FXML
    public TextField tfEditTreatment;
    @FXML
    public TextField chooseId;

    @FXML
    private TableView medicalFilesTable;
    @FXML
    public TableColumn animalInfoColumn;
    @FXML
    public TableColumn idColumn;
    @FXML
    public TableColumn symptomsColumn;
    @FXML
    public TableColumn diagnoseColumn;
    @FXML
    public TableColumn treatmentColumn;
    @FXML
    public TableColumn idDoctorColumn;

    // second tab
    @FXML
    public TextField tfFilterByDiagnose;
    @FXML
    public TextField tfFilterByTreatment;
    @FXML
    public TableView filterTable;
    @FXML
    public TableColumn filterDiagnose;
    @FXML
    public TableColumn filterTreatment;
    @FXML
    private TableColumn<MedicalFile, String> animal;

    // third tab
    @FXML
    public TableView workProgramTableView;
    @FXML
    public TableColumn idWpColumn;
    @FXML
    public TableColumn nameWpColumn;
    @FXML
    public TableColumn startProgramWpColumn;
    @FXML
    public TableColumn endProgramWpColumn;
    @FXML
    public TableColumn consultsColumn;
    @FXML
    public TextField tdEditStartProgram;
    @FXML
    public TextField tfIdToUpdate;
    @FXML
    public TextField tfEditEndProgram;

    PersistenceMedicalFile persistenceMedicalFile = new PersistenceMedicalFile();
    PersistenceDoctor persistenceDoctor = new PersistenceDoctor();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<MedicalFile> listMedicalFile;
        listMedicalFile = persistenceMedicalFile.readXML();
        // first tab
        animalInfoColumn.setCellValueFactory(new PropertyValueFactory<>("animal"));
        idColumn.setCellValueFactory(new PropertyValueFactory<>("idMedicalFile"));
        idDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("idDoctor"));
        treatmentColumn.setCellValueFactory(new PropertyValueFactory<>("treatment"));
        diagnoseColumn.setCellValueFactory(new PropertyValueFactory<>("diagnose"));
        symptomsColumn.setCellValueFactory(new PropertyValueFactory<>("symptoms"));

        List<Doctor> listDoc;
        listDoc = persistenceDoctor.readXML();

        for (MedicalFile mf : listMedicalFile) {
            for (Doctor d : listDoc) {
                if (mf.getIdDoctor() == d.getId()) {
                    medicalFilesTable.getItems().add(mf);
                }
            }
        }
        // second tab
        animal.setCellValueFactory(new PropertyValueFactory<>("Animal"));
        filterDiagnose.setCellValueFactory(new PropertyValueFactory<>("Diagnose"));
        filterTreatment.setCellValueFactory(new PropertyValueFactory<>("Treatment"));

        //third tab
        idWpColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
        nameWpColumn.setCellValueFactory(new PropertyValueFactory<>("Name"));
        startProgramWpColumn.setCellValueFactory(new PropertyValueFactory<>("startProgram"));
        endProgramWpColumn.setCellValueFactory(new PropertyValueFactory<>("finishProgram"));
        consultsColumn.setCellValueFactory(new PropertyValueFactory<>("consults"));
        for (Doctor d : listDoc) {
            workProgramTableView.getItems().add(d);
        }
    }

    public void saveClick(ActionEvent actionEvent) throws IOException, ParserConfigurationException, SAXException {
        PresenterDoctor presenterDoctor = new PresenterDoctor(this);
        presenterDoctor.save();
        HelloApplication main = new HelloApplication();
        main.changeScene("doctor-view.fxml", "Doctor");
    }

    public void filterClick(ActionEvent actionEvent) throws IOException, ParserConfigurationException, SAXException {
        PresenterDoctor presenterDoctor = new PresenterDoctor(this);
        presenterDoctor.filter();

    }

    public void editProgramClick(ActionEvent actionEvent) throws ParserConfigurationException, IOException, SAXException {
        PresenterDoctor presenterDoctor = new PresenterDoctor(this);
        presenterDoctor.editProgram();
        HelloApplication main = new HelloApplication();
        main.changeScene("doctor-view.fxml", "Doctor");
    }

    public void logoutClick(ActionEvent actionEvent) throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("login-view.fxml", "Login");
    }

    @Override
    public String getTfEditSymptoms() {
        return tfEditSymptoms.getText();
    }

    @Override
    public String getTfEditDiagnose() {
        return tfEditDiagnose.getText();
    }

    @Override
    public String getTfEditTreatment() {
        return tfEditTreatment.getText();
    }

    @Override
    public String getTfId() {
        return chooseId.getText();
    }

    @Override
    public String getTfFilterByDiagnose() {
        return tfFilterByDiagnose.getText();
    }

    @Override
    public String getTfFilterByTreatment() {
        return tfFilterByTreatment.getText();
    }

    @Override
    public TableView getFilterTableView() {
        return filterTable;
    }

    @Override
    public String getTfIdToUpdate() {
        return tfIdToUpdate.getText();
    }

    @Override
    public String getTfEditStartProgram() {
        return tdEditStartProgram.getText();
    }

    @Override
    public String getTfEditEndProgram() {
        return tfEditEndProgram.getText();
    }
}
