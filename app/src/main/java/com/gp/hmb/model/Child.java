package com.gp.hmb.model;

public class Child {
    private String childName, fatherName, dateOfBirth, kind, bloodType, tall, weight;
    private int months = 0;


    public Child() {
    }

    public Child(String childName, String fatherName, String dateOfBirth, String kind, String bloodType, String tall, String weight) {
        this.childName = childName;
        this.fatherName = fatherName;
        this.dateOfBirth = dateOfBirth;
        this.kind = kind;
        this.bloodType = bloodType;
        this.tall = tall;
        this.weight = weight;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public String getChildName() {
        return childName;
    }

    public void setChildName(String childName) {
        this.childName = childName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public String getTall() {
        return tall;
    }

    public void setTall(String tall) {
        this.tall = tall;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
}
