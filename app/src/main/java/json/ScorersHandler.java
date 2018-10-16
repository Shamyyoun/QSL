package json;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import datamodels.Scorer;

public class ScorersHandler {
    private JSONArray jsonArray;

    public ScorersHandler(JSONArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public List<Scorer> handle() {
        List<Scorer> scorers = new ArrayList<>();
        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);

                // get data
                int id = jsonObject.getInt("Scorer_Id");
                String name = jsonObject.getString("Scorer_Name");
                String teamName = jsonObject.getString("Team_Name");
                int goals = jsonObject.getInt("Goals");

                // create object
                Scorer scorer = new Scorer(id);
                scorer.setName(name);
                scorer.setTeamName(teamName);
                scorer.setGoals(goals);

                // add to list
                scorers.add(scorer);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            scorers = null;
        }

        return scorers;
    }
}
