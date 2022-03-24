package com.example.projecttema1ps.model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class PersistenceMedicalFile extends DefaultHandler {
    List<MedicalFile> medicalFileList = new ArrayList<>();
    String xmlMedicalFileName = "medicalFiles.xml";
    String tempValue;
    MedicalFile tempMedicalFile;
    List<Animal> animalList = new ArrayList<>();

    public void addMedicalFile(MedicalFile medicalFile) {
        readXML();
        for (int i = 0; i < medicalFileList.size(); i++) {
            MedicalFile mf = medicalFileList.get(i);
            if (medicalFile.getIdMedicalFile() == mf.getIdMedicalFile())
                return;
        }
        medicalFileList.add(medicalFile);
        System.out.println(medicalFileList.size());
        System.out.println(medicalFileList);
        writeXML(medicalFileList);
    }

    public void deleteMedicalFile(int id) {
        readXML();
        for (int i = 0; i < medicalFileList.size(); i++) {
            MedicalFile mf = (MedicalFile) medicalFileList.get(i);
            if (mf.getIdMedicalFile() == id) {
                medicalFileList.remove(mf);
            }
        }
        writeXML(medicalFileList);
    }

    public void updateMedicalFile(MedicalFile medicalFile) {
        for (int i = 0; i < medicalFileList.size(); i++) {
            MedicalFile updatedMedicalFile = medicalFileList.get(i);
            if (updatedMedicalFile.getIdMedicalFile() == medicalFile.getIdMedicalFile()) {
                updatedMedicalFile.setDiagnose(medicalFile.getDiagnose());
                updatedMedicalFile.setTreatment(medicalFile.getTreatment());
                updatedMedicalFile.setSymptoms(medicalFile.getSymptoms());
                updatedMedicalFile.setAnimal(medicalFile.getAnimal());
                break;
            }
        }
        writeXML(medicalFileList);
    }

    public List<MedicalFile> readXML() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlMedicalFileName, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return medicalFileList;
    }

    public void writeXML(List<MedicalFile> medicalFileList) {
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream("medicalFiles.xml"));
            xmlStreamWriter.writeStartDocument("1.0");
            xmlStreamWriter.writeStartElement("medicalFiles");
            for (MedicalFile medicalFile : medicalFileList) {
                xmlStreamWriter.writeStartElement("medicalFile");
                xmlStreamWriter.writeAttribute("id", Integer.toString(medicalFile.getIdMedicalFile()));
                xmlStreamWriter.writeAttribute("idDoctor", Integer.toString(medicalFile.getIdDoctor()));

                xmlStreamWriter.writeStartElement("treatment");
                xmlStreamWriter.writeCharacters(medicalFile.getTreatment());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("diagnose");
                xmlStreamWriter.writeCharacters(medicalFile.getDiagnose());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("symptoms");
                for (int s = 0; s < medicalFile.getSymptoms().size(); s++) {
                    xmlStreamWriter.writeStartElement("symptom");
                    xmlStreamWriter.writeCharacters(medicalFile.getSymptoms().get(s));
                    xmlStreamWriter.writeEndElement();
                }
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("idAnimal");
                xmlStreamWriter.writeCharacters(Integer.toString(medicalFile.getAnimal().getId()));
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("name");
                xmlStreamWriter.writeCharacters(medicalFile.getAnimal().getName());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("species");
                xmlStreamWriter.writeCharacters(medicalFile.getAnimal().getSpecies());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("weight");
                xmlStreamWriter.writeCharacters(medicalFile.getAnimal().getWeight());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeEndElement();
            }

            xmlStreamWriter.writeEndElement();
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }


    private void printData() {
        for (MedicalFile tmpMf : medicalFileList) {
            System.out.println(tmpMf.toString());
        }
    }

    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        if (elementName.equalsIgnoreCase("medicalFile")) {
            tempMedicalFile = new MedicalFile();
            tempMedicalFile.setIdMedicalFile(Integer.parseInt(attributes.getValue("id")));
            tempMedicalFile.setIdDoctor(Integer.parseInt(attributes.getValue("idDoctor")));
        }
    }

    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
        if (element.equals("medicalFile")) {
            medicalFileList.add(tempMedicalFile);
        }
        if (element.equals("treatment")) {
            tempMedicalFile.setTreatment(tempValue);
        }
        if (element.equals("diagnose")) {
            tempMedicalFile.setDiagnose(tempValue);
        }
        if (element.equals("symptom")) {
            tempMedicalFile.getSymptoms().add(tempValue);
        }
        if (element.equals("idAnimal")) {
            tempMedicalFile.getAnimal().setId(Integer.parseInt(tempValue));
        }
        if (element.equals("name")) {
            tempMedicalFile.getAnimal().setName(tempValue);
        }
        if (element.equals("species")) {
            tempMedicalFile.getAnimal().setSpecies(tempValue);
        }
        if (element.equals("weight")) {
            tempMedicalFile.getAnimal().setWeight(tempValue);
        }
    }

    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        tempValue = new String(ac, i, j);
    }
}


