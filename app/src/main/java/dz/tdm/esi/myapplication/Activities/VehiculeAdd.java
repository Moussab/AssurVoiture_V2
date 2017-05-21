package dz.tdm.esi.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import dz.tdm.esi.myapplication.R;
import dz.tdm.esi.myapplication.Util.Config;
import dz.tdm.esi.myapplication.Util.Util;
import dz.tdm.esi.myapplication.models.User;
import dz.tdm.esi.myapplication.models.Vehicule;

public class VehiculeAdd extends AppCompatActivity {

    EditText nomVehicule,
             categorie,
             niv,
             pays,
             matricule;

    Button addbtn;

    Vehicule vehicule;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule_add);
        getSupportActionBar().setTitle("Ajouter un vehicule");

        database = FirebaseDatabase.getInstance();


        nomVehicule = (EditText)findViewById(R.id.nomVehicule);
        categorie = (EditText)findViewById(R.id.categorie);
        niv = (EditText)findViewById(R.id.vin);
        pays = (EditText)findViewById(R.id.pays);
        matricule = (EditText)findViewById(R.id.matricule);

        addbtn = (Button) findViewById(R.id.addbtn);

        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nomVehicule.getText().toString().isEmpty() || categorie.getText().toString().isEmpty() ||
                        niv.getText().toString().isEmpty() || pays.getText().toString().isEmpty() || matricule.getText().toString().isEmpty()){
                    Util.alert(VehiculeAdd.this,"Veuillez remplir tout les champs !").show();
                }else{
                    vehicule = new Vehicule(
                            nomVehicule.getText().toString(),
                            categorie.getText().toString(),
                            niv.getText().toString(),
                            pays.getText().toString(),
                            matricule.getText().toString()
                    );

                    User user = null;
                    SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
                    String restoredText = prefs.getString("user", null);
                    if (restoredText != null) {
                        Gson gson = new Gson();
                        user = gson.fromJson(restoredText, User.class);
                    }

                    myRef = database.getReference("AssurVoiture").child(user.getNumPermis()).child("Vehicules");
                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            Config.MAX_ID_VEHICULE = dataSnapshot.getChildrenCount();

                            System.out.println("Config.MAX_ID_VEHICULE : " + Config.MAX_ID_VEHICULE);

                            DatabaseReference child;
                            myRef.keepSynced(true);
                            child = myRef.child(String.valueOf(Config.MAX_ID_VEHICULE));
                            child.setValue(vehicule);

                            Intent it = new Intent(VehiculeAdd.this, VehiculeList.class);
                            startActivity(it);
                        }
                        @Override
                        public void onCancelled(DatabaseError error) {}
                    });


                }
            }
        });

    }
}
