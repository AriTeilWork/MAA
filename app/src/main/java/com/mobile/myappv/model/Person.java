package com.mobile.myappv.model;

import java.util.List;

public class Person {
    private String firstName;
    private String lastName;
    private String phone;
    private String education;
    private List<String> hobbies;

    public Person(String firstName, String lastName, String phone,
                  String education, List<String> hobbies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.education = education;
        this.hobbies = hobbies;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPhone() { return phone; }
    public String getEducation() { return education; }
    public List<String> getHobbies() { return hobbies; }
}