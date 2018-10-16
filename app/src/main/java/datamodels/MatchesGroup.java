package datamodels;

import java.util.Date;
import java.util.List;

import utils.DateUtil;

/**
 * Created by Shamyyoun on 11/30/2015.
 */
public class MatchesGroup {
    private static final String DATE_FORMAT = "yyyy-MM-dd";
    private Date date;
    private List<Match> matches;

    public MatchesGroup(Date date, List<Match> matches) {
        this.date = date;
        this.matches = matches;
    }

    public MatchesGroup(String date, List<Match> matches) {
        this.date = DateUtil.convertToDate(date, DATE_FORMAT);
        this.matches = matches;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.date = DateUtil.convertToDate(date, DATE_FORMAT);
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }
}
