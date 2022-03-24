package com.example.projecttema1ps.view;

import javafx.scene.control.TableView;

public interface IViewDoctor {
    String getTfEditSymptoms();
    String getTfEditDiagnose();
    String getTfEditTreatment();
    String getTfId();

    String getTfFilterByDiagnose();
    String getTfFilterByTreatment();

    TableView getFilterTableView();

    String getTfIdToUpdate();
    String getTfEditStartProgram();
    String getTfEditEndProgram();

}
