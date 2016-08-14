package com.dlodlo.api.model;



public class UpdateTime{

    private static final String FIELD_VALUE = "value";


    private String mValue;


    public UpdateTime(){

    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }


}