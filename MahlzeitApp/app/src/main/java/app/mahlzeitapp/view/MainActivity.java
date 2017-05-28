package app.mahlzeitapp.view;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
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
import app.mahlzeitapp.presenter.Database.MahlzeitDataSource;
import app.mahlzeitapp.presenter.Database.MahlzeitDbHelper;
import app.mahlzeitapp.presenter.MahlzeitServiceAPI;
import app.mahlzeitapp.presenter.VolleyCallback;


public class MainActivity extends AppCompatActivity  {

    public static Person user = null;

    MahlzeitServiceAPI service = new MahlzeitServiceAPI(MainActivity.this);

    public MainActivity() throws MalformedURLException {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button = (Button) findViewById(R.id.btn_login);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v) {
                EditText personenkennziffer = (EditText) findViewById(R.id.edit_username);
                final String txt = personenkennziffer.getText().toString();
               try {
                   service.getUserData(txt, MainActivity.this, new VolleyCallback() {
                        @Override
                        public void onSuccess(Person p) {
                            if(p != null) {
                                TextView errormsg = (TextView) findViewById(R.id.label_error);
                                errormsg.setText("");
                                user = p;
                                goToHomeView(p);
                            } else {
                                TextView errormsg = (TextView) findViewById(R.id.label_error);
                                errormsg.setText("Falsche Personenkennziffer");
                            }
                        }

                       @Override
                       public void onGetALL(ArrayList<Person> personen) {

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
        });
    }

    public void goToHomeView(Person user) {
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }


}
