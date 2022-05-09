package com.assignment.online.utils.models;

import java.io.Serializable;
import java.util.Date;

public class Person implements Serializable {

    //Login
    public static final String USERNAME="username";
    public static final String PASSWORD="password";

    public static final String LOGIN="login";
    public static final String ADMIN="admin";
    public static final String PERSON="person";

    public static final String PERSON_NUMBER="personNumber";
    public static final String PERSON_NAME="personName";
    public static final String PERSON_REGION_ID="personRegionId";
    public static final String REGISTERATION_DATE="registerationDate";
    public static final String IMAGE="image";
    public static final String PERSON_ID="personId";
    public static final String PERSON_PASSWORD = "personPassword";
    public static final String NAME = "name";
    public static final String SALES_PERSON_NUMBER = "salesPersonNumber";

    public int personId;
    public int personNumber;
    public String personName;

    //in view data
    public String personPassword;
    public String image;
    public Date registerationDate;
    public int personRegionId;

    public Person(int personId, int personNumber, String personName, String personPassword, String image, Date registerationDate, int personRegionId) {
        this.personId = personId;
        this.personNumber = personNumber;
        this.personName = personName;
        this.personPassword = personPassword;
        this.image = image;
        this.registerationDate = registerationDate;
        this.personRegionId = personRegionId;
    }
}
