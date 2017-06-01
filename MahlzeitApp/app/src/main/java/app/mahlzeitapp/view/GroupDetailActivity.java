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
    //public static ArrayList<Person> members = selectedGroup.getMembers();

    public GroupDetailActivity() throws MalformedURLException {
        user = MainActivity.user;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_detail);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        final ArrayList<String> favoriteMembers = new ArrayList<String>();
        final ArrayList<String> regularMembers = new ArrayList<String>();

        TextView groupName = (TextView) findViewById(R.id.group_name); groupName.setText(selectedGroup.getRestaurant().getName());
        TextView groupCat = (TextView) findViewById(R.id.group_cat); groupCat.setText(selectedGroup.getRestaurant().getCategory().getName());
        TextView groupPlace = (TextView) findViewById(R.id.group_place); groupPlace.setText(selectedGroup.getRestaurant().getPlace());

        Button btnJoin = (Button) findViewById(R.id.btn_join_group);
        if (selectedGroup.checkIfUserInGroup(user)) {
            btnJoin.setText("austreten");
        } else {
            btnJoin.setText("beitreten");
        }

        /*for (Person p : members) {
            if (p.checkFavorite(user)) {
                favoriteMembers.add(p.getVorname().toString());
            } else {
                regularMembers.add(p.getVorname().toString());
            }
        }


        final ListView listviewFavo = (ListView) findViewById(R.id.list_favorites);
        final ListView listviewMember = (ListView) findViewById(R.id.list_members);

        final ArrayAdapter favoAdapter = new ArrayAdapter(GroupDetailActivity.this,
                android.R.layout.simple_list_item_1, favoriteMembers);
        listviewFavo.setAdapter(favoAdapter);

        final ArrayAdapter memberAdapter = new ArrayAdapter(GroupDetailActivity.this,
                android.R.layout.simple_list_item_1, members);
        listviewMember.setAdapter(memberAdapter);*/

        try {
            service.getAllGroups(user, GroupDetailActivity.this, new VolleyCallback() {

                @Override
                public void onSuccess(Person string) {

                }

                @Override
                public void onGetALL(ArrayList<Person> personen) {


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

}
