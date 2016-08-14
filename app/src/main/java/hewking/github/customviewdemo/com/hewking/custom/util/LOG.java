package hewking.github.customviewdemo.com.hewking.custom.util;

import android.util.Log;

/**
 * Created by hewking on 2016/1/29.
 */
public class LOG {

    public static boolean DEBUG = true;

    public  static  void cjh(String text){
        if(DEBUG)
            Log.e("cjh",text);
    }

}
