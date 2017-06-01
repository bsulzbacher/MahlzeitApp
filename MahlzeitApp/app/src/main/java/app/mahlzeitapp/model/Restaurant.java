package app.mahlzeitapp.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Eli on 25.05.17.
 */

public class Restaurant implements Serializable{

    private String id;
    private String name;
    private String place;
    private Cat category;

    public Restaurant(String id, String name, String place, Cat category) {
        this.id = id;
        this.name = name;
        this.place = place;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Cat getCategory() {
        return category;
    }

    public void setCategory(Cat category) {
        this.category = category;
    }


}
