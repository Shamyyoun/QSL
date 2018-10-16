package datamodels;

/**
 * Created by Shamyyoun on 11/8/2015.
 */
public class Constants {
    // languages
    public static final String LANG_AR = "ar";
    public static final String LANG_EN = "en";

    // SP
    public static final String SP_SETTINGS = "settings";
    public static final String SP_RESPONSES = "responses";
    public static final String SP_KEY_LANGUAGE = "language";
    public static final String SP_KEY_STANDINGS = "standings";
    public static final String SP_KEY_SCORERS = "scorers";
    public static final String SP_KEY_ASSISTS = "assists";
    public static final String SP_KEY_MATCHES = "matches";
    public static final String SP_KEY_WINNERS = "winners";

    // volley request tags
    public static final String VOLLEY_REQ_STANDINGS = "standings";
    public static final String VOLLEY_REQ_SCORERS = "scorers";
    public static final String VOLLEY_REQ_ASSISTS = "assists";
    public static final String VOLLEY_REQ_MATCHES = "matches";
    public static final String VOLLEY_REQ_WINNER = "winner";

    // connection request timeouts
    public static final int CON_TIMEOUT_STANDINGS = 1 * 60 * 1000;
    public static final int CON_TIMEOUT_SCORERS = 1 * 60 * 1000;
    public static final int CON_TIMEOUT_ASSISTS = 1 * 60 * 1000;
    public static final int CON_TIMEOUT_MATCHES = 1 * 60 * 1000;
    public static final int CON_TIMEOUT_WINNER = 1 * 60 * 1000;
}
