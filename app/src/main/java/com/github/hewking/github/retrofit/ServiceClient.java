//package com.github.hewking.github.retrofit;
//
//import com.squareup.okhttp.OkHttpClient;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.lang.reflect.Type;
//
//import retrofit2.Converter;
//
////import retrofit.RestAdapter;
////import retrofit.client.OkClient;
////import retrofit.converter.ConversionException;
////import retrofit.converter.Converter;
////import retrofit.mime.MimeUtil;
////import retrofit.mime.TypedInput;
////import retrofit.mime.TypedOutput;
//
///**
// * Created by hewking on 2016/3/10.
// */
//public class ServiceClient {
//
//    private static OkHttpClient mClient;
//    private static Service mService;
//
//    private ServiceClient() {
//
//    }
//
//    public static OkHttpClient getInstance(){
//        if(mClient == null){
//            synchronized (ServiceClient.class){
//                if (mClient == null){
//                    mClient = new OkHttpClient();
//                }
//            }
//        }
//        return mClient;
//    }
//
//    public static Service getService(){
//        if(mService == null){
//             createService();
//        }
//        return  mService;
//    }
//
//    private static void createService(){
//        mService = createAdapter().create(Service.class);
//    }
//
//    private static RestAdapter createAdapter(){
//        return new RestAdapter.Builder().setEndpoint(Service.BASE_URL)
//                .setClient(new OkClient(getInstance())).setConverter(new Converter() {
//                    @Override
//                    public Object fromBody(TypedInput body, Type type) throws ConversionException {
//                        return getString(body);
//                    }
//
//                    @Override
//                    public TypedOutput toBody(Object object) {
//                        return null;
//                    }
//                })
//                .build();
//    }
//
//
//    private static final String CHARSET_DEFAULT = "utf-8";
//    private static final int BUFFER_SIZE = 0x400;
//    private static String getString(TypedInput body) throws ConversionException {
//        String charset = CHARSET_DEFAULT;
//        if (body.mimeType() != null) {
//            charset = MimeUtil.parseCharset(body.mimeType(), charset);
//        }
//        InputStreamReader isr = null;
//        String result;
//        try {
//            isr = new InputStreamReader(body.in(), charset);
//            StringBuilder sb = new StringBuilder();
//            char[] buf = new char[BUFFER_SIZE];
//            for (; ; ) {
//                int res = isr.read(buf, 0, buf.length);
//                if (res < 0)  {
//                    break;
//                }
//                sb.append(buf, 0, res);
//            }
//            result = sb.toString();
//            return result;
//        } catch (IOException e) {
//            throw new ConversionException(e);
//        } finally {
//            if (isr != null) {
//                try {
//                    isr.close();
//                } catch (IOException ignored) {
//                }
//            }
//        }
//    }
//
//}
