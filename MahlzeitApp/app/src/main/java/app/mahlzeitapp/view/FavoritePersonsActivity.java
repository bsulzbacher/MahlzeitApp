package app.mahlzeitapp.view;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;

import org.json.JSONException;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import app.mahlzeitapp.R;
import app.mahlzeitapp.model.Cat;
import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.model.Restaurant;
import app.mahlzeitapp.presenter.FavoritePersonListAdapter;
import app.mahlzeitapp.presenter.MahlzeitServiceAPI;
import app.mahlzeitapp.presenter.VolleyCallback;

public class FavoritePersonsActivity extends BaseActivity {

    MahlzeitServiceAPI service = new MahlzeitServiceAPI(FavoritePersonsActivity.this);
    Person user;

    public FavoritePersonsActivity() throws MalformedURLException {
        user = MainActivity.user;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_persons);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
            try {
                service.getAllPersonen(user, FavoritePersonsActivity.this,  new VolleyCallback() {
                    @Override
                    public void onSuccess(Person string) {

                    }

                    @Override
                    public void onGetALL(ArrayList<Person> p) {

                        final FavoritePersonListAdapter personsListAdapter =
                                new FavoritePersonListAdapter(FavoritePersonsActivity.this,
                                        R.layout.list_item_persons, p,
                                        MainActivity.user, service
                                );
                        final ListView personsListView = (ListView) findViewById(R.id.list_persons);
                        personsListView.setAdapter(personsListAdapter);
                    }

                    @Override
                    public void onGetGroups(ArrayList<Group> groups) {

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
}
