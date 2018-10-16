package datamodels;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Shamyyoun on 3/29/2015.
 */
public class Cache {
    /**
     * method used to update language in SP
     */
    public static void updateLanguage(Context context, String value) {
        updateCachedString(context, Constants.SP_SETTINGS, Constants.SP_KEY_LANGUAGE, value);
    }

    /**
     * method used to get language from SP
     */
    public static String getLanguage(Context context) {
        return getCachedString(context, Constants.SP_SETTINGS, Constants.SP_KEY_LANGUAGE);
    }

    /**
     * method used to update standings response in SP
     */
    public static void updateStandingsResponse(Context context, String value) {
        updateCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_STANDINGS, value);
    }

    /**
     * method used to get standings response from SP
     */
    public static String getStandingsResponse(Context context) {
        return getCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_STANDINGS);
    }

    /**
     * method used to update top scorers response in SP
     */
    public static void updateScorersResponse(Context context, String value) {
        updateCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_SCORERS, value);
    }

    /**
     * method used to get top scorers response from SP
     */
    public static String getScorersResponse(Context context) {
        return getCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_SCORERS);
    }

    /**
     * method used to update assists response in SP
     */
    public static void updateAssistsResponse(Context context, String value) {
        updateCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_ASSISTS, value);
    }

    /**
     * method used to get assists response from SP
     */
    public static String getAssistsResponse(Context context) {
        return getCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_ASSISTS);
    }

    /**
     * method used to update matches response in SP
     */
    public static void updateMatchesResponse(Context context, String value) {
        updateCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_MATCHES, value);
    }

    /**
     * method used to get matches response from SP
     */
    public static String getMatchesResponse(Context context) {
        return getCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_MATCHES);
    }

    /**
     * method used to update winners response in SP
     */
    public static void updateWinnersResponse(Context context, String value) {
        updateCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_WINNERS, value);
    }

    /**
     * method used to get winners response from SP
     */
    public static String getWinnersResponse(Context context) {
        return getCachedString(context, Constants.SP_RESPONSES, Constants.SP_KEY_WINNERS);
    }



    /*
     * method, used to update string value in SP
     */
    private static void updateCachedString(Context context, String spName, String valueName, String value) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(valueName, value);
        editor.commit();
    }

    /*
     * method, used to get cached String from SP
     */
    private static String getCachedString(Context context, String spName, String valueName) {
        SharedPreferences sp = context.getSharedPreferences(spName, Context.MODE_PRIVATE);
        String value = sp.getString(valueName, null);

        return value;
    }
}
