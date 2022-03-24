package com.example.projecttema1ps.presenter;

import com.example.projecttema1ps.model.Doctor;
import com.example.projecttema1ps.model.PersistenceDoctor;
import com.example.projecttema1ps.model.PersistenceUser;
import com.example.projecttema1ps.model.User;
import com.example.projecttema1ps.view.IViewAdmin;

import java.util.List;

public class PresenterAdmin {
    IViewAdmin iViewAdmin;
    PersistenceUser persistenceUser;
    PersistenceDoctor persistenceDoctor;

    public PresenterAdmin(IViewAdmin iViewAdmin) {
        this.iViewAdmin = iViewAdmin;
        persistenceUser = new PersistenceUser();
        persistenceDoctor = new PersistenceDoctor();
    }

    public void addUser() {
        User userToAdd = new User();
        userToAdd.setId(Integer.parseInt(iViewAdmin.getTfId()));
        userToAdd.setRole(iViewAdmin.getTfType());
        userToAdd.setName(iViewAdmin.getTfName());
        userToAdd.setUsername(iViewAdmin.getTfUsername());
        userToAdd.setPassword(iViewAdmin.getTfPassword());
        persistenceUser.addUser(userToAdd);
    }

    public void updateUser() {
        User userToUpdate = new User();
        userToUpdate.setId(Integer.parseInt(iViewAdmin.getTfId()));
        if(!iViewAdmin.getTfType().isEmpty())
            userToUpdate.setRole(iViewAdmin.getTfType());
        if(!iViewAdmin.getTfName().isEmpty())
            userToUpdate.setName(iViewAdmin.getTfName());
        if(!iViewAdmin.getTfUsername().isEmpty())
            userToUpdate.setUsername(iViewAdmin.getTfUsername());
        if(!iViewAdmin.getTfPassword().isEmpty())
            userToUpdate.setPassword(iViewAdmin.getTfPassword());
        System.out.println(userToUpdate);
        persistenceUser.updateUser(userToUpdate);
    }

    public void deleteUser() {
        List<User> userList = persistenceUser.readXML();
        for (User user : userList) {
            if (user.getId() == Integer.parseInt(iViewAdmin.getTfId())) {
                persistenceUser.deleteUser(user.getId());
                break;
            }
        }
    }

    public void addDoctor() {
        Doctor doctorToAdd = new Doctor();
        doctorToAdd.setId(Integer.parseInt(iViewAdmin.getTfDoctorId()));
        doctorToAdd.setRole(iViewAdmin.getTfTypeDoctor());
        doctorToAdd.setName(iViewAdmin.getTfDoctorName());
        doctorToAdd.setStartProgram(iViewAdmin.getTfStartProgram());
        doctorToAdd.setFinishProgram(iViewAdmin.getTfEndProgram());
        doctorToAdd.setUsername(iViewAdmin.getTfDoctorUsername());
        doctorToAdd.setPassword(iViewAdmin.getTfDoctorPassword());
        persistenceDoctor.addDoctor(doctorToAdd);
    }

    public void updateDoctor() {
        Doctor doctorToUpdate = new Doctor();
        doctorToUpdate.setId(Integer.parseInt(iViewAdmin.getTfDoctorId()));
        doctorToUpdate.setRole(iViewAdmin.getTfTypeDoctor());
        if(!iViewAdmin.getTfDoctorName().isEmpty())
            doctorToUpdate.setName(iViewAdmin.getTfDoctorName());
        if(!iViewAdmin.getTfStartProgram().isEmpty())
            doctorToUpdate.setStartProgram(iViewAdmin.getTfStartProgram());
        if(!iViewAdmin.getTfEndProgram().isEmpty())
            doctorToUpdate.setFinishProgram(iViewAdmin.getTfEndProgram());
        if(!iViewAdmin.getTfDoctorUsername().isEmpty())
            doctorToUpdate.setUsername(iViewAdmin.getTfDoctorUsername());
        if(!iViewAdmin.getTfDoctorPassword().isEmpty())
            doctorToUpdate.setPassword(iViewAdmin.getTfDoctorPassword());
        persistenceDoctor.updateDoctor(doctorToUpdate);
    }

    public void deleteDoctor() {
        List<Doctor> doctorList = persistenceDoctor.readXML();
        for (Doctor doctor : doctorList) {
            if (doctor.getId() == Integer.parseInt(iViewAdmin.getTfDoctorId())) {
                persistenceDoctor.deleteDoctor(doctor.getId());
                break;
            }
        }
    }
}

