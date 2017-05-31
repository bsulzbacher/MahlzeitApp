package app.mahlzeitapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

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

public class NewGroupActivity extends BaseActivity {

    MahlzeitServiceAPI service = new MahlzeitServiceAPI(NewGroupActivity.this);
    Person user;

    public NewGroupActivity() throws MalformedURLException {
        user = MainActivity.user;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        final Spinner resDropdown = (Spinner) findViewById(R.id.restaurant_spinner);
        final ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();

        try {
            service.getAllRestaurants(NewGroupActivity.this,  new VolleyCallback() {

                @Override
                public void onSuccess(Person string) {

                }

                @Override
                public void onGetALL(ArrayList<Person> personen) {

                }

                @Override
                public void onGetGroups(ArrayList<Group> g) {

                }

                @Override
                public void onGetRestaurants(ArrayList<Restaurant> restaurants) {
                    ArrayList<String> resNames = new ArrayList<String>();
                    for(int i = 0; i < restaurants.size(); i++)
                    {
                        Restaurant restaurant = restaurants.get(i);
                        restaurantList.add(restaurant); //to access later to get id
                        String resName = restaurant.getName();
                        resNames.add(resName);
                    }

                    String[] resItems = resNames.toArray(new String[0]);
                    ArrayAdapter<String> resAdapter = new ArrayAdapter<String>(NewGroupActivity.this, android.R.layout.simple_spinner_dropdown_item, resItems);
                    resDropdown.setAdapter(resAdapter);
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


        //zb senden: {"restaurant":{"id":3}}
        final Button buttonAddGroup = (Button) findViewById(R.id.btn_add_group);
        buttonAddGroup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            final String selectedResName = resDropdown.getItemAtPosition(resDropdown.getSelectedItemPosition()).toString();

            //get selected Restaurant from dropdown
            Restaurant newRestaurant = null;
            for (Restaurant r : restaurantList) {
                if (selectedResName.equals(r.getName())) {
                    newRestaurant = r;
                    break;
                }
            }

            Group newGroup = new Group(String.valueOf(new Random().nextInt()), newRestaurant, new ArrayList<Person>());
            newGroup.setRestaurant(newRestaurant);

            try {
                service.addGroup(user, newGroup, NewGroupActivity.this);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            }
        });

        final Button buttonNewRestaurant = (Button) findViewById(R.id.btn_new_restaurant);
        buttonNewRestaurant.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                goToNewRestaurantView(user);
            }
        });

        //button "new restaurant"
        final Button button = (Button) findViewById(R.id.btn_new_restaurant);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                goToNewRestaurantView(user);
            }
        });
    }


    public void goToNewRestaurantView(Person user) {
        Intent intent = new Intent(this, NewRestaurantActivity.class);
        startActivity(intent);
    }

}
