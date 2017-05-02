package app.mahlzeitapp.model;

import java.util.ArrayList;

/**
 * Created by Bianca on 19.04.2017.
 */

public class Person {
    private String personenkennziffer;
    private String vorname;
    private String nachname;
    private ArrayList<Person> favoritePersons;

    public Person(String pnr, String vorname, String nachname)
    {
        this.personenkennziffer = pnr;
        this.vorname = vorname;
        this.nachname = nachname;
        favoritePersons = new ArrayList<Person>();
    }

    public void setFavorit(Person favorite)
    {
        this.favoritePersons.add(favorite);
    }

    public void removeFavorite(Person favorite)
    {
        this.favoritePersons.remove(favorite);
    }

    public boolean checkFavorite(Person p)
    {
        for(int i = 0; i < favoritePersons.size(); i++)
        {
            if(favoritePersons.get(i) == p)
                return true;
        }
        return false;
    }

    public String getPersonenkennziffer()
    {
        return this.personenkennziffer;
    }

    @Override
    public String toString()
    {
        return  this.vorname + " " + this.nachname;
    }
}