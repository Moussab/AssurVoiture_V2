package dz.tdm.esi.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import dz.tdm.esi.myapplication.Adapters.VehiculeAdapter;
import dz.tdm.esi.myapplication.MainActivity;
import dz.tdm.esi.myapplication.R;
import dz.tdm.esi.myapplication.Util.ClickListener;
import dz.tdm.esi.myapplication.Util.Config;
import dz.tdm.esi.myapplication.Util.RecyclerTouchListener;
import dz.tdm.esi.myapplication.models.User;
import dz.tdm.esi.myapplication.models.Vehicule;

public class VehiculeList extends AppCompatActivity {

    FloatingActionButton addVehicule;
    RecyclerView vehiculeList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private List<Vehicule> vehicules = new ArrayList<>();
    private VehiculeAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule_list);
        getSupportActionBar().setTitle("Liste des Véhicules");

        User user = null;
        SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
        String restoredText = prefs.getString("user", null);
        if (restoredText != null) {
            Gson gson = new Gson();
            user = gson.fromJson(restoredText, User.class);
        }
        

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("AssurVoiture").child(user.getNumPermis()).child("Vehicules");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                vehicules.clear();
                long cpt = dataSnapshot.getChildrenCount();
                for (int i = 0; i< cpt; i++){
                    DataSnapshot vehiculeDataSnapshot = dataSnapshot.child(String.valueOf(i));
                    Vehicule vehicule = vehiculeDataSnapshot.getValue(Vehicule.class);
                    vehicules.add(vehicule);
                }

                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        vehiculeList = (RecyclerView)findViewById(R.id.vehiculeList);
        mLayoutManager = new LinearLayoutManager(this);
        vehiculeList.setLayoutManager(mLayoutManager);
        vehiculeList.setItemAnimator(new DefaultItemAnimator());
        vehiculeList.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mAdapter = new VehiculeAdapter(vehicules,this);
        vehiculeList.setAdapter(mAdapter);
        vehiculeList.addOnItemTouchListener(new RecyclerTouchListener(this, vehiculeList, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Gson gson = new Gson();
                String jsonInString = gson.toJson(vehicules.get(position));
                SharedPreferences.Editor editor = getSharedPreferences("vehicule", MODE_PRIVATE).edit();
                editor.putString("vehicule", jsonInString);
                editor.commit();
                Intent intent = new Intent(VehiculeList.this,VehiculeFolderList.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        addVehicule = (FloatingActionButton)findViewById(R.id.addVehicule);
        addVehicule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(VehiculeList.this, VehiculeAdd.class);
                startActivity(it);
            }
        });

    }
}
