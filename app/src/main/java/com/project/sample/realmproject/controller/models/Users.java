package com.project.sample.realmproject.controller.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by yoyo on 8/24/16.
 */
public class Users extends RealmObject {

    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_GENDER = "gender";

    @PrimaryKey
    private String name;

    private String gender;


    //region Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
    //endregion

    //region Getters
    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
    //endregion
}
