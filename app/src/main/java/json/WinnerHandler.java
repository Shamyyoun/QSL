package json;

import org.json.JSONException;
import org.json.JSONObject;

import datamodels.Team;

public class WinnerHandler {
    private JSONObject jsonObject;

    public WinnerHandler(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Team handle() {
        Team team;
        try {
            JSONObject winnerJO = jsonObject.getJSONObject("Winner");
            String title = winnerJO.getString("Team_Name");
            String logo = winnerJO.getString("Team_Logo");
            String color = winnerJO.getString("Team_Color");

            team = new Team(-1);
            team.setTitle(title);
            team.setLogo(logo);
            team.setColor(color);
        } catch (JSONException e) {
            e.printStackTrace();
            team = null;
        }

        return team;
    }
}
