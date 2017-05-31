package app.mahlzeitapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import app.mahlzeitapp.R;
import app.mahlzeitapp.model.Cat;
import app.mahlzeitapp.model.Group;
import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.model.Restaurant;
import app.mahlzeitapp.presenter.FavoritePersonListAdapter;
import app.mahlzeitapp.presenter.GroupsListAdapter;
import app.mahlzeitapp.presenter.MahlzeitServiceAPI;
import app.mahlzeitapp.presenter.VolleyCallback;

/**
 * Created by Eli on 27.05.17.
 */

public class GroupDetailActivity extends BaseActivity {

    MahlzeitServiceAPI service = new MahlzeitServiceAPI(GroupDetailActivity.this);
    Person user;
    public static Group selectedGroup = null;

    public GroupDetailActivity() throws MalformedURLException {
        user = MainActivity.user;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        TextView groupName = (TextView) findViewById(R.id.group_name); groupName.setText(selectedGroup.getRestaurant().getName());
        TextView groupCat = (TextView) findViewById(R.id.group_cat); groupCat.setText(selectedGroup.getRestaurant().getCategory().getName());
        TextView groupPlace = (TextView) findViewById(R.id.group_place); groupPlace.setText(selectedGroup.getRestaurant().getPlace());

        try {
            service.getAllGroups(user, GroupDetailActivity.this, new VolleyCallback() {

                @Override
                public void onSuccess(Person string) {

                }

                @Override
                public void onGetALL(ArrayList<Person> personen) {

                    ArrayList<Person> p = personen;

                    ArrayList<String> persNames = new ArrayList<String>();
                    for(int i = 0; i < personen.size(); i++)
                    {
                        Person person = personen.get(i);
                        String persName = person.getVorname();
                        persNames.add(persName);
                    }


                    /*final GroupsListAdapter groupsListAdapter =
                            new GroupsListAdapter(MenuActivity.this,
                                    R.layout.list_item_group, groups,
                                    MainActivity.user, service
                            );*/

                    /*ArrayList<String> resNames = new ArrayList<String>();
                    for(int i = 0; i < restaurants.size(); i++)
                    {
                        Restaurant restaurant = restaurants.get(i);
                        restaurantList.add(restaurant); //to access later to get id
                        String resName = restaurant.getName();
                        resNames.add(resName);
                    }

                    String[] resItems = resNames.toArray(new String[0]);
                    ArrayAdapter<String> resAdapter = new ArrayAdapter<String>(NewGroupActivity.this, android.R.layout.simple_spinner_dropdown_item, resItems);*/


                    ArrayAdapter<String> personsListAdapter = new ArrayAdapter<String>(GroupDetailActivity.this, android.R.layout.simple_list_item_1, persNames);

                    final ListView personsListView = (ListView) findViewById(R.id.list_persons);
                    personsListView.setAdapter(personsListAdapter);
                }

                @Override
                public void onGetGroups(ArrayList<Group> g) {
                    ArrayList<Group> groups = g;

                }

                @Override
                public void onGetRestaurants(ArrayList<Restaurant> restaurants) {

                }

                @Override
                public void onGetCat(ArrayList<Cat> categories) {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void goToHomeView(Person user) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public void goToNewRestaurantView(Person user) {
        Intent intent = new Intent(this, NewRestaurantActivity.class);
        startActivity(intent);
    }

}
