package dz.tdm.esi.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import dz.tdm.esi.myapplication.Adapters.FolderAdapter;
import dz.tdm.esi.myapplication.R;
import dz.tdm.esi.myapplication.models.Dossier;
import dz.tdm.esi.myapplication.models.User;
import dz.tdm.esi.myapplication.models.Vehicule;

public class VehiculeFolderList extends AppCompatActivity {

    TextView nom, categorie, numero, pays, matricule;

    RecyclerView foldersListe;

    ConstraintLayout emptyFolder;

    FloatingActionButton addDosier, updateVehicule;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private List<Dossier> dossiers = new ArrayList<>();
    private FolderAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule_folder_list);
        getSupportActionBar().setTitle("DOSSIER VOITURE");

        Vehicule vehicule = null;
        SharedPreferences prefs = getSharedPreferences("vehicule", MODE_PRIVATE);
        String restoredText = prefs.getString("vehicule", null);
        if (restoredText != null) {
            Gson gson = new Gson();
            vehicule = gson.fromJson(restoredText, Vehicule.class);
        }

        nom = (TextView)findViewById(R.id.nom);
        categorie = (TextView)findViewById(R.id.categorie);
        numero = (TextView)findViewById(R.id.numero);
        pays = (TextView)findViewById(R.id.pays);
        matricule = (TextView)findViewById(R.id.matricule);

        nom.setText(vehicule.getNom());
        categorie.setText(vehicule.getCategorie());
        numero.setText(vehicule.getNumero());
        pays.setText(vehicule.getPays());
        matricule.setText(vehicule.getMatricule());

        User user = null;
        prefs = getSharedPreferences("user", MODE_PRIVATE);
        restoredText = prefs.getString("user", null);
        if (restoredText != null) {
            Gson gson = new Gson();
            user = gson.fromJson(restoredText, User.class);
        }

        foldersListe = (RecyclerView)findViewById(R.id.foldersListe);
        emptyFolder = (ConstraintLayout)findViewById(R.id.emptyFolder);


        myRef = database.getReference("AssurVoiture").child(user.getNumPermis()).child("Dossiers");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                dossiers.clear();
                long cpt = dataSnapshot.getChildrenCount();
                if (cpt == 0){
                    emptyFolder.setVisibility(View.VISIBLE);
                    foldersListe.setVisibility(View.GONE);
                }else{
                    emptyFolder.setVisibility(View.GONE);
                    foldersListe.setVisibility(View.VISIBLE);
                    for (int i = 0; i< cpt; i++){
                        DataSnapshot vehiculeDataSnapshot = dataSnapshot.child(String.valueOf(i));
                        Dossier dossier = vehiculeDataSnapshot.getValue(Dossier.class);
                        dossiers.add(dossier);
                    }

                    //  mAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });


        updateVehicule = (FloatingActionButton)findViewById(R.id.modifierVehicule);
        updateVehicule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehiculeFolderList.this, UpdateCar.class);
                startActivity(intent);
            }
        });

        addDosier = (FloatingActionButton) findViewById(R.id.addDosier);
        addDosier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VehiculeFolderList.this, AddDossier.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {

        Vehicule vehicule = null;
        SharedPreferences prefs = getSharedPreferences("vehicule", MODE_PRIVATE);
        String restoredText = prefs.getString("vehicule", null);
        if (restoredText != null) {
            Gson gson = new Gson();
            vehicule = gson.fromJson(restoredText, Vehicule.class);
        }
        nom.setText(vehicule.getNom());
        categorie.setText(vehicule.getCategorie());
        numero.setText(vehicule.getNumero());
        pays.setText(vehicule.getPays());
        matricule.setText(vehicule.getMatricule());

        super.onBackPressed();
    }
}
