package json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import datamodels.Assist;

public class AssistsHandler {
    private JSONArray jsonArray;

    public AssistsHandler(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public List<Assist> handle() {
        List<Assist> assists = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // get data
                int id = jsonObject.getInt("Player_Id");
                String name = jsonObject.getString("Player_Name");
                String teamName = jsonObject.getString("Current_Team");
                int assistsCount = jsonObject.getInt("Assits");

                // create object
                Assist assist = new Assist(id);
                assist.setName(name);
                assist.setTeamName(teamName);
                assist.setAssistsCount(assistsCount);

                // add to list
                assists.add(assist);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            assists = null;
        }

        return assists;
    }
}
