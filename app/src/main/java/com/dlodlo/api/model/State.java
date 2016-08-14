package com.dlodlo.api.model;



public class State{

    private static final String FIELD_VALUE = "value";


    private int mValue;


    public State(){

    }

    public void setValue(int value) {
        mValue = value;
    }

    public int getValue() {
        return mValue;
    }


}