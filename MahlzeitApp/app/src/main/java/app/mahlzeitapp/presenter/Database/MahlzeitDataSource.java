package app.mahlzeitapp.presenter.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

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

    public Person getUserData()
    {
        Cursor resultSet = database.rawQuery("Select _id, firstname, lastname from user WHERE isUser=1",null);

        resultSet.moveToFirst();
        int id = resultSet.getInt(0);
        String firstname = resultSet.getString(1);
        String lastname = resultSet.getString(2);
        return new Person(Integer.toString(id), firstname,lastname );
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
        Cursor resultSet = database.rawQuery("Select _id, name from categories", null);
        ArrayList<Cat> categories = new ArrayList<Cat>();
        resultSet.moveToFirst();
        while (!resultSet.isAfterLast()) {
            int id = resultSet.getInt(0);
            String name = resultSet.getString(1);
            categories.add(new Cat(String.valueOf(id), name));
            resultSet.moveToNext();
        }
        return categories;
    }

    public void insertCat(Cat c)
    {
        database.delete("categories", "_id = ?", new String[] {c.getId()}); //new String "cat id"?
        ContentValues values = new ContentValues();
        values.put("_id", c.getId());
        values.put("name", c.getName());
        database.insertOrThrow("categories", null, values);
    }

    public ArrayList<Restaurant> getAllRestaurants() {
        Cursor resultSet = database.rawQuery("Select r._id, r.name, r.place, c._id, c.name from restaurants r, categories c WHERE r._id = c._id", null); //???
        ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
        resultSet.moveToFirst();
        while (!resultSet.isAfterLast()) {
            int id = resultSet.getInt(0);
            String name = resultSet.getString(1);
            String place = resultSet.getString(2);
            int catId = resultSet.getInt(3);
            String catName = resultSet.getString(4);
            restaurants.add(new Restaurant(String.valueOf(id), name, place, new Cat(String.valueOf(catId), catName)));
            resultSet.moveToNext();
        }
        return restaurants;
    }

    public void insertRestaurant(Restaurant r)
    {
        database.delete("categories", "_id = ?", new String[] {r.getCategory().getId()});
        ContentValues category = new ContentValues();
        category.put("_id", r.getCategory().getId());
        category.put("name", r.getCategory().getName());
        database.insertOrThrow("categories", null, category);

        database.delete("restaurants", "_id = ?", new String[] {r.getId()});
        ContentValues restaurant = new ContentValues();
        restaurant.put("_id", r.getId());
        restaurant.put("name",  r.getName());
        restaurant.put("place", r.getPlace());
        restaurant.put("id", r.getCategory().getId());
        database.insertOrThrow("restaurants", null, restaurant);
    }

    //Groups
    public ArrayList<Group> getAllGroups(Person user) {
        Cursor resultSetGroups = database.rawQuery("Select _id, idRestaurant from groups", null); //??? favorites?
        ArrayList<Group> groups = new ArrayList<Group>();
        resultSetGroups.moveToFirst();
        while (!resultSetGroups.isAfterLast()) {
            int groupid = resultSetGroups.getInt(0);
            int resId = resultSetGroups.getInt(1);

            Cursor resultSetRestaurant = database.rawQuery("Select name, place, id from restaurants WHERE _id = ?", new String[] {String.valueOf(resId)});
            resultSetRestaurant.moveToFirst();
            String restaurantName = resultSetRestaurant.getString(0);
            String restaurantPlace = resultSetRestaurant.getString(1);
            int categoryId = resultSetRestaurant.getInt(2);

            Cursor resultSetCategory = database.rawQuery("Select name from categories WHERE _id = ?", new String[] {String.valueOf(categoryId)});
            resultSetCategory.moveToFirst();
            String categoryName = resultSetCategory.getString(0);

            Cat c = new Cat(String.valueOf(categoryId), categoryName);
            Restaurant r = new Restaurant(String.valueOf(resId), restaurantName, restaurantPlace, c);

            Cursor resultSetMemebrs = database.rawQuery("Select g.idMember, p.firstname, p.lastname from group_members g, user p WHERE g.idMember = p._id AND g.idGroup = ?", new String[] {String.valueOf(groupid)});
            ArrayList<Person> members = new ArrayList<>();
            resultSetMemebrs.moveToFirst();
            while (!resultSetMemebrs.isAfterLast()) {
                int id = resultSetMemebrs.getInt(0);
                String firstname = resultSetMemebrs.getString(1);
                String lastname = resultSetMemebrs.getString(2);
                members.add(new Person(Integer.toString(id), firstname,lastname));
                resultSetMemebrs.moveToNext();
            }
            Group p = new Group(String.valueOf(groupid), r, members);
            groups.add(p);
            resultSetGroups.moveToNext();
        }
        return groups;
    }

    public void insertMember(Group g, Person user)
    {
        ContentValues member = new ContentValues();
        member.put("idGroup", g.getId());
        member.put("idMember", user.getPersonenkennziffer());
        database.insertOrThrow("group_members", null, member);

    }

    public int checkUpdateTable(String id, String pnum)
    {
        Cursor result = database.rawQuery("Select count(*) from group_members_updated WHERE idMember = ? AND idGroup = ?", new String[] {pnum, id});
        result.moveToNext();
        return result.getInt(0);
    }

    public void updateUpdateTable(int count, String id, String pnum) {
        if(count > 0) {
            database.delete("group_members_updated", "idGroup = ? AND idMember= ?", new String[] {id, pnum});
        } else {
            ContentValues member = new ContentValues();
            member.put("idGroup", id);
            member.put("idMember", pnum);
            database.insertOrThrow("group_members_updated", null, member);
        }
    }

    public void deleteUpdateTable(String id, String pnum) {
        database.delete("group_members_updated", "idGroup = ? AND idMember= ?", new String[] {id, pnum});
    }

    public int[] getUpdatedGroups() {
        Cursor result = database.rawQuery("Select idGroup from group_members_updated", new String[] {});
        int[] groupids = new int[result.getCount()];
        result.moveToFirst();
        int i = 0;
        while (!result.isAfterLast()) {
            groupids[i] = result.getInt(0);
            i++;
            result.moveToNext();
        }
        return groupids;
    }

    public void deleteMember(Group g, Person user)
    {
        database.delete("group_members", "idGroup = ? AND idMember = ?", new String[] {g.getId(), user.getPersonenkennziffer()});
    }

    public void insertGroup(Group g, Person user, ArrayList<Person> favorites)
    {
        database.delete("categories", "_id = ?", new String[] {g.getRestaurant().getCategory().getId()});
        ContentValues category = new ContentValues();
        category.put("_id", g.getRestaurant().getCategory().getId());
        category.put("name", g.getRestaurant().getCategory().getName());
        database.insertOrThrow("categories", null, category);

        database.delete("restaurants", "_id = ?", new String[] {g.getRestaurant().getId()});
        ContentValues restaurant = new ContentValues();
        restaurant.put("_id", g.getRestaurant().getId());
        restaurant.put("name", g.getRestaurant().getName());
        restaurant.put("place", g.getRestaurant().getPlace());
        restaurant.put("id", g.getRestaurant().getCategory().getId());
        database.insertOrThrow("restaurants", null, restaurant);

        database.delete("groups", "_id = ?", new String[] {g.getId()});
        ContentValues group = new ContentValues();
        group.put("_id", g.getId());
        group.put("idRestaurant", g.getRestaurant().getId());
        database.insertOrThrow("groups", null, group);

        database.delete("group_members", "idGroup = ?", new String[] {g.getId()});
        ArrayList<Person> members = g.getMembers();
        for(int i = 0; i < members.size(); i++) {
            if(!members.get(i).getPersonenkennziffer().equals(user.getPersonenkennziffer()))
                this.insertUser(members.get(i),0);
            if(favorites.contains(members.get(i)))
                this.insertFavorite(user, members.get(i));
            ContentValues member = new ContentValues();
            member.put("idGroup", g.getId());
            member.put("idMember", members.get(i).getPersonenkennziffer());
            database.insertOrThrow("group_members", null, member);
        }
    }
}
