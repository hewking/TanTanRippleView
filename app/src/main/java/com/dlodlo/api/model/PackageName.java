package com.dlodlo.api.model;



public class PackageName{

    private static final String FIELD_VALUE = "value";


    private String mValue;


    public PackageName(){

    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }


}