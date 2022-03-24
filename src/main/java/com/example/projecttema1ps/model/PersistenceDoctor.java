package com.example.projecttema1ps.model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.*;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class PersistenceDoctor extends DefaultHandler {
    List<Doctor> doctorList = new ArrayList<>();
    String xmlDoctorsFileName = "doctors.xml";
    String tempValue;
    Doctor tempDoctor;
    Doctor doctor = null;

    public void addDoctor(Doctor doctor) {
        readXML();
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor d = doctorList.get(i);
            if (doctor.getId() != d.getId()) {
                doctorList.add(doctor);
                break;
            }
        }
        writeXML(doctorList);
    }

    public void deleteDoctor(int id) {
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor doctor = (Doctor) doctorList.get(i);
            if (doctor.getId() == id) {
                doctorList.remove(doctor);
            }
        }
        writeXML(doctorList);
    }

    public void updateDoctor(Doctor doctor) {
        readXML();
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor updatedDoctor = (Doctor) doctorList.get(i);
            if (updatedDoctor.getId() == doctor.getId()) {
                if (doctor.getName() == null)
                    updatedDoctor.setName(((Doctor) doctorList.get(i)).getName());
                else updatedDoctor.setName(doctor.getName());
                updatedDoctor.setRole(doctor.getRole());
                if (doctor.getStartProgram() == null)
                    updatedDoctor.setStartProgram(((Doctor) doctorList.get(i)).getStartProgram());
                else updatedDoctor.setStartProgram(doctor.getStartProgram());
                if (doctor.getFinishProgram() == null)
                    updatedDoctor.setFinishProgram(((Doctor) doctorList.get(i)).getFinishProgram());
                else updatedDoctor.setFinishProgram(doctor.getFinishProgram());
                if (doctor.getUsername() == null)
                    updatedDoctor.setUsername(((Doctor) doctorList.get(i)).getUsername());
                else updatedDoctor.setUsername(doctor.getUsername());
                if (doctor.getPassword() == null)
                    updatedDoctor.setPassword(((Doctor) doctorList.get(i)).getPassword());
                else updatedDoctor.setPassword(doctor.getPassword());
            }
        }
        writeXML(doctorList);
    }

    public List<Doctor> readXML() {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            parser.parse(xmlDoctorsFileName, this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return doctorList;
    }

    public void writeXML(List<Doctor> doctorsList) {
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream("doctors.xml"));
            xmlStreamWriter.writeStartDocument("1.0");
            xmlStreamWriter.writeStartElement("doctors");
            for (Doctor doctor : doctorsList) {
                xmlStreamWriter.writeStartElement("doctor");
                xmlStreamWriter.writeAttribute("id", Integer.toString(doctor.getId()));

                xmlStreamWriter.writeStartElement("type");
                xmlStreamWriter.writeCharacters(doctor.getRole());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("name");
                xmlStreamWriter.writeCharacters(doctor.getName());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("startProgram");
                xmlStreamWriter.writeCharacters(doctor.getStartProgram());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("finishProgram");
                xmlStreamWriter.writeCharacters(doctor.getFinishProgram());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("consults");
                for (int c = 0; c < doctor.getConsults().size(); c++) {
                    xmlStreamWriter.writeStartElement("consult");
                    xmlStreamWriter.writeCharacters(doctor.getConsults().get(c));
                    xmlStreamWriter.writeEndElement();
                }
                xmlStreamWriter.writeEndElement();


                xmlStreamWriter.writeStartElement("username");
                xmlStreamWriter.writeCharacters(doctor.getUsername());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("password");
                xmlStreamWriter.writeCharacters(doctor.getPassword());
                xmlStreamWriter.writeEndElement();
                xmlStreamWriter.writeEndElement();
            }
            xmlStreamWriter.writeEndElement();
            xmlStreamWriter.flush();
            xmlStreamWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startElement(String s, String s1, String elementName, Attributes attributes) throws SAXException {
        if (elementName.equalsIgnoreCase("doctor")) {
            tempDoctor = new Doctor();
            tempDoctor.setId(Integer.parseInt(attributes.getValue("id")));
        }
    }

    @Override
    public void endElement(String s, String s1, String element) throws SAXException {
        if (element.equalsIgnoreCase("doctor")) {
            doctorList.add(tempDoctor);
        }
        if (element.equals("type")) {
            tempDoctor.setRole(tempValue);
        }
        if (element.equals("name")) {
            tempDoctor.setName(tempValue);
        }
        if (element.equals("consult")) {
            tempDoctor.getConsults().add(tempValue);
        }
        if (element.equals("startProgram")) {
            tempDoctor.setStartProgram(tempValue);
        }
        if (element.equals("finishProgram")) {
            tempDoctor.setFinishProgram(tempValue);
        }
        if (element.equals("username")) {
            tempDoctor.setUsername(tempValue);
        }
        if (element.equals("password")) {
            tempDoctor.setPassword(tempValue);
        }
    }

    @Override
    public void characters(char[] ac, int i, int j) throws SAXException {
        tempValue = new String(ac, i, j);
    }

    public Doctor validateLogin(String username, String password) {
        readXML();
        System.out.println(username + " " + password);
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor doctor = doctorList.get(i);
            if (username.equals(doctor.getUsername()) && password.equals(doctor.getPassword())) {
                System.out.println(doctor);
                return doctor;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        PersistenceDoctor persistenceDoctor = new PersistenceDoctor();
        List<Doctor> list = persistenceDoctor.readXML();
        System.out.println(list);
    }
}

