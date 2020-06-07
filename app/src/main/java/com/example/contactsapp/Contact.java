package com.example.contactsapp;

public class Contact {
    String name, number, email, organization, relationship;
    int id;

    public Contact() {
    }

    public Contact(int id, String name, String number, String email, String organization, String relationship) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.organization = organization;
        this.id = id;
        this.relationship = relationship;
    }

    public Contact(String name, String number, String email, String organization, String relationship) {
        this.name = name;
        this.number = number;
        this.email = email;
        this.organization = organization;
        this.relationship = relationship;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRelationship() {
        return relationship;
    }

    public void setRelationship(String relationship) {
        this.relationship = relationship;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

