package dz.tdm.esi.myapplication.Activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import java.util.List;

import dz.tdm.esi.myapplication.R;
import dz.tdm.esi.myapplication.Util.Util;
import dz.tdm.esi.myapplication.models.Dossier;
import dz.tdm.esi.myapplication.models.User;
import dz.tdm.esi.myapplication.models.Vehicule;

public class FolderUpdate extends AppCompatActivity {
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
        setContentView(R.layout.activity_update_car);
        getSupportActionBar().setTitle("Modifier un vehicule");

        database = FirebaseDatabase.getInstance();

        SharedPreferences prefs = getSharedPreferences("vehicule", MODE_PRIVATE);
        String restoredText = prefs.getString("vehicule", null);
        if (restoredText != null) {
            Gson gson = new Gson();
            vehicule = gson.fromJson(restoredText, Vehicule.class);
        }


        nomVehicule = (EditText)findViewById(R.id.nomVehicule);
        categorie = (EditText)findViewById(R.id.categorie);
        niv = (EditText)findViewById(R.id.vin);
        pays = (EditText)findViewById(R.id.pays);
        matricule = (EditText)findViewById(R.id.matricule);


        nomVehicule.setText(vehicule.getNom());
        categorie.setText(vehicule.getCategorie());
        niv.setText(vehicule.getNumero());
        pays.setText(vehicule.getPays());
        matricule.setText(vehicule.getMatricule());

        addbtn = (Button) findViewById(R.id.addbtn);
        addbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (nomVehicule.getText().toString().isEmpty() || categorie.getText().toString().isEmpty() ||
                        niv.getText().toString().isEmpty() || pays.getText().toString().isEmpty() || matricule.getText().toString().isEmpty()){
                    Util.alert(FolderUpdate.this,"Veuillez remplir tout les champs !").show();
                }else{
                    vehicule.setNom(nomVehicule.getText().toString());
                    vehicule.setCategorie(categorie.getText().toString());
                    vehicule.setNumero(niv.getText().toString());
                    vehicule.setPays(pays.getText().toString());
                    vehicule.setMatricule(matricule.getText().toString());

                    User user = null;
                    SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
                    String restoredText = prefs.getString("user", null);
                    if (restoredText != null) {
                        Gson gson = new Gson();
                        user = gson.fromJson(restoredText, User.class);
                    }

                    DatabaseReference child;
                    myRef = database.getReference("AssurVoiture").child(user.getToken()).child("Vehicules");
                    myRef.keepSynced(true);
                    child = myRef.child(String.valueOf(vehicule.getId()));
                    child.setValue(vehicule);

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(vehicule);
                    SharedPreferences.Editor editor = getSharedPreferences("vehicule", MODE_PRIVATE).edit();
                    editor.putString("vehicule", jsonInString);
                    editor.commit();

                    onBackPressed();
                }
            }
        });
    }
}
