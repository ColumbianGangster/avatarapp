package com.example.linearalgebra.avatarapp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Model {

    @SerializedName("people")
    @Expose
    private ArrayList<Person> people = new ArrayList<Person>();

    /**
     * 
     * @return
     *     The people
     */
    public ArrayList<Person> getPeople() {
        return people;
    }

    /**
     * 
     * @param people
     *     The people
     */
    public void setPeople(ArrayList<Person> people) {
        this.people = people;
    }

}
