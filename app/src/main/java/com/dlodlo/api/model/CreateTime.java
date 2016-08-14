package com.dlodlo.api.model;



public class CreateTime{

    private static final String FIELD_VALUE = "value";


    private String mValue;


    public CreateTime(){

    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }


}