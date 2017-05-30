package app.mahlzeitapp.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Eli on 25.05.17.
 */

public class Group {
    private String id;
    private Restaurant restaurant;
    private ArrayList<Person> members;
    //private Date date;

    public Group(String id, Restaurant restaurant, ArrayList<Person> members) {
        this.id = id;
        this.restaurant = restaurant;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public ArrayList<Person> getMembers() {
        return members;
    }

    public void setMember(Person member) {
        this.members.add(member);
    }

    public void removeMember(Person member)
    {
        for(int i = 0; i < members.size(); i++)
        {
            if(members.get(i).getPersonenkennziffer().equals(member.getPersonenkennziffer()))
                this.members.remove(i);
        }
    }

    public boolean checkIfUserInGroup(Person user) {
        for(int i = 0; i < members.size(); i++) {
            if (members.get(i).getPersonenkennziffer().equals(user.getPersonenkennziffer()))
                return true;
        }
        return false;
    }

    public boolean checkIfFavoriteInGroup(ArrayList<Person> favorites) {
        for(int i = 0; i < members.size(); i++) {
            for(int j = 0; j < favorites.size(); j++) {
                if (members.get(i).getPersonenkennziffer().equals(favorites.get(j).getPersonenkennziffer()))
                    return true;
            }
        }
        return false;
    }

    public void clearMembers() { this.members.clear(); }
}
