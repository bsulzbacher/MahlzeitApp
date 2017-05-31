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
import java.util.ArrayList;

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

public class GroupDetailActivity extends BaseActivity {

    MahlzeitServiceAPI service = new MahlzeitServiceAPI(GroupDetailActivity.this);
    Person user;

    public GroupDetailActivity() throws MalformedURLException {
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        //button join/leave group
        final Button buttonAddGroup = (Button) findViewById(R.id.btn_add_group);
        buttonAddGroup.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                /*try {
                    service.joinGroup(user, group, GroupDetailActivity.this, );
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }*/
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
