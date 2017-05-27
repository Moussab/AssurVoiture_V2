package dz.tdm.esi.myapplication.Util;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Amine on 26/05/2017.
 */

public class FireBaseDB {

    private static FirebaseDatabase mDatabase;
    private static DatabaseReference myRef;

    public static FirebaseDatabase getDatabase() {
        if (mDatabase == null) {
            mDatabase = FirebaseDatabase.getInstance();
            mDatabase.setPersistenceEnabled(true);
            myRef = mDatabase.getReference();
        }
        return mDatabase;
    }

    public static DatabaseReference getMyRef() {
        if (myRef == null) {
            getDatabase();
        }
        return myRef;
    }

}
