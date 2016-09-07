package com.dbtest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import hewking.github.customviewdemo.R;

public class DBTestActivity extends AppCompatActivity {

    private Button btn_add;
    private Button btn_delete;
    private TextView tv_desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbtest);

        btn_add = (Button) findViewById(R.id.btn_add);
        btn_delete = (Button) findViewById(R.id.btn_delete);
        tv_desc = (TextView) findViewById(R.id.tv_desc);


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaRecentEntity mediaRecentEntity = new MediaRecentEntity();
                mediaRecentEntity.setPath("//sss/aaa/aaa/bb" + System.currentTimeMillis());
                mediaRecentEntity.setResourceType(System.currentTimeMillis() + "");

                mediaRecentEntity.setResourceId("aaa" + System.currentTimeMillis());
                mediaRecentEntity.setIconUrl("bbb" + System.currentTimeMillis());
                mediaRecentEntity.setPlay_duration(System.currentTimeMillis());

                MediaRecentDao.getInstance(DBTestActivity.this).insertInfo(mediaRecentEntity);
            }
        });

        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<MediaRecentEntity> mediaRecentEntities = MediaRecentDao.getInstance(DBTestActivity.this).queryAllMediaRecent();
                StringBuilder sb = new StringBuilder();
                for (MediaRecentEntity entity:mediaRecentEntities ) {
                   sb.append(entity.getPath() + ":" + entity.getResourceType());


                    sb.append(entity.getIconUrl()).append(entity.getPlay_duration());
                }
                tv_desc.setText(sb.toString());
            }
        });
    }

}
