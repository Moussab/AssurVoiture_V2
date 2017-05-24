package dz.tdm.esi.myapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import dz.tdm.esi.myapplication.R;
import dz.tdm.esi.myapplication.models.Dossier;

public class AddDossier extends AppCompatActivity {

    CheckBox adversaire;
    CardView adversaire1, adversaire2, adversaire3, adversaire4;
    TextView date;
    ImageView photo, video;
    EditText montant, nomAdver, nPermisAdvers, vehiculeAdvers, matriculeAdvers;
    Button addFolder;

    Dossier dossier = new Dossier();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dossier);

        adversaire = (CheckBox) findViewById(R.id.adversaire);
        adversaire1 = (CardView) findViewById(R.id.adversaire1);
        adversaire1.setVisibility(View.GONE);
        adversaire2 = (CardView) findViewById(R.id.adversaire2);
        adversaire2.setVisibility(View.GONE);
        adversaire3 = (CardView) findViewById(R.id.adversaire3);
        adversaire3.setVisibility(View.GONE);
        adversaire4 = (CardView) findViewById(R.id.adversaire4);
        adversaire4.setVisibility(View.GONE);

        date = (TextView) findViewById(R.id.date);

        photo = (ImageView)findViewById(R.id.photo);
        video = (ImageView)findViewById(R.id.video);

        montant = (EditText) findViewById(R.id.montant);
        nomAdver = (EditText) findViewById(R.id.nomAdver);
        nPermisAdvers = (EditText) findViewById(R.id.nPermisAdvers);
        vehiculeAdvers = (EditText) findViewById(R.id.vehiculeAdvers);
        matriculeAdvers = (EditText) findViewById(R.id.matriculeAdvers);

        addFolder = (Button) findViewById(R.id.addFolder);

        adversaire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adversaire.isChecked()){
                    adversaire1.setVisibility(View.VISIBLE);
                    adversaire2.setVisibility(View.VISIBLE);
                    adversaire3.setVisibility(View.VISIBLE);
                    adversaire4.setVisibility(View.VISIBLE);
                }else {
                    adversaire1.setVisibility(View.GONE);
                    adversaire2.setVisibility(View.GONE);
                    adversaire3.setVisibility(View.GONE);
                    adversaire4.setVisibility(View.GONE);
                }
            }
        });

        addFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
