package com.dlodlo.api.model;



public class PraiseNum{

    private static final String FIELD_VALUE = "value";


    private int mValue;


    public PraiseNum(){

    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }


}