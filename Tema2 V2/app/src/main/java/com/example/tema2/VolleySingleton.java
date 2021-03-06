package com.example.tema2;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;
import androidx.annotation.Nullable;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton instance;
    private RequestQueue requestQueue;
    private ImageLoader imageLoader;
    private  static Context context;

    public ImageLoader getImageLoader() {
        return imageLoader;
    }

    private VolleySingleton(Context context){
        this.context=context;
        this.requestQueue=getRequestQueue();
        this.imageLoader=new ImageLoader(requestQueue, new ImageLoader.ImageCache() {

            private final LruCache<String, Bitmap> cache=new LruCache<String, Bitmap>(20);
            @Nullable
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue= Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized VolleySingleton getInstance(Context context){
        if(instance==null)
            instance=new VolleySingleton(context);
        return instance;
    }

    public <T> void addRequestToQueue(Request<T> request){
        getRequestQueue().add(request);
    }
}
