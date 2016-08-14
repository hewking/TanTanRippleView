//package com.github.hewking.github.retrofit;
//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.Log;
//
//import hewking.github.customviewdemo.R;
//import rx.Observable;
//import rx.Subscriber;
//import rx.android.schedulers.AndroidSchedulers;
//import rx.schedulers.Schedulers;
//
///**
// * Created by hewking on 2016/3/10.
// */
//public class TestRetrofitActivity extends Activity {
//    public static final String TAG = TestRetrofitActivity.class.getSimpleName();
//    Observable<String> observable;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.retrofit);
//        Log.e(TAG,"oncreate");
//
//        observable = new RetrofitModel().testObservable;
//        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
//        observable.subscribe(new Subscriber<String>() {
//            @Override
//            public void onCompleted() {
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.e(TAG,"onnext s = " + s);
//            }
//        });
//    }
//}
