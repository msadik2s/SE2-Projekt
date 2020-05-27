package org.HardCore.model.objects.dto;

public class Student extends User{
    private String vorname = "Student";


    public String getVorname() {
        return vorname;
    }

    public void setVorname(String vorname) {
        this.vorname = vorname;
    }
}
