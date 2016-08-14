package com.dlodlo.api.model;



public class VersionName{

    private static final String FIELD_VALUE = "value";


    private double mValue;


    public VersionName(){

    }

    public void setValue(double value) {
        mValue = value;
    }

    public double getValue() {
        return mValue;
    }


}