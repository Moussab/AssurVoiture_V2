package dz.tdm.esi.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {


    private TextView sinscrire;
    private EditText numPermis;
    private EditText password;
    private TextView buttonsignin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();


        sinscrire = (TextView) findViewById(R.id.inscrire);
        numPermis = (EditText)findViewById(R.id.numPermis);
        password = (EditText)findViewById(R.id.password);
        buttonsignin = (TextView) findViewById(R.id.buttonsignin);



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
            /*    Intent it = new Intent(Login.this, Home.class);
                startActivity(it);*/
                onBackPressed();
            }
        });


    }
}
