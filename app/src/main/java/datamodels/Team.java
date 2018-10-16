package datamodels;

import java.io.Serializable;

/**
 * Created by Dahman on 9/20/2015.
 */
public class Team {
    private int id;
    private String title;
    private String logo;
    private String color; // stored as hash color

    public Team(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String imageResId) {
        this.logo = imageResId;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
