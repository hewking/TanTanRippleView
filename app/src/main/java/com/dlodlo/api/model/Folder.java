package com.dlodlo.api.model;

import java.util.List;


public class Folder{

    private static final String FIELD_ROWS = "rows";
//    private static final String FIELD_@TYPE = "@type";
    private static final String FIELD_USERDATA = "userdata";


    private List<Row> mRows;
//    private String m@type;
    private Userdatum mUserdatum;


    public Folder(){

    }

    @Override
    public String toString() {
        return "Folder{" +
                "mRows=" + mRows +
                ", mUserdatum=" + mUserdatum +
                '}';
    }

    public void setRows(List<Row> rows) {
        mRows = rows;
    }

    public List<Row> getRows() {
        return mRows;
    }

//    public void set@type(String @type) {
//        m@type = @type;
//    }
//
//    public String get@type() {
//        return m@type;
//    }

    public void setUserdatum(Userdatum userdatum) {
        mUserdatum = userdatum;
    }

    public Userdatum getUserdatum() {
        return mUserdatum;
    }


}