package com.mobile.myappv.manager;

import com.mobile.myappv.model.Person;

import java.util.ArrayList;
import java.util.List;

public class PhoneCatalog {

    private List<Person> people = new ArrayList<>();

    public void addPerson(Person person) {
        people.add(person);
    }

    public List<Person> getPeople() {
        return people;
    }

    public List<String> generateSearchData() {
        List<String> list = new ArrayList<>();

        for (Person p : people) {
            String h = (p.getHobbies() != null) ? String.join(",", p.getHobbies()) : "";
            String f = p.getFirstName();
            String l = p.getLastName();
            String ph = p.getPhone();
            String ed = p.getEducation();

            // name
            list.add(f + ";" + l + ";" + ph + ";" + ed + ";" + h);
            // last
            list.add(l + ";" + f + ";" + ph + ";" + ed + ";" + h);
            // 3. phone
            list.add(ph + ";" + f + ";" + l + ";" + ed + ";" + h);
            // 4. education
            list.add(ed + ";" + f + ";" + l + ";" + ph + ";" + h);
        }

        return list;
    }
}