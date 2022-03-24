package com.example.projecttema1ps.model;


public class Consult {
    private int IdDoctor;
    private String treatment;
    private String diagnose;
    private String symptoms;

    public Consult(int idDoctor, String treatment, String diagnose, String symptoms) {
        IdDoctor = idDoctor;
        this.treatment = treatment;
        this.diagnose = diagnose;
        this.symptoms = symptoms;
    }

    public Consult( String treatment, String diagnose, String symptoms) {
        this.treatment = treatment;
        this.diagnose = diagnose;
        this.symptoms = symptoms;
    }

    public Consult() {

    }

    public int getIdDoctor() {
        return IdDoctor;
    }

    public void setIdDoctor(int idDoctor) {
        IdDoctor = idDoctor;
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

    public String getSymptoms() {
        return symptoms;
    }

    public void setSymptoms(String symptoms) {
        this.symptoms = symptoms;
    }

    @Override
    public String toString() {
        return "Consult{" +
                "IdDoctor='" + IdDoctor + '\'' +
                ", treatment='" + treatment + '\'' +
                ", diagnose='" + diagnose + '\'' +
                ", symptoms='" + symptoms + '\'' +
                '}';
    }
}
