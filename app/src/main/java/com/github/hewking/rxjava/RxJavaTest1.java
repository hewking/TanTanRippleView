package com.github.hewking.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Func1;

/**
 * Created by hewking on 2016/2/22.
 */
public class RxJavaTest1 extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        testRxJava1();
//        RxJavaTest2();
        testRxjava3();
    }


    public void testRxJava1(){

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello ");
                subscriber.onNext("world");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.e("cjh","oncompletd");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("cjh","hello world");
                Toast.makeText(RxJavaTest1.this, "worilegoule", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void RxJavaTest2(){
        Subscription subscribe = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("hello ");
                subscriber.onNext("world");
                subscriber.onCompleted();
            }
        }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.e("cjh", s);
            }
        });

        subscribe.unsubscribe();
    }

    public void testRxjava3(){
        Observable.create(new Observable.OnSubscribe<Student>(){
            @Override
            public void call(Subscriber<? super Student> subscriber){

                Student student = new Student();
                student.age = 18;
                student.sex="male";
                Course course = new Course();
                course.english = 60;
                course.math = 100;
                student.cource = course;
                subscriber.onNext(student);
                subscriber.onCompleted();
            }

        }).flatMap(new Func1<Student, Observable<Course>>() {
            @Override
            public Observable<Course> call(Student student){
                return Observable.just(student.cource);
            };
        }).subscribe(new Subscriber<Course>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Course course) {
                Log.e("cjh",course.english + "");
            }
        });

    }

    public static class  Student{
        public int age;
        public String sex;
        public Course cource;

        Student(){

        }
    }
}
