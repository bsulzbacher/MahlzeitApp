package app.mahlzeitapp.presenter.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Bianca on 12.05.2017.
 */

public class MahlzeitDbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "mahlzeit.db";
    public static final int DB_VERSION = 14;

    public static final String TABLE_USER = "user";
    public static final String TABLE_FAVORITES = "favorites";
    public static final String TABLE_CAT = "categories";
    public static final String TABLE_RESTAURANTS = "restaurants";
    public static final String TABLE_GROUPS = "groups";
    public static final String TABLE_MEMBERS_GROUPS = "group_members";
    public static final String TABLE_MEMBERS_GROUPS_UPDATED = "group_members_updated";

    //table user
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_FIRSTNAME = "firstname";
    public static final String COLUMN_LASTNAME = "lastname";
    public static final String COLUMN_PRIMARAY_USER = "isUser";

    // table favorites
    public static final String COLUMN_ID_USER = "_id";
    public static final String COLUMN_ID_FAVORITE = "idFavorite";

    public static final String COLUMN_ID_CAT = "id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PLACE = "place";
    public static final String COLUMN_ID_RESTAURANT = "idRestaurant";
    public static final String COLUMN_ID_MEMBER = "idMember";
    public static final String COLUMN_ID_GROUP = "idGroup";

    public static final String SQL_CREATE =
            "CREATE TABLE " + TABLE_USER +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_PRIMARAY_USER + " INTEGER NOT NULL, " +
                    COLUMN_FIRSTNAME + " TEXT NOT NULL, " +
                    COLUMN_LASTNAME + " TEXT NOT NULL);";

    public static final String SQL_CREATE_MAPPING =
            "CREATE TABLE " + TABLE_FAVORITES +
                    "(" + COLUMN_ID_USER + " INTEGER NOT NULL, " +
                    COLUMN_ID_FAVORITE + " INTEGER NOT NULL," +
                    " PRIMARY KEY ("+COLUMN_ID_USER+", "+COLUMN_ID_FAVORITE+"));";

    public static final String SQL_CREATE2 =
            "CREATE TABLE " + TABLE_CAT +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT NOT NULL);";

    public static final String SQL_CREATE3 =
            "CREATE TABLE " + TABLE_RESTAURANTS +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_PLACE + "  TEXT NOT NULL, "+
                    COLUMN_ID_CAT + "  INTEGER NOT NULL);";

    public static final String SQL_CREATE4 =
            "CREATE TABLE " + TABLE_GROUPS +
                    "(" + COLUMN_ID + " INTEGER PRIMARY KEY, " +
                    COLUMN_ID_RESTAURANT + " INTEGER NOT NULL);";

    public static final String SQL_CREATE5 =
            "CREATE TABLE " + TABLE_MEMBERS_GROUPS +
                    "(" + COLUMN_ID_GROUP + "  INTEGER NOT NULL, " +
                    COLUMN_ID_MEMBER + " INTEGER NOT NULL, "+
                    " PRIMARY KEY ("+COLUMN_ID_GROUP+", "+COLUMN_ID_MEMBER+"));";

    public static final String SQL_CREATE6 =
            "CREATE TABLE " + TABLE_MEMBERS_GROUPS_UPDATED +
                    "(" + COLUMN_ID_GROUP + "  INTEGER NOT NULL, " +
                    COLUMN_ID_MEMBER + " INTEGER NOT NULL, "+
                    " PRIMARY KEY ("+COLUMN_ID_GROUP+", "+COLUMN_ID_MEMBER+"));";




    public MahlzeitDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE);
        db.execSQL(SQL_CREATE_MAPPING);
        db.execSQL(SQL_CREATE2);
        db.execSQL(SQL_CREATE3);
        db.execSQL(SQL_CREATE4);
        db.execSQL(SQL_CREATE5);
        db.execSQL(SQL_CREATE6);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CAT);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_RESTAURANTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBERS_GROUPS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBERS_GROUPS_UPDATED);

       onCreate(db);
    }
}
