package com.example.projecttema1ps.view;

import com.example.projecttema1ps.HelloApplication;
import com.example.projecttema1ps.model.Doctor;
import com.example.projecttema1ps.model.PersistenceDoctor;
import com.example.projecttema1ps.model.PersistenceUser;
import com.example.projecttema1ps.model.User;
import com.example.projecttema1ps.presenter.PresenterAdmin;
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

public class ViewAdmin implements Initializable, IViewAdmin {
    // first tab
    @FXML
    public TableView usersTableView;
    @FXML
    public TableColumn idUserColumn;
    @FXML
    public TableColumn typeUserColumn;
    @FXML
    public TableColumn nameUserColumn;
    @FXML
    public TableColumn usernameUserColumn;
    @FXML
    public TableColumn passwordUserColumn;
    @FXML
    public TextField tfId;
    @FXML
    public TextField tfType;
    @FXML
    public TextField tfName;
    @FXML
    public TextField tfUsername;
    @FXML
    public TextField tfPassword;

    // second tab
    @FXML
    public TableColumn idDoctorColumn;
    @FXML
    public TableColumn typeDoctorColumn;
    @FXML
    public TableColumn nameDoctorColumn;
    @FXML
    public TableColumn startProgramColumn;
    @FXML
    public TableColumn endProgramColumn;
    @FXML
    public TableColumn usernameDoctorColumn;
    @FXML
    public TableColumn passwordDoctorColumn;
    @FXML
    public TextField tfIdDoctor;
    @FXML
    public TextField tfNameDoctor;
    @FXML
    public TextField tfUsernameDoctor;
    @FXML
    public TextField tfPasswordDoctor;
    @FXML
    public TextField tfTypeDoctor;
    @FXML
    public TableView doctorTableView;
    @FXML
    public TextField tfStartProgram;
    @FXML
    public TextField tfEndProgram;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // first tab
        PersistenceUser persistenceUser = new PersistenceUser();
        List<User> userList = persistenceUser.readXML();

        idUserColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeUserColumn.setCellValueFactory(new PropertyValueFactory<>("Role"));
        nameUserColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        usernameUserColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordUserColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        for (User user : userList) {
            usersTableView.getItems().add(user);
        }

        // second tab
        PersistenceDoctor persistenceDoctor = new PersistenceDoctor();
        List<Doctor> doctorList = persistenceDoctor.readXML();

        idDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("role"));
        nameDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        startProgramColumn.setCellValueFactory(new PropertyValueFactory<>("startProgram"));
        endProgramColumn.setCellValueFactory(new PropertyValueFactory<>("finishProgram"));
        usernameDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("username"));
        passwordDoctorColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

        for (Doctor doctor : doctorList) {
            doctorTableView.getItems().add(doctor);
        }
    }

    public void addUserClick(ActionEvent actionEvent) throws IOException {
        PresenterAdmin presenterAdmin = new PresenterAdmin(this);
        presenterAdmin.addUser();
        HelloApplication main = new HelloApplication();
        main.changeScene("admin-view.fxml", "Admin");
    }

    public void updateUserClick(ActionEvent actionEvent) throws IOException {
        PresenterAdmin presenterAdmin = new PresenterAdmin(this);
        presenterAdmin.updateUser();
        HelloApplication main = new HelloApplication();
        main.changeScene("admin-view.fxml", "Admin");
    }

    public void deleteUserClick(ActionEvent actionEvent) throws IOException {
        PresenterAdmin presenterAdmin = new PresenterAdmin(this);
        presenterAdmin.deleteUser();
        HelloApplication main = new HelloApplication();
        main.changeScene("admin-view.fxml", "Admin");
    }

    public void addDoctorClick(ActionEvent actionEvent) throws IOException {
        PresenterAdmin presenterAdmin = new PresenterAdmin(this);
        presenterAdmin.addDoctor();
        HelloApplication main = new HelloApplication();
        main.changeScene("admin-view.fxml", "Admin");
    }

    public void updateDoctorClick(ActionEvent actionEvent) throws IOException {
        PresenterAdmin presenterAdmin = new PresenterAdmin(this);
        presenterAdmin.updateDoctor();
        HelloApplication main = new HelloApplication();
        main.changeScene("admin-view.fxml", "Admin");
    }

    public void deleteDoctorClick(ActionEvent actionEvent) throws IOException {
        PresenterAdmin presenterAdmin = new PresenterAdmin(this);
        presenterAdmin.deleteDoctor();
        HelloApplication main = new HelloApplication();
        main.changeScene("admin-view.fxml", "Admin");
    }

    public void logoutClick(ActionEvent actionEvent) throws IOException {
        HelloApplication main = new HelloApplication();
        main.changeScene("login-view.fxml", "Login");
    }

    @Override
    public String getTfId() {
        return tfId.getText();
    }

    @Override
    public String getTfType() {
        return tfType.getText();
    }

    @Override
    public String getTfName() {
        return tfName.getText();
    }

    @Override
    public String getTfUsername() {
        return tfUsername.getText();
    }

    @Override
    public String getTfPassword() {
        return tfPassword.getText();
    }

    @Override
    public String getTfDoctorId() {
        return tfIdDoctor.getText();
    }

    @Override
    public String getTfTypeDoctor() {
        return tfTypeDoctor.getText();
    }

    @Override
    public String getTfDoctorName() {
        return tfNameDoctor.getText();
    }

    @Override
    public String getTfDoctorUsername() {
        return tfUsernameDoctor.getText();
    }

    @Override
    public String getTfDoctorPassword() {
        return tfPasswordDoctor.getText();
    }

    @Override
    public String getTfStartProgram() {
        return tfStartProgram.getText();
    }

    @Override
    public String getTfEndProgram() {
        return tfEndProgram.getText();
    }
}
