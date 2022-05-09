package com.assignment.online.utils.models;

import java.io.Serializable;
import java.util.Date;

public class Commission implements Serializable {
    public static final String COMMISSION_ID="commissionId";
    public static final String COMMISSION="commission";

    public int commissionId;
    public int personId;
    public double southern;
    public double costal;
    public double eastern;
    public double lebanon;
    public double northern;
    public double commission;
    public int month;
    public int year;
    public Date registerationDate;

    public Commission(int commissionId, int personId, double southern, double costal, double eastern, double lebanon, double northern, double commission, int month, int year, Date registerationDate) {
        this.commissionId = commissionId;
        this.personId = personId;
        this.southern = southern;
        this.costal = costal;
        this.eastern = eastern;
        this.lebanon = lebanon;
        this.northern = northern;
        this.commission = commission;
        this.month = month;
        this.year = year;
        this.registerationDate = registerationDate;
    }

    public Commission(int commissionId, int personId, double southern, double costal, double eastern, double lebanon, double northern, double commission, int month, int year) {
        this.commissionId = commissionId;
        this.personId = personId;
        this.southern = southern;
        this.costal = costal;
        this.eastern = eastern;
        this.lebanon = lebanon;
        this.northern = northern;
        this.commission = commission;
        this.month = month;
        this.year = year;
    }
}
