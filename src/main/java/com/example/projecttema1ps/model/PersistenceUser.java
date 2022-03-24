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

public class PersistenceUser extends DefaultHandler {
    private boolean hasType = false;
    private boolean hasName = false;
    private boolean hasUsername = false;
    private boolean hasPassword = false;

    private ArrayList userList = new ArrayList<>();
    private User user = null;

    public ArrayList<User> getUserList() {
        return userList;
    }

    public void addUser(User user) {
        readXML();
        for (int i = 0; i < userList.size(); i++) {
            User u = (User) userList.get(i);
            if (user.getId() != u.getId()) {
                userList.add(user);
                break;
            }
        }
        writeXML(userList);
    }

    public void deleteUser(int id) {
        for (int i = 0; i < userList.size(); i++) {
            User user = (User) userList.get(i);
            if (user.getId() == id) {
                userList.remove(user);
            }
        }
        writeXML(userList);
    }

    public void updateUser(User user) {
        readXML();
        for (int i = 0; i < userList.size(); i++) {
            User updatedUser = (User) userList.get(i);
            if (updatedUser.getId() == user.getId()) {
                if (user.getName() == null)
                    updatedUser.setName(((User) userList.get(i)).getName());
                else updatedUser.setName(user.getName());
                if (user.getRole() == null)
                    updatedUser.setRole(((User) userList.get(i)).getRole());
                else updatedUser.setRole(user.getRole());
                if (user.getUsername() == null)
                    updatedUser.setUsername(((User) userList.get(i)).getUsername());
                else updatedUser.setUsername(user.getUsername());
                if (user.getPassword() == null)
                    updatedUser.setPassword(((User) userList.get(i)).getPassword());
                else updatedUser.setPassword(user.getPassword());
            }
        }
        writeXML(userList);
    }

    public static void writeXML(ArrayList<User> userList) {
        try {
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newFactory();
            XMLStreamWriter xmlStreamWriter = xmlOutputFactory.createXMLStreamWriter(new FileOutputStream("users.xml"));
            xmlStreamWriter.writeStartDocument("1.0");
            xmlStreamWriter.writeStartElement("users");
            for (User user : userList) {
                xmlStreamWriter.writeStartElement("user");
                xmlStreamWriter.writeAttribute("id", Integer.toString(user.getId()));

                xmlStreamWriter.writeStartElement("type");
                xmlStreamWriter.writeCharacters(user.getRole());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("name");
                xmlStreamWriter.writeCharacters(user.getName());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("username");
                xmlStreamWriter.writeCharacters(user.getUsername());
                xmlStreamWriter.writeEndElement();

                xmlStreamWriter.writeStartElement("password");
                xmlStreamWriter.writeCharacters(user.getPassword());
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

    public ArrayList readXML() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        try {
            SAXParser saxParser = saxParserFactory.newSAXParser();
            PersistenceUser handler = new PersistenceUser();
            saxParser.parse(new File("/Users/mac/Documents/JavaFX/ProjectTema1PS/users.xml"), handler);
            List<User> handlerUserList = handler.getUserList();
            for (User user : handlerUserList) {
                userList.add(user);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equalsIgnoreCase("user")) {
            String id = attributes.getValue("id");
            user = new User();
            user.setId(Integer.parseInt(id));
            if (userList == null)
                userList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("type")) {
            hasType = true;
        } else if (qName.equalsIgnoreCase("name")) {
            hasName = true;
        } else if (qName.equalsIgnoreCase("username")) {
            hasUsername = true;
        } else if (qName.equalsIgnoreCase("password")) {
            hasPassword = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equalsIgnoreCase("user")) {
            userList.add(user);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        if (hasType) {
            user.setRole(new String(ch, start, length));
            hasType = false;
        } else if (hasName) {
            user.setName(new String(ch, start, length));
            hasName = false;
        } else if (hasUsername) {
            user.setUsername(new String(ch, start, length));
            hasUsername = false;
        } else if (hasPassword) {
            user.setPassword(new String(ch, start, length));
            hasPassword = false;
        }
    }

    public User validateLogin(String username, String password) {
        readXML();
        System.out.println(username + " " + password);
        for (int i = 0; i < userList.size(); i++) {
            User user = (User) userList.get(i);
            if (username.equals(user.getUsername()) && password.equals(user.getPassword())) {
                System.out.println(user);
                return user;
            }
        }
        return null;
    }
}
