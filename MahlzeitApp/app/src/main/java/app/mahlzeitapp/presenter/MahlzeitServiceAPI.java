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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Map;

import app.mahlzeitapp.model.Cat;
import app.mahlzeitapp.model.Group;
import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.presenter.Database.MahlzeitDataSource;
import app.mahlzeitapp.view.MainActivity;
import app.mahlzeitapp.view.MenuActivity;

/**
 * Created by Bianca on 21.04.2017.
 */

public class MahlzeitServiceAPI {

    private MahlzeitDataSource dataSource;

    public MahlzeitServiceAPI(Context context) throws MalformedURLException {
        dataSource = new MahlzeitDataSource(context);
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
                            dataSource.open();
                            dataSource.insertUser(p, 1);
                            dataSource.cleanFavoriteTable();
                            dataSource.close();
                            callback.onSuccess(p);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dataSource.openRead();
                        Person p = dataSource.getUserData();
                        dataSource.close();
                        callback.onSuccess(p);
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
                                Person p =  new Person(o.get("id").toString(), o.get("prename").toString(), o.get("surname").toString());
                                if(o.get("isFriend").toString().toLowerCase() == "true") {
                                    user.setFavorit(p);
                                    dataSource.open();
                                    dataSource.insertFavorite(user,p);
                                    dataSource.close();
                                }
                                personen.add(p);
                                dataSource.open();
                                dataSource.insertUser(p, 0);
                                dataSource.close();
                            }
                            callback.onGetALL(personen);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dataSource.openRead();
                ArrayList<Person> personenFavorites = dataSource.getFavorites();
                ArrayList<Person> personen = dataSource.getAllPersonen();
                dataSource.close();
                user.clearFavorites();
                for(int i = 0; i < personenFavorites.size(); i++)
                {
                    user.setFavorit(personenFavorites.get(i));
                }
                callback.onGetALL(personen);
            }
        });

        queue.add(stringRequest);
    }

    public void removeFavoritePerson(final Person user, final Person p, Context co) throws IOException, JSONException {
        String url = "http://10.0.2.2:8080/users/addFriends/" + user.getPersonenkennziffer();

        ArrayList<Person> favorite = user.getFavoritePersons();
        RequestQueue queue = Volley.newRequestQueue(co);

        JSONArray myarray = new JSONArray();
        for(int i = 0; i < favorite.size(); i++) {
            myarray.put(Integer.parseInt(favorite.get(i).getPersonenkennziffer()));
        }
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, myarray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String t= response.toString();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dataSource.open();
                dataSource.removeFavorite(user, p);
                dataSource.close();
            }
        });

        queue.add(request);
    }

    public void addFavoritePerson(final Person user, final Person p, Context co) throws IOException, JSONException {
        String url = "http://10.0.2.2:8080/users/addFriends/" + user.getPersonenkennziffer();

        RequestQueue queue = Volley.newRequestQueue(co);

        ArrayList<Person> favorite = user.getFavoritePersons();
        JSONArray myarray = new JSONArray();
        for(int i = 0; i < favorite.size(); i++) {
            myarray.put(Integer.parseInt(favorite.get(i).getPersonenkennziffer()));
        }

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.POST, url, myarray, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String t= response.toString();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if(p != null) {
                    dataSource.open();
                    dataSource.insertFavorite(user, p);
                    dataSource.close();
                }
            }
        });

        queue.add(request);
    }

    //---------------------------------------------------------------------------
    public void getAllGroups(final Person user, Context co, final VolleyCallback callback) throws IOException, JSONException {
        String url = "http://10.0.2.2:8080/users/getGroups/" + user.getPersonenkennziffer();

        RequestQueue queue = Volley.newRequestQueue(co);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String t= response.toString();
                        try {
                            JSONArray obj = new JSONArray(t);
                            ArrayList<Group> groups  = new ArrayList<Group>();

                            for(int i = 0; i < obj.length(); i++)
                            {
                                JSONObject o = obj.getJSONObject(i);
                                Group g =  new Group(o.get("id").toString(), o.get("prename").toString(), o.get("surname").toString());
                                groups.add(g);
                                dataSource.open();
                                dataSource.insertGroup(g, 0);
                                dataSource.close();
                            }
                            callback.onGetGroups(groups);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dataSource.openRead();
                ArrayList<Group> groups = dataSource.getAllGroups();
                dataSource.close();
                callback.onGetGroups(groups);
            }
        });

        queue.add(stringRequest);
    }



    public void getAllCat(Context co, final VolleyCallback callback) throws IOException, JSONException {
        String url = "http://10.0.2.2:8080/users/getallcat/";

        RequestQueue queue = Volley.newRequestQueue(co);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        String t= response.toString();
                        try {
                            JSONArray obj = new JSONArray(t);
                            ArrayList<Cat> categories  = new ArrayList<Cat>();

                            for(int i = 0; i < obj.length(); i++)
                            {
                                JSONObject o = obj.getJSONObject(i);
                                Cat c =  new Cat(o.get("id").toString(), o.get("category").toString());
                                categories.add(c);
                                dataSource.open();
                                dataSource.insertCategory(c, 0);
                                dataSource.close();
                            }
                            callback.onGetGroups(categories);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                dataSource.openRead();
                ArrayList<Cat> categories = dataSource.getAllCat();
                dataSource.close();
                callback.onGetCat(categories);
            }
        });

        queue.add(stringRequest);
    }

    //---------------------------------------------------------------------------

}

