package datamodels;

/**
 * Created by Shamyyoun on 11/24/2015.
 */
public class Assist {
    private int id;
    private String name;
    private String teamName;
    private int assistsCount;

    public Assist(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getAssistsCount() {
        return assistsCount;
    }

    public void setAssistsCount(int assistsCount) {
        this.assistsCount = assistsCount;
    }
}
