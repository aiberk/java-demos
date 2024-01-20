package com.abyiber.familytree.dto;

public class PersonForm {

    private String name;
    private String motherName;
    private String fatherName;

    // Constructors
    public PersonForm() {
    }

    public PersonForm(String name, String motherName, String fatherName) {
        this.name = name;
        this.motherName = motherName;
        this.fatherName = fatherName;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

}
