package app.mahlzeitapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import app.mahlzeitapp.R;
import app.mahlzeitapp.model.Cat;
import app.mahlzeitapp.model.Group;
import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.model.Restaurant;
import app.mahlzeitapp.presenter.MahlzeitServiceAPI;
import app.mahlzeitapp.presenter.VolleyCallback;

/**
 * Created by Eli on 27.05.17.
 */

public class NewRestaurantActivity extends BaseActivity {

    MahlzeitServiceAPI service = new MahlzeitServiceAPI(NewRestaurantActivity.this);
    Person user;

    public NewRestaurantActivity() throws MalformedURLException {
        user = MainActivity.user;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_restaurant);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        final Spinner catDropdown = (Spinner) findViewById(R.id.cat_spinner);
        final ArrayList<Cat> categoryList = new ArrayList<Cat>();
        final ArrayList<Restaurant> restaurantList = new ArrayList<Restaurant>();

        try {
            service.getAllCat(NewRestaurantActivity.this, new VolleyCallback() {

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
                    for(int i = 0; i < restaurants.size(); i++)
                    {
                        Restaurant restaurant = restaurants.get(i);
                        restaurantList.add(restaurant);
                    }
                }


                @Override
                public void onGetCat(ArrayList<Cat> categories) {

                    ArrayList<String> catNames = new ArrayList<String>();
                    for(int i = 0; i < categories.size(); i++)
                    {
                        Cat category = categories.get(i);
                        categoryList.add(category); //access later to get id of cat
                        String catName = category.getName();
                        catNames.add(catName);
                    }

                    String[] catItems = catNames.toArray(new String[0]);
                    ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(NewRestaurantActivity.this, android.R.layout.simple_spinner_dropdown_item, catItems);
                    catDropdown.setAdapter(catAdapter);

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        final Button button = (Button) findViewById(R.id.btn_add_restaurant);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                EditText resName = (EditText) findViewById(R.id.edit_name);
                EditText resPlace = (EditText) findViewById(R.id.edit_place);
                final String txtResName = resName.getText().toString();
                final String txtResPlace = resPlace.getText().toString();
                final String selectedCat = catDropdown.getItemAtPosition(catDropdown.getSelectedItemPosition()).toString();

                int catId = getCatIdFromCatName(selectedCat);
                String resId = Integer.toString(restaurantList.size()+1);

                Cat newCat = new Cat(Integer.toString(catId), selectedCat);
                Restaurant newRestaurant = new Restaurant(resId, txtResName, txtResPlace, newCat);

                try {
                    service.addRestaurant(user, newRestaurant, NewRestaurantActivity.this);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void goToHomeView(Person user) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }

    public int getCatIdFromCatName(String catName) {
        int catId;
        if (catName == "Fastfood") {
            catId = 1;}
        else if (catName == "Restaurant") {
            catId = 2;}
        else if (catName == "Imbissstand") {
            catId = 3;}
        else {
            catId = 4;}
        return catId;
    }

}
