package com.example.projecttema1ps.view;

import com.example.projecttema1ps.HelloApplication;
import com.example.projecttema1ps.model.Doctor;
import com.example.projecttema1ps.model.User;
import com.example.projecttema1ps.presenter.PresenterLogin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class ViewLogin implements IViewLogin {
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;

    @FXML
    public void loginClick(ActionEvent actionEvent) throws ParserConfigurationException, IOException, SAXException {
        HelloApplication main = new HelloApplication();
        PresenterLogin loginPresenter = new PresenterLogin(this);
        User user = loginPresenter.loginUser();
        Doctor doctor = loginPresenter.loginDoctor();
        try {
                if (doctor == null && user.getRole().equalsIgnoreCase("admin")) {
                    main.changeScene("admin-view.fxml", "Admin");
                } else if (doctor == null &&  user.getRole().equalsIgnoreCase("assistant")) {
                    main.changeScene("assistant-view.fxml", "Assistant");
                } else if (doctor.getRole().equalsIgnoreCase("doctor")){
                    main.changeScene("doctor-view.fxml", "Doctor with id: " + doctor.getId());
                }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getUsername() {
        return tfUsername.getText();
    }

    @Override
    public String getPassword() {
        return tfPassword.getText();
    }
}
