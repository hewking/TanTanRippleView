package com.dbtest;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/6/4.
 */
public class MediaRecentDao implements MediaRecentDaoInface {

    public static final  String TAG ="MediaRecentDao";

    private RecentMediaOpenHelper mediaOpenHelper;
    private final SQLiteDatabase db;

    private MediaRecentDao(Context context){
        mediaOpenHelper = new RecentMediaOpenHelper(context);
        db = mediaOpenHelper.getWritableDatabase();
    }

    private static MediaRecentDao instance;

    public static MediaRecentDao getInstance(Context context){
        if(instance == null){
            instance = new MediaRecentDao(context);
        }
        return instance;
    }

    @Override
    public MediaRecentEntity queryResoruceType(String filePath) {
        MediaRecentEntity entity = null;
        Cursor cursor = db.query(MediaRecentEntity.TABLE_NAME, null, "filePath = ?", new String[]{filePath}, null, null, null);
        if(cursor.moveToNext()){
            entity = MediaRecentEntity.getFromCursor(cursor);
        }
        cursor.close();
//        db.close();
        return entity;
    }

    @Override
    public List<MediaRecentEntity> queryAllMediaRecent() {
        List<MediaRecentEntity> infos = new ArrayList<>();
        Cursor cursor = db.query(MediaRecentEntity.TABLE_NAME, null, null, null, null, null, MediaRecentEntity.LAST_ACCESS + " DESC");
        while(cursor.moveToNext()){
            infos.add(MediaRecentEntity.getFromCursor(cursor));
        }
        cursor.close();
        return infos;
    }

    public MediaRecentEntity insertInfo(String filePath,String resourceType){
        return  insertInfo(filePath,resourceType,null,0,null);
    }

    public MediaRecentEntity insertInfo(String filePath , String resourceType ,String resourceId,long played_duration,String iconUrl){
        MediaRecentEntity entity = new MediaRecentEntity();
        entity.setPath(filePath);
        entity.setResourceType(resourceType);
        entity.setLast_access(System.currentTimeMillis());
        entity.setResourceId(resourceId);
        entity.setIconUrl(iconUrl);
        entity.setPlay_duration(played_duration);
        return insertInfo(entity);
    }

    @Override
    public MediaRecentEntity insertInfo(MediaRecentEntity entity) {
        boolean succeed = false ;
        if(queryResoruceType(entity.getPath()) != null){
            db.update(MediaRecentEntity.TABLE_NAME, entity.toContentValues(), "filePath = ?", new String[]{entity.getPath()});
        }else{
            succeed  = db.insert(MediaRecentEntity.TABLE_NAME, null, entity.toContentValues()) != -1;
        }
        return succeed ? entity : null;
    }

    @Override
    public void update(MediaRecentEntity entity) {
        db.update(MediaRecentEntity.TABLE_NAME, entity.toContentValues(), "filePath = ?", new String[]{entity.getPath()});
//        db.close();
    }

    @Override
    public void delete(MediaRecentEntity entity) {
        if(queryResoruceType(entity.getPath()) != null){
            int delete = db.delete(MediaRecentEntity.TABLE_NAME, "filePath = ?", new String[]{entity.getPath()});
        }
    }
}
