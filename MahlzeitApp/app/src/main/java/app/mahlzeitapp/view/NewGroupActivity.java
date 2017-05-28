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

import app.mahlzeitapp.R;
import app.mahlzeitapp.model.Group;
import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.presenter.FavoritePersonListAdapter;
import app.mahlzeitapp.presenter.MahlzeitServiceAPI;
import app.mahlzeitapp.presenter.VolleyCallback;

/**
 * Created by Eli on 27.05.17.
 */

public class NewGroupActivity extends BaseActivity {

    MahlzeitServiceAPI service = new MahlzeitServiceAPI(NewGroupActivity.this);
    Person user;

    public NewGroupActivity() throws MalformedURLException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_group);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        //restaurants dropdown
        Spinner restaurantDropdown = (Spinner)findViewById(R.id.restaurant_spinner);
        String[] restaurantItems = new String[]{"1", "2", "three"};
        ArrayAdapter<String> restaurantAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, restaurantItems);
        restaurantDropdown.setAdapter(restaurantAdapter);

        //categories dropdown
        Spinner catDropdown = (Spinner)findViewById(R.id.cat_spinner);
        String[] catItems = new String[]{"1", "2", "three"};
        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, catItems);
        catDropdown.setAdapter(catAdapter);


        //button new group (today)
        //POST: restaurants (dropdown), kategories (dropdown), userId - then go to home view
        final Button buttonAddGroup = (Button) findViewById(R.id.btn_add_group);
        buttonAddGroup.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
               //to do: send restaurantId (JSON), userId (in url) like this: {"restaurant":{"id":3}} -> goToHomeView
                try {
                    service.addGroup(user.getPersonenkennziffer(), NewGroupActivity.this, new VolleyCallback() {
                        //to do

                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });


        //button new restaurant
        //POST: userid?
        final Button buttonNewRestaurant = (Button) findViewById(R.id.btn_new_restaurant);
        buttonNewRestaurant.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
               goToNewRestaurantView(user);
            }
        });

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
