package app.mahlzeitapp.presenter;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.view.MainActivity;
import app.mahlzeitapp.view.MenuActivity;

/**
 * Created by Bianca on 21.04.2017.
 */

public class MahlzeitServiceAPI {

    public MahlzeitServiceAPI() throws MalformedURLException {
    }


    public void getUserData(final String pnm, MainActivity co, final VolleyCallback callback) throws IOException, JSONException {
        String url = "http://10.0.2.2:8080/login/" + pnm;

        RequestQueue queue = Volley.newRequestQueue(co.getApplicationContext());

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String t= response.toString();
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(t);
                            Person p =  new Person(pnm, obj.get("prename").toString(), obj.get("surname").toString());
                            callback.onSuccess(p);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        String t= "error";
                    }
        });

        queue.add(stringRequest);
    }

    public void getAllPersonen(final Person user, Context co, final VolleyCallback callback) throws IOException, JSONException {
        String url = "http://10.0.2.2:8080/users/getAll/" + user.getPersonenkennziffer();

        RequestQueue queue = Volley.newRequestQueue(co);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String t= response.toString();
                        try {
                            JSONArray obj = new JSONArray(t);
                            ArrayList<Person> personen  = new ArrayList<Person>();

                            for(int i = 0; i < obj.length(); i++)
                            {
                                JSONObject o = obj.getJSONObject(i);
                                Person p =  new Person(user.getPersonenkennziffer(), o.get("prename").toString(), o.get("surname").toString());
                                if(o.get("isFriend").toString().toLowerCase() == "true")
                                    user.setFavorit(p);
                                personen.add(p);
                            }
                            callback.onGetALL(personen);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String t= "error";
            }
        });

        queue.add(stringRequest);
    }

    public void removeFavoritePerson(Person user, Person favorite, Context co) throws IOException, JSONException {
        String url = "http://10.0.2.2:8080/users/addFriends/" + user.getPersonenkennziffer();

        /*RequestQueue queue = Volley.newRequestQueue(co);

        final JSONObject jsonBody = new JSONObject("{\"type\":\"example\"}");
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String t= response.toString();
                        try {
                            JSONArray obj = new JSONArray(t);
                            ArrayList<Person> personen  = new ArrayList<Person>();

                            for(int i = 0; i < obj.length(); i++)
                            {
                                JSONObject o = obj.getJSONObject(i);
                                Person p =  new Person(user.getPersonenkennziffer(), o.get("prename").toString(), o.get("surname").toString());
                                if(o.get("isFriend").toString().toLowerCase() == "true")
                                    user.setFavorit(p);
                                personen.add(p);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String t= "error";
            }
        });

        queue.add(stringRequest);*/

    }
}

