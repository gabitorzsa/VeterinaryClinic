package com.example.projecttema1ps.presenter;

import com.example.projecttema1ps.model.Doctor;
import com.example.projecttema1ps.model.MedicalFile;
import com.example.projecttema1ps.model.PersistenceDoctor;
import com.example.projecttema1ps.model.PersistenceMedicalFile;
import com.example.projecttema1ps.view.IViewDoctor;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class PresenterDoctor {
    IViewDoctor iViewDoctor;
    PersistenceDoctor persistenceDoctor;
    PersistenceMedicalFile persistenceMedicalFile;


    public PresenterDoctor(IViewDoctor iViewDoctor) throws ParserConfigurationException, IOException, SAXException {
        this.iViewDoctor = iViewDoctor;
        persistenceDoctor = new PersistenceDoctor();
        persistenceMedicalFile = new PersistenceMedicalFile();
    }

    public void save() {
        List<MedicalFile> medicalFileList = persistenceMedicalFile.readXML();
        for (MedicalFile mf : medicalFileList) {
            if (mf.getIdMedicalFile() == Integer.valueOf(iViewDoctor.getTfId())) {
                if (!iViewDoctor.getTfEditDiagnose().isEmpty()) {
                    mf.setDiagnose(iViewDoctor.getTfEditDiagnose());
                }
                if (!iViewDoctor.getTfEditTreatment().isEmpty()) {
                    mf.setTreatment(iViewDoctor.getTfEditTreatment());
                }
                if (!iViewDoctor.getTfEditSymptoms().isEmpty()) {
                    String temp;
                    temp = iViewDoctor.getTfEditSymptoms();
                    List<String> tempSymptoms;
                    tempSymptoms = List.of(temp.split("\\s+"));
                    mf.setSymptoms(tempSymptoms);
                }
                persistenceMedicalFile.updateMedicalFile(mf);
                break;
            }
        }
    }

    public void filter() {
        PersistenceMedicalFile persistenceMedicalFile = new PersistenceMedicalFile();
        List<MedicalFile> medicalFileList = persistenceMedicalFile.readXML();
        iViewDoctor.getFilterTableView().getItems().clear();
        for (MedicalFile mf : medicalFileList) {
            if (iViewDoctor.getTfFilterByDiagnose().equals(mf.getDiagnose())) {
                iViewDoctor.getFilterTableView().getItems().add(mf);
            }
            if (iViewDoctor.getTfFilterByTreatment().equals(mf.getTreatment())) {
                iViewDoctor.getFilterTableView().getItems().add(mf);
            }
        }
    }

    public void editProgram() {
        PersistenceDoctor persistenceDoctor = new PersistenceDoctor();
        List<Doctor> doctorList = persistenceDoctor.readXML();
        for(Doctor doctor : doctorList) {
            if(doctor.getId() == Integer.parseInt(iViewDoctor.getTfIdToUpdate())) {
                if (!iViewDoctor.getTfEditStartProgram().isEmpty())
                    doctor.setStartProgram(iViewDoctor.getTfEditStartProgram());
                if (!iViewDoctor.getTfEditEndProgram().isEmpty())
                    doctor.setFinishProgram(iViewDoctor.getTfEditEndProgram());
                persistenceDoctor.updateDoctor(doctor);
                break;
            }
        }
    }
}
