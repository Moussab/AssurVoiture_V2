package dz.tdm.esi.myapplication.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import dz.tdm.esi.myapplication.models.User;

/**
 * Created by Amine on 20/05/2017.
 */

public class userDAO extends DAOBase {



    public static final String TABLE_NAME = "user";
    public static final String KEY = "id_user";
    public static final String Nom = "userName";
    public static final String NUM_TEL = "numTel";
    public static final String EMAIL = "email";
    public static final String MDP = "mdp";
    public static final String NUM_PERMIS = "numPermis";


    public static final String TABLE_CREATE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_NAME + " ("
            + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + Nom + " TEXT, "
            + NUM_TEL + " TEXT, "
            + EMAIL + " TEXT,"
            + MDP + " TEXT,"
            + NUM_PERMIS + " TEXT);";

    public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public userDAO(Context pContext) {
        super(pContext);
        open();
        getDb().execSQL(TABLE_CREATE);
    }

    public void ajouter(User user) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(Nom, user.getUserName());
        value.put(NUM_TEL, user.getNumTel());
        value.put(EMAIL, user.getEmail());
        value.put(MDP, user.getMdp());
        value.put(NUM_PERMIS, user.getNumPermis());
        mDb.insert(TABLE_NAME, null, value);
    }

    public void supprimer(long id) {
        // CODE
        mDb.delete(TABLE_NAME, KEY + " = ?", new String[] {String.valueOf(id)});
    }

    public void modifier(User user) {
        // CODE
        ContentValues value = new ContentValues();
        value.put(Nom, user.getUserName());
        value.put(NUM_TEL, user.getNumTel());
        value.put(EMAIL, user.getEmail());
        value.put(MDP, user.getMdp());
        value.put(NUM_PERMIS, user.getNumPermis());
        mDb.update(TABLE_NAME, value, KEY  + " = ?", new String[] {String.valueOf(user.getId())});

    }

    public User selectionner(long id) {
        // CODE
        Cursor cursor = mDb.rawQuery("select * from " + TABLE_NAME + " where " + KEY +" = ?", new String[]{String.valueOf(id)});

        cursor.moveToFirst();

        User user = new User(
                cursor.getLong(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5)
        );

        return user;
    }

    public List getFournisseurs() {
        List<User> users = new ArrayList<User>();

        // Name of the columns we want to select
        String[] tableColumns = new String[] {KEY,Nom,NUM_TEL,EMAIL,MDP,NUM_PERMIS};

        // Query the database
        Cursor cursor = mDb.query(TABLE_NAME, tableColumns, null, null, null, null, null);
        cursor.moveToFirst();

        // Iterate the results
        while (!cursor.isAfterLast()) {
            User user = new User(
                    cursor.getLong(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4),
                    cursor.getString(5)
            );


            // Add to the DB
            users.add(user);

            // Move to the next result
            cursor.moveToNext();
        }

        return users;
    }
}
