package json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import datamodels.Match;
import datamodels.MatchesGroup;
import datamodels.Team;
import utils.DateUtil;

public class MatchesGroupsHandler {
    private JSONArray jsonArray;

    public MatchesGroupsHandler(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public List<MatchesGroup> handle() {
        List<MatchesGroup> matchesGroups = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // get match group data
                String date = jsonObject.getString("date");
                JSONArray matchesJA = jsonObject.getJSONArray("matches");

                // loop to get matches
                List<Match> matches = new ArrayList<>(matchesJA.length());
                for (int j = 0; j < matchesJA.length(); j++) {
                    JSONObject matchJO = matchesJA.getJSONObject(j);
                    
                    // get first team
                    int teamId1 = matchJO.getInt("First_Team_Id");
                    String teamTitle1 = matchJO.getString("First_Team_Name");
                    String teamLogo1 = matchJO.getString("First_Team_Logo");
                    String teamColor1 = matchJO.getString("First_Team_Color");
                    Team team1 = new Team(teamId1);
                    team1.setTitle(teamTitle1);
                    team1.setLogo(teamLogo1);
                    team1.setColor(teamColor1);

                    // get second team
                    int teamId2 = matchJO.getInt("Second_Team_Id");
                    String teamTitle2 = matchJO.getString("Second_Team_Name");
                    String teamLogo2 = matchJO.getString("Second_Team_Logo");
                    String teamColor2 = matchJO.getString("Second_Team_Color");
                    Team team2 = new Team(teamId2);
                    team2.setTitle(teamTitle2);
                    team2.setLogo(teamLogo2);
                    team2.setColor(teamColor2);

                    // get other match data
                    int id = matchJO.getInt("Match_Id");
                    int goals1 = matchJO.getInt("First_Team_Goals");
                    int goals2 = matchJO.getInt("Second_Team_Goals");
                    String status = matchJO.getString("Status");
                    String matchDateTimeStr = matchJO.getString("Match_Date") + " " + matchJO.getString("Match_Time");
                    Date matchDateTime = DateUtil.convertToDate(matchDateTimeStr, "yyyy-MM-dd hh:mm:ss");

                    // create match object
                    Match match = new Match(id);
                    match.setTeam1(team1);
                    match.setTeam2(team2);
                    match.setGoals1(goals1);
                    match.setGoals2(goals2);
                    match.setStatus(status);
                    match.setDateTime(matchDateTime);

                    // add match object to matches list
                    matches.add(match);
                }

                // create matches group object
                MatchesGroup matchesGroup = new MatchesGroup(date, matches);

                // add matches group to list
                matchesGroups.add(matchesGroup);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            matchesGroups = null;
        }

        return matchesGroups;
    }
}
