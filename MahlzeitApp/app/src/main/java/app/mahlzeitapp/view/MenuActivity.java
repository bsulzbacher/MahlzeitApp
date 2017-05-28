package app.mahlzeitapp.view;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

public class MenuActivity extends BaseActivity {

    MahlzeitServiceAPI service = new MahlzeitServiceAPI(MenuActivity.this);
    Person user;

    public MenuActivity() throws MalformedURLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);

        //todays date
        String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        TextView myTextView = (TextView) findViewById(R.id.todays_date); myTextView.setText("Tagesübersicht " + date);

        //todays groups ?
        try {
            service.getAllGroups(user, MenuActivity.this,  new VolleyCallback() {

                @Override
                public void onGetGroups(ArrayList<Group> g) {
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //button "new group" ?
        final Button button = (Button) findViewById(R.id.btn_new_group);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {

                try {
                    service.addGroup(user, MenuActivity.this, new VolleyCallback() {
                        @Override
                        public void onSuccess(Person p) {
                            user = p;
                            goToNewGroupView(p);
                        }

                        @Override
                        public void onGetALL(ArrayList<Person> personen) {

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void goToNewGroupView(Person user) {
        Intent intent = new Intent(this, NewGroupActivity.class);
        startActivity(intent);
    }

}
