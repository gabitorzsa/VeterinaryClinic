package com.example.projecttema1ps.view;

import javafx.scene.control.TableView;

public interface IViewAssistant {
    String getTfEditAnimalName();
    String getTfEditSpecies();
    String getTfEditWeight();
    String getTfChooseIdAnimal();
    TableView getAnimalTableView();

    TableView getFilterAnimalTableView();
    String getTfFilterAnimalByDoctor();
    String getTfFilterAnimalByDiagnose();
    String getTfFilterAnimalBySpecies();

    void setTfFilterAnimalByDoctor();
    void setTfFilterAnimalByDiagnose();
    void setTfFilterAnimalBySpecies();
}
