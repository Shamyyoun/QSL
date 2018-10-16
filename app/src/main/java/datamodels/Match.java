package datamodels;

import java.util.Date;

/**
 * Created by Dahman on 9/21/2015.
 */
public class Match {
    public static final String STATUS_NOT_STARTED = "match_not_started";
    public static final String STATUS_SOON = "match_soon";
    public static final String STATUS_FULL_TIME = "match_full_time";

    private int id;
    private Team team1;
    private Team team2;
    private int goals1;
    private int goals2;
    private String status;
    private Date dateTime;

    public Match(int id) {
        this.id = id;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam2() {
        return team2;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public int getGoals1() {
        return goals1;
    }

    public void setGoals1(int goals1) {
        this.goals1 = goals1;
    }

    public int getGoals2() {
        return goals2;
    }

    public void setGoals2(int goals2) {
        this.goals2 = goals2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }
}
