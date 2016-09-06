package com.top10News.application;

import android.app.Application;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.top10News.util.Constants;

/**
 * Created by grumoon on 2015/1/14.
 */
public class PTRApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Constants.DisplayUtil.init(getApplicationContext());

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(getApplicationContext()).build();

        ImageLoader.getInstance().init(configuration);
    }
}
