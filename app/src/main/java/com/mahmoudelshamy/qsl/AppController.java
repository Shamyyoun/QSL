package com.mahmoudelshamy.qsl;

import android.app.Application;
import android.content.Context;

import datamodels.Constants;

/**
 * Created by Dahman on 9/20/2015.
 */
public class AppController extends Application {
    public static final String QSL_API_KEY = "XywLPESrLNc+Gs8RgYpA=f965AKMgtcLL";
    public static final int SEASON_ID = 101;
    private String endPoint;
    private String language = Constants.LANG_EN;

    @Override
    public void onCreate() {
        super.onCreate();
        endPoint = "http://api.superkoora.com/" + language + "/QSL-Mobile";
    }

    /**
     * method used to return current application instance
     */
    public static AppController getInstance(Context context) {
        return (AppController) context.getApplicationContext();
    }

    /**
     * static method, used to get url end point
     */
    public static String getEndPoint(Context context) {
        return getInstance(context).endPoint;
    }

    /**
     * static method, used to return current language
     */
    public static String getLanguage(Context context) {
        return getInstance(context).language;
    }
}
