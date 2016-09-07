package com.dbtest;


import java.util.List;

public interface MediaRecentDaoInface {

     MediaRecentEntity queryResoruceType(String filePath);

    List<MediaRecentEntity>  queryAllMediaRecent();

     MediaRecentEntity insertInfo(MediaRecentEntity entity);

    void update(MediaRecentEntity entity);

    void delete(MediaRecentEntity entity);

}
