package com.dlodlo.api.model;



public class Description{

    private static final String FIELD_VALUE = "value";


    private String mValue;


    public Description(){

    }

    public void setValue(String value) {
        mValue = value;
    }

    public String getValue() {
        return mValue;
    }


}