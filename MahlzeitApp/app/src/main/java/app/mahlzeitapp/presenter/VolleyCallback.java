package app.mahlzeitapp.presenter;

import java.util.ArrayList;

import app.mahlzeitapp.model.Cat;
import app.mahlzeitapp.model.Group;
import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.model.Restaurant;

public interface VolleyCallback{
    void onSuccess(Person string);
    void onGetALL(ArrayList<Person> personen);
    //void onGetGroups(ArrayList<Group> groups);
    void onGetRestaurants(ArrayList<Restaurant> restaurants);
    void onGetCat(ArrayList<Cat> categories);
}
