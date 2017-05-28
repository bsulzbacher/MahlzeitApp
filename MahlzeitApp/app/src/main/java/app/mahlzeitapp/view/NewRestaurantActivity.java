package app.mahlzeitapp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;

import app.mahlzeitapp.R;
import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.presenter.VolleyCallback;

/**
 * Created by Eli on 27.05.17.
 */

public class NewRestaurantActivity extends BaseActivity {

    //MahlzeitServiceAPI service = new MahlzeitServiceAPI();
    Person user;

    public NewRestaurantActivity() throws MalformedURLException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_restaurant);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        //restaurant name text input


        //restaurant place text input


        //categories dropdown
        Spinner catDropdown = (Spinner)findViewById(R.id.cat_spinner);
        String[] catItems = new String[]{"1", "2", "three"};
        ArrayAdapter<String> catAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, catItems);
        catDropdown.setAdapter(catAdapter);


        //button add restaurant - user automatically creates and joins group
        //POST: restaurants (dropdown), kategories (dropdown), userId - then go to home view
        final Button button = (Button) findViewById(R.id.btn_add_group);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                //to do (send userId)
                try {
                    service.addRestaurant(user.getPersonenkennziffer(), NewRestaurantActivity.this, new VolleyCallback() {
                        //to do

                    });
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

}
