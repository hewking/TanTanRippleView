package com.dlodlo.api.model;

import java.util.List;


public class UrlList{

    private static final String FIELD_VALUE = "value";


    private List<String> mValues;


    public UrlList(){

    }

    public void setValues(List<String> values) {
        mValues = values;
    }

    public List<String> getValues() {
        return mValues;
    }


}