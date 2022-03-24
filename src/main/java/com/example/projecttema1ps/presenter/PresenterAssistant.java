package com.example.projecttema1ps.presenter;

import com.example.projecttema1ps.model.MedicalFile;
import com.example.projecttema1ps.model.PersistenceMedicalFile;
import com.example.projecttema1ps.view.IViewAssistant;

import java.io.*;
import java.util.List;


public class PresenterAssistant {
    IViewAssistant iViewAssistant;
    PersistenceMedicalFile persistenceMedicalFile;

    public PresenterAssistant(IViewAssistant iViewAssistant) {
        this.iViewAssistant = iViewAssistant;
        persistenceMedicalFile = new PersistenceMedicalFile();
    }

    public void updateAnimal() {
        List<MedicalFile> medicalFileList = persistenceMedicalFile.readXML();
        for(MedicalFile mf : medicalFileList) {
            if (mf.getAnimal().getId() == Integer.valueOf(iViewAssistant.getTfChooseIdAnimal())) {
                if (!iViewAssistant.getTfEditAnimalName().isEmpty()) {
                    mf.getAnimal().setName(iViewAssistant.getTfEditAnimalName());
                }
                if (!iViewAssistant.getTfEditSpecies().isEmpty()) {
                    mf.getAnimal().setSpecies(iViewAssistant.getTfEditSpecies());
                }
                if (!iViewAssistant.getTfEditWeight().isEmpty()) {
                    mf.getAnimal().setWeight(iViewAssistant.getTfEditWeight());
                }
                persistenceMedicalFile.updateMedicalFile(mf);
                break;
            }
        }
    }

    public void filterAnimal() {
        PersistenceMedicalFile persistenceMedicalFile = new PersistenceMedicalFile();
        List<MedicalFile> medicalFileList = persistenceMedicalFile.readXML();
        iViewAssistant.getFilterAnimalTableView().getItems().clear();
        for (MedicalFile mf : medicalFileList) {
            if (iViewAssistant.getTfFilterAnimalByDiagnose().equals(mf.getDiagnose())) {
                iViewAssistant.getFilterAnimalTableView().getItems().add(mf.getAnimal());
            }
            if (iViewAssistant.getTfFilterAnimalBySpecies().equals(mf.getAnimal().getSpecies())) {
                iViewAssistant.getFilterAnimalTableView().getItems().add(mf.getAnimal());
            }
            if (iViewAssistant.getTfFilterAnimalByDoctor().equals(String.valueOf(mf.getIdDoctor()))) {
                iViewAssistant.getFilterAnimalTableView().getItems().add(mf.getAnimal());
            }
        }
        iViewAssistant.setTfFilterAnimalByDiagnose();
        iViewAssistant.setTfFilterAnimalBySpecies();
        iViewAssistant.setTfFilterAnimalByDoctor();
    }

    public void writeCSV() throws IOException {
        Writer writer = null;
        try {
            File file = new File("Animal.csv");
            writer = new BufferedWriter(new FileWriter(file));
            String text = iViewAssistant.getFilterAnimalTableView().getItems().toString();
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            writer.flush();
            writer.close();
        }
    }

}
