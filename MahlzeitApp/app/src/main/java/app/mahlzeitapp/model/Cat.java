package app.mahlzeitapp.model;

/**
 * Created by Eli on 25.05.17.
 */

public class Cat {

    private String id;
    private String name;


    public Cat(String id, String name) {
        this.id = id;
        this.name = name;
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
}
