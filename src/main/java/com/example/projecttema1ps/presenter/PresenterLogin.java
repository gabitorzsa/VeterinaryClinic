package com.example.projecttema1ps.presenter;

import com.example.projecttema1ps.model.Doctor;
import com.example.projecttema1ps.model.PersistenceDoctor;
import com.example.projecttema1ps.model.PersistenceUser;
import com.example.projecttema1ps.model.User;
import com.example.projecttema1ps.view.IViewLogin;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class PresenterLogin{
    IViewLogin iViewLogin;
    PersistenceUser persistenceUser;
    PersistenceDoctor persistenceDoctor;

    public PresenterLogin(IViewLogin iViewLogin) throws ParserConfigurationException, IOException, SAXException {
        this.iViewLogin = iViewLogin;
        persistenceUser = new PersistenceUser();
        persistenceDoctor = new PersistenceDoctor();
    }

    public User loginUser() {
        return persistenceUser.validateLogin(iViewLogin.getUsername(), iViewLogin.getPassword());
    }
    public Doctor loginDoctor() {
        return persistenceDoctor.validateLogin(iViewLogin.getUsername(), iViewLogin.getPassword());
    }
}
