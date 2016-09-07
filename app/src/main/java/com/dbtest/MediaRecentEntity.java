package com.dbtest;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * Created by Administrator on 2016/6/4.
 */
public class MediaRecentEntity {

    public static final String TABLE_NAME = "RecentMedia";
    public final static String ID = "id";
    public final static String PATH = "filePath";
    public final static String RESOURCETYPE = "resourceType";
    public final static String LAST_ACCESS = "last_access";
    public final static String ISLOCALRESOURCES = "isLocalresources";
    public final static String RESOURCE_ID = "resourceId";
    public final static String ICONURL = "icon";
    public final static String PLAYED_DURATION = "played_duration";

    public String getResourceId() {
        return resourceId;
    }

    public void setResourceId(String resourceId) {
        this.resourceId = resourceId;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public long getPlay_duration() {
        return play_duration;
    }

    public void setPlay_duration(long play_duration) {
        this.play_duration = play_duration;
    }

    private int id;
    private String path;
    private String resourceType;

    private long last_access;
    private String resourceId;
    private String iconUrl;
    private long play_duration;

    public long getLast_access() {
        return last_access;
    }

    public void setLast_access(long last_access) {
        this.last_access = last_access;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return "MediaRecentEntity{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", resourceType='" + resourceType + '\'' +
                '}';
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ContentValues toContentValues() {
        ContentValues cv = new ContentValues();
//        cv.put(ID, "NULL");
        cv.put(PATH, path);
        cv.put(RESOURCETYPE, resourceType);
        cv.put(LAST_ACCESS,last_access);
        if(checkNotNull(resourceId)){
            cv.put(RESOURCE_ID,resourceId);
        }
        if(checkNotNull(iconUrl)){
            cv.put(ICONURL,iconUrl);
        }
        if(play_duration != 0){
            cv.put(PLAYED_DURATION,play_duration);
        }
        return cv;
    }

    private boolean checkNotNull(String string){
        if (string == null || string.equals("")){
            return false;
        }
        return true;
    }

    public static MediaRecentEntity getFromCursor(Cursor cursor){
        MediaRecentEntity info = new MediaRecentEntity();
        info.path = cursor.getString(cursor.getColumnIndex(PATH));
        info.resourceType = cursor.getString(cursor.getColumnIndex(RESOURCETYPE));
        info.last_access = cursor.getLong(cursor.getColumnIndex(LAST_ACCESS));
        info.resourceId = cursor.getString(cursor.getColumnIndex(RESOURCE_ID));
        info.iconUrl = cursor.getString(cursor.getColumnIndex(ICONURL));
        info.play_duration = cursor.getLong(cursor.getColumnIndex(PLAYED_DURATION));
        return info;
    }
}
