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

    public String getVorname() { return this.vorname; }
    public String getNachname() { return this.nachname; }

    public void setFavorit(Person favorite)
    {
        if(!checkFavorite(favorite))
            this.favoritePersons.add(favorite);
    }

    public void clearFavorites() { this.favoritePersons.clear(); }

    public void removeFavorite(Person favorite)
    {
        for(int i = 0; i < favoritePersons.size(); i++)
        {
            if(favoritePersons.get(i).getPersonenkennziffer().equals(favorite.getPersonenkennziffer()))
                this.favoritePersons.remove(i);
        }
    }

    public boolean checkFavorite(Person p)
    {
        for(int i = 0; i < favoritePersons.size(); i++)
        {
            if(favoritePersons.get(i).getPersonenkennziffer().equals(p.getPersonenkennziffer()))
                return true;
        }
        return false;
    }

    public String getPersonenkennziffer()
    {
        return this.personenkennziffer;
    }

    public ArrayList<Person> getFavoritePersons() { return this.favoritePersons; }
    @Override
    public String toString()
    {
        return  this.vorname + " " + this.nachname;
    }
}
