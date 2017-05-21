package dz.tdm.esi.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.gson.Gson;

import dz.tdm.esi.myapplication.DAO.UserDAO;
import dz.tdm.esi.myapplication.R;
import dz.tdm.esi.myapplication.Util.Util;
import dz.tdm.esi.myapplication.models.User;

public class SignUp extends AppCompatActivity {

    TextView suivant;

    EditText userName,
            numTel,
            email,
            numPermis,
            mdp;

    UserDAO userDAO;

    FirebaseDatabase database;
    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        getSupportActionBar().hide();

        database = FirebaseDatabase.getInstance();
        database.setPersistenceEnabled(true);
        myRef = database.getReference("AssurVoiture");


        userDAO = new UserDAO(this);
        suivant = (TextView) findViewById(R.id.account);


        userName = (EditText) findViewById(R.id.user);
        numTel = (EditText) findViewById(R.id.numTel);
        email = (EditText) findViewById(R.id.email);
        numPermis = (EditText) findViewById(R.id.numPermis);
        mdp = (EditText) findViewById(R.id.password);


        suivant.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userName.getText().toString().isEmpty() ||
                        numTel.getText().toString().isEmpty() ||
                        email.getText().toString().isEmpty() ||
                        numPermis.getText().toString().isEmpty() ||
                        mdp.getText().toString().isEmpty()){
                    Util.alert(SignUp.this,"Remplir les champs vide !");
                }else{

                    User user = new User(userName.getText().toString().trim(),
                            numTel.getText().toString().trim(),
                            email.getText().toString().trim(),
                            mdp.getText().toString().trim(),
                            numPermis.getText().toString().trim());

                    DatabaseReference child;
                    myRef.keepSynced(true);
                    child = myRef.child(user.getNumPermis()).child("client");
                    child.setValue(user);

                    Gson gson = new Gson();
                    String jsonInString = gson.toJson(user);
                    SharedPreferences.Editor editor = getSharedPreferences("user", MODE_PRIVATE).edit();
                    editor.putString("user", jsonInString);
                    editor.commit();

                    userDAO.ajouter(user);

                    Intent it = new Intent(SignUp.this, VehiculeAdd.class);
                    startActivity(it);
                    onBackPressed();

                }
            }
        });
    }


}
