package dz.tdm.esi.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import dz.tdm.esi.myapplication.DAO.UserDAO;
import dz.tdm.esi.myapplication.R;
import dz.tdm.esi.myapplication.Util.Util;
import dz.tdm.esi.myapplication.models.User;

public class Login extends AppCompatActivity {

    UserDAO userDAO;
    private TextView sinscrire;
    private EditText numPermis;
    private EditText password;
    private TextView buttonsignin;
    boolean b = false, aBoolean = false;

    FirebaseDatabase database;

    User user = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        userDAO = new UserDAO(this);

        sinscrire = (TextView) findViewById(R.id.inscrire);
        numPermis = (EditText)findViewById(R.id.numPermis);
        password = (EditText)findViewById(R.id.password);
        buttonsignin = (TextView) findViewById(R.id.buttonsignin);



        SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
        String restoredText = prefs.getString("user", null);
        if (restoredText != null) {
            Gson gson = new Gson();
            User user = gson.fromJson(restoredText, User.class);
            User user1 = userDAO.selectionner(user.getNumPermis());
            if (user.getMdp().compareTo(user1.getMdp()) == 0){
                Intent it = new Intent(Login.this, VehiculeList.class);
                startActivity(it);
            }
        }



        sinscrire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(Login.this, SignUp.class);
                startActivity(it);
            }
        });


        buttonsignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numPermis.getText().toString().isEmpty() ||
                        password.getText().toString().isEmpty()){
                    Util.alert(Login.this,"Remplir les champs vide !").show();
                }else{
                    DatabaseReference scoresRef = FirebaseDatabase.getInstance().getReference("AssurVoiture").child(numPermis.getText().toString().trim()).child("client");
                    scoresRef.keepSynced(true);
                    scoresRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            user = dataSnapshot.getValue(User.class);
                            if (!aBoolean)
                            login(user);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }



            }
        });


    }

    private void login(User user){
        if (user != null ){
            Gson gson = new Gson();
            String jsonInString = gson.toJson(user);
            SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
            editor.putString("user", jsonInString);
            editor.commit();

            if(userDAO.getUsers().size() == 0){
                userDAO.ajouter(user);
            }else
                userDAO.modifier(user);

            if (user.getMdp().toString().compareTo(password.getText().toString()) == 0 &&
                    user.getNumPermis().toString().compareTo(numPermis.getText().toString()) == 0) {
                b = true;
            }

            if (b) {
                Intent it = new Intent(Login.this, VehiculeList.class);
                startActivity(it);
                user = null;
            } else
                Util.alert(Login.this, "Compte inéxistant !").show();
        }
    }
}
