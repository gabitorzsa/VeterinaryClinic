package com.example.projecttema1ps.model;

import java.util.ArrayList;
import java.util.List;

public class MedicalFile {
    private int idMedicalFile;
    private int idDoctor;
    private String treatment;
    private String diagnose;
    private List<String> symptoms;
    private Animal animal;

    public MedicalFile() {
       this.animal = new Animal();
       symptoms = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "MedicalFile{" +
                "id=" + idMedicalFile +
                ", idDoctor=" + idDoctor +
                ", treatment='" + treatment + '\'' +
                ", diagnose='" + diagnose + '\'' +
                ", symptoms=" + symptoms +
                ", animal=" + animal +
                '}' + "\n";
    }

    public int getIdMedicalFile() {
        return idMedicalFile;
    }

    public void setIdMedicalFile(int idMedicalFile) {
        this.idMedicalFile = idMedicalFile;
    }

    public int getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getDiagnose() {
        return diagnose;
    }

    public void setDiagnose(String diagnose) {
        this.diagnose = diagnose;
    }

    public List<String> getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(List<String> symptoms) {
        this.symptoms = symptoms;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }
}
