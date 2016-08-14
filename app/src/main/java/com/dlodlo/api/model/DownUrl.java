package com.dlodlo.api.model;



public class DownUrl{

    private static final String FIELD_VALUE = "value";


    private String mValue;


    public DownUrl(){

    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }


}