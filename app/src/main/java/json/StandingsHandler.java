package json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import datamodels.Standing;
import datamodels.Team;

public class StandingsHandler {
    private JSONArray jsonArray;

    public StandingsHandler(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public List<Standing> handle() {
        List<Standing> standings = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // get data
                int teamId = jsonObject.getInt("Team_Id");
                String teamName = jsonObject.getString("Team_Name");
                String teamLogo = jsonObject.getString("Team_Logo");
                String teamColor = jsonObject.getString("Team_Color");
                int position = jsonObject.getInt("Position");
                int played = jsonObject.getInt("Played");
                int won = jsonObject.getInt("Won");
                int drawn = jsonObject.getInt("Drawn");
                int lost = jsonObject.getInt("Lost");
                int goalsFor = jsonObject.getInt("Goals_For");
                int goalsAgainst = jsonObject.getInt("Goals_Against");
                int goalsDifference = jsonObject.getInt("Goal_Difference");
                int points = jsonObject.getInt("Points");

                // create team object
                Team team = new Team(teamId);
                team.setTitle(teamName);
                team.setLogo(teamLogo);
                team.setColor(teamColor);

                // create standing object
                Standing standing = new Standing();
                standing.setTeam(team);
                standing.setPosition(position);
                standing.setPlayed(played);
                standing.setWon(won);
                standing.setDrawn(drawn);
                standing.setLost(lost);
                standing.setGoalsFor(goalsFor);
                standing.setGoalsAgainst(goalsAgainst);
                standing.setGoalsDifference(goalsDifference);
                standing.setPoints(points);

                // add to list
                standings.add(standing);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            standings = null;
        }

        return standings;
    }
}
