package com.example.projecttema1ps.model;

import org.json.simple.JSONObject;

public class Animal {
    private int id;
    private String name;
    private String species;
    private String weight;

    public Animal(int id, String name, String species, String weight) {
        this.id = id;
        this.name = name;
        this.species = species;
        this.weight = weight;
    }

    public Animal() {

    }

    public JSONObject toJsonObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("id", id);
        jsonObject.put("name", name);
        jsonObject.put("species", species);
        jsonObject.put("weight", weight);
        return jsonObject;
    }

    @Override
    public String toString() {
        return "Animal: " + "\n" +
                " id = " + id + "\n" +
                " name = " + name + "\n" +
                " species = " + species + "\n" +
                " weight = " + weight + " kg";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
