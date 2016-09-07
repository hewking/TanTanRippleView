package com.dbtest;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by Administrator on 2016/6/4.
 */
public class RecentMediaOpenHelper extends SQLiteOpenHelper {

    public final static String DATABASE_NAME = "RecentMedia.db";
    public final static int DATABASE_VERSION = 2;

    public RecentMediaOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS "
                + MediaRecentEntity.TABLE_NAME
                + String.format(
                "("
                        + "%s INTEGER PRIMARY KEY AUTOINCREMENT, " // id, download id
                        + "%s VARCHAR, " // resourceType
                        + "%s VARCHAR, " // path
                        + "%s INTEGER, " // last_access
                        + "%s VARCHAR, " // RESOURCE_ID
                        + "%s INTEGER, " // PLAYED_DURATION
                        + "%s VARCHAR " // ICONURL
//                        + "%s INTEGER " // ISLOCALRESOURCES
                        + ")"
                , MediaRecentEntity.ID
                , MediaRecentEntity.RESOURCETYPE
                , MediaRecentEntity.PATH
                ,MediaRecentEntity.LAST_ACCESS
                ,MediaRecentEntity.RESOURCE_ID
                ,MediaRecentEntity.PLAYED_DURATION
                ,MediaRecentEntity.ICONURL
//                ,MediaRecentEntity.ISLOCALRESOURCES

        ));
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion){
            case 1:
                db.execSQL("DELETE FROM " +MediaRecentEntity.TABLE_NAME);
                db.execSQL(String.format("ALTER TABLE" + " %s ADD COLUMN" + " %s VARCHAR"
                        ,MediaRecentEntity.TABLE_NAME,MediaRecentEntity.RESOURCE_ID));
                db.execSQL(String.format("ALTER TABLE" + " %s ADD COLUMN" + " %s INTEGER DEFAULT 0"
                        ,MediaRecentEntity.TABLE_NAME,MediaRecentEntity.PLAYED_DURATION));
                db.execSQL(String.format("ALTER TABLE" + " %s ADD COLUMN" + " %s INTEGER DEFAULT 0"
//                        ,MediaRecentEntity.TABLE_NAME,MediaRecentEntity.LAST_ACCESS));
//                db.execSQL(String.format("ALTER TABLE" + " %s ADD COLUMN" + " %s VARCHAR"
                        ,MediaRecentEntity.TABLE_NAME,MediaRecentEntity.ICONURL));
            default:
                break;
        }
    }
}
