package app.mahlzeitapp.presenter.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import app.mahlzeitapp.model.Cat;
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

    //---------------------------------------------------------------------------

    //Category

    public ArrayList<Cat> getAllCat() {
        Cursor resultSet = database.rawQuery("Select _id, name from cat", null);
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
        database.delete("user", "_id = ?", new String[] {c.getId()});
        ContentValues values = new ContentValues();
        values.put("_id", c.getId());
        values.put("name", c.getName());
        database.insertOrThrow("categories", null, values);
    }

    //Restaurant

    public ArrayList<Cat> getAllRestaurants() {
        Cursor resultSet = database.rawQuery("Select _id, name, place from restaurant", null);
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

    public void insertRestaurant(Restaurant r)
    {
        database.delete("categories", "_id = ?", new String[]);
        ContentValues values = new ContentValues();
        values.put("_id", r.getId());
        values.put("name", r.getName());
        values.put("place", r.getPlace());
        values.put("", r.getCategory().getId());
        values.put("", r.getCategory().getName());
        database.insertOrThrow("user", null, values);
    }


    //---------------------------------------------------------------------------

}
