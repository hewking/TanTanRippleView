//package com.github.hewking.github.retrofit.gankio24;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.widget.Button;
//import android.widget.TextView;
//
//import butterknife.Bind;
//import butterknife.ButterKnife;
//import butterknife.OnClick;
//import hewking.github.customviewdemo.R;
//import retrofit2.Call;
//import retrofit2.Response;
//import retrofit2.Retrofit;
//
///**
// * Created by hewking on 2016/3/26.
// */
//public class TestRetrofitActivity2 extends Activity {
//    @Bind(R.id.click_me_BN)
//    Button clickMeBN;
//    @Bind(R.id.result_TV)
//    TextView resultTV;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        ButterKnife.bind(this);
//    }
//
//    @OnClick(R.id.click_me_BN)
//    public void onClick() {
//        getMovie();
//    }
//
//    //进行网络请求
//    private void getMovie(){
//        String baseUrl = "https://api.douban.com/v2/movie/";
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        MovieService movieService = retrofit.create(MovieService.class);
//        Call<MovieEntity> call = movieService.getTopMovie(0, 10);
//        call.enqueue(new Callback<MovieEntity>() {
//            @Override
//            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
//                resultTV.setText(response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<MovieEntity> call, Throwable t) {
//                resultTV.setText(t.getMessage());
//            }
//        });
//    }
//}
