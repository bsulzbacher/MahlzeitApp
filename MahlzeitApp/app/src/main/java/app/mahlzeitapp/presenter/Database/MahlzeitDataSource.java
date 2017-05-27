package app.mahlzeitapp.presenter.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import app.mahlzeitapp.model.Cat;
import app.mahlzeitapp.model.Group;
import app.mahlzeitapp.model.Person;
import app.mahlzeitapp.model.Restaurant;

/**
 * Created by Bianca on 12.05.2017.
 */

public class MahlzeitDataSource {

    private SQLiteDatabase database;
    private MahlzeitDbHelper dbHelper;


    public MahlzeitDataSource(Context context) {
        dbHelper = new MahlzeitDbHelper(context);
    }


    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void openRead() {
        database = dbHelper.getReadableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertUser(Person p, int isUser)
    {
        database.delete("user", "_id = ?", new String[] {p.getPersonenkennziffer()});
        ContentValues values = new ContentValues();
        values.put("_id", p.getPersonenkennziffer());
        values.put("isUser", isUser);
        values.put("firstname", p.getVorname());
        values.put("lastname", p.getNachname());
        database.insertOrThrow("user", null, values);
    }

    public void cleanFavoriteTable()
    {
        database.delete("favorites", null, null);
    }

    public void insertFavorite(Person user, Person p)
    {
        database.delete("favorites", "idFavorite = ?", new String[] {p.getPersonenkennziffer()});
        ContentValues values = new ContentValues();
        values.put("_id", user.getPersonenkennziffer());
        values.put("idFavorite", p.getPersonenkennziffer());
        database.insertOrThrow("favorites", null, values);
    }

    public Person getUserData()
    {
        Cursor resultSet = database.rawQuery("Select _id, firstname, lastname from user WHERE isUser=1",null);

        resultSet.moveToFirst();
        int id = resultSet.getInt(0);
        String firstname = resultSet.getString(1);
        String lastname = resultSet.getString(2);
        return new Person(Integer.toString(id), firstname,lastname );
    }

    public ArrayList<Person> getFavorites()
    {
        Cursor resultSet = database.rawQuery("Select i.idFavorite, u.firstname, u.lastname from favorites i, user u WHERE u._id = i.idFavorite AND u.isUser=0", null);
        ArrayList<Person> favorites = new ArrayList<Person>();
        resultSet.moveToFirst();
        resultSet.getCount();
        while (!resultSet.isAfterLast()) {
            int id = resultSet.getInt(0);
            String firstname = resultSet.getString(1);
            String lastname = resultSet.getString(2);
            favorites.add(new Person(Integer.toString(id), firstname,lastname ));
            resultSet.moveToNext();
        }
        return favorites;
    }

    public ArrayList<Person> getAllPersonen()
    {
        Cursor resultSet = database.rawQuery("Select _id, firstname, lastname from user u WHERE isUser=0", null);
        ArrayList<Person> personen = new ArrayList<Person>();
        resultSet.moveToFirst();
        while (!resultSet.isAfterLast()) {
            int id = resultSet.getInt(0);
            String firstname = resultSet.getString(1);
            String lastname = resultSet.getString(2);
            personen.add(new Person(Integer.toString(id), firstname,lastname ));
            resultSet.moveToNext();
        }
        return personen;
    }

    public void removeFavorite(Person user, Person favorite)
    {
        database.delete("favorites", "idFavorite = ?", new String[] {favorite.getPersonenkennziffer()});
    }


    //Category

    public ArrayList<Cat> getAllCat() {
        Cursor resultSet = database.rawQuery("Select _id, category from cat", null);
        ArrayList<Cat> categories = new ArrayList<Cat>();
        resultSet.moveToFirst();
        while (!resultSet.isAfterLast()) {
            int id = resultSet.getInt(0);
            String name = resultSet.getString(1);
            categories.add(new Cat(Integer.toString(id), name));
            resultSet.moveToNext();
        }
        return categories;
    }

    public void insertCat(Cat c)
    {
        database.delete("user", "_id = ?", new String[] {c.getId()}); //new String "cat id"?
        ContentValues values = new ContentValues();
        values.put("_id", c.getId());
        values.put("name", c.getName());
        database.insertOrThrow("categories", null, values);
    }

    //Restaurant

    public ArrayList<Restaurant> getAllRestaurants() {
        //? wie verweist restaurant auf cat? hat cat restaurant_id?
        Cursor resultSet = database.rawQuery("Select r._id, r.name, r.ort, c.id, c.category from restaurant r, cat c WHERE r._id = c.restaurant_id", null); //???
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        resultSet.moveToFirst();
        while (!resultSet.isAfterLast()) {
            int id = resultSet.getInt(0);
            String name = resultSet.getString(1);
            String place = resultSet.getString(2);
            String catId = resultSet.getString(3);
            String catName = resultSet.getString(4);
            restaurants.add(new Restaurant(Integer.toString(id), name, place, new Cat(catId, catName)));
            resultSet.moveToNext();
        }
        return restaurants;
    }

    public void insertRestaurant(Restaurant r)
    {
        database.delete("categories", "_id = ?", new String[] {r.getId()}); //new String "restaurant id"?
        ContentValues values = new ContentValues();
        values.put("_id", r.getId());
        values.put("name", r.getName());
        values.put("place", r.getPlace());
        values.put("catId", r.getCategory().getId());
        values.put("catName", r.getCategory().getName());
        database.insertOrThrow("user", null, values);
    }

    //Groups

    public ArrayList<Group> getAllGroups(Person user) {
        Cursor resultSet = database.rawQuery("Select g._id, g.name, r.id, r.name, r.ort, c.id, " +
                "c.category from group g, restaurant r, cat c WHERE ", null); //??? favorites?
        ArrayList<Group> groups = new ArrayList<Group>();
        resultSet.moveToFirst();
        while (!resultSet.isAfterLast()) {
            /*int id = resultSet.getInt(0);
            //restaurant?
            int resId = 0;
            String resName = "test";
            String resPlace = "test";
            //cat?
            int catId = 0;
            String catName = "test";
            groups.add(new Group(Integer.toString(id), new Restaurant(Integer.toString(resId), resName, resPlace,
                    new Cat(Integer.toString(catId), catName)))); //members?
            resultSet.moveToNext();*/
        }
        return groups;
    }

    public void insertGroup(Group g)
    {
        database.delete("groups", "_id = ?", new String[] {g.getId()}); //new String "group id"?
        ContentValues values = new ContentValues();
        values.put("_id", g.getId());
        values.put("resId", g.getRestaurant().getId());
        values.put("resName", g.getRestaurant().getName());
        values.put("resPlace", g.getRestaurant().getPlace());
        values.put("catId", g.getRestaurant().getCategory().getId());
        values.put("catName", g.getRestaurant().getCategory().getName());
        database.insertOrThrow("user", null, values);
    }

}
