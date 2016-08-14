package com.github.hewking.github.retrofit;

import rx.Observable;

/**
 * Created by hewking on 2016/3/10.
 */
public interface Service {
    String BASE_URL = "http://con.dlodlo.com/resource/v2/listGame?offset=10&size=8";
    Observable<String> getTestInfo();
}
