package dz.tdm.esi.myapplication.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.Calendar;

import dz.tdm.esi.myapplication.DAO.DossierDAO;
import dz.tdm.esi.myapplication.R;
import dz.tdm.esi.myapplication.Util.ImageTaker;
import dz.tdm.esi.myapplication.Util.Util;
import dz.tdm.esi.myapplication.Util.VideoTaker;
import dz.tdm.esi.myapplication.models.Dossier;
import dz.tdm.esi.myapplication.models.EtatDossier;

import static dz.tdm.esi.myapplication.Util.ImageTaker.CAMERA_CAPTURE_IMAGE_REQUEST_CODE;
import static dz.tdm.esi.myapplication.Util.VideoTaker.REQUEST_VIDEO_CAPTURE;

public class AddDossier extends AppCompatActivity {

    CheckBox adversaire;
    CardView adversaire1, adversaire2, adversaire3, adversaire4;
    TextView date;
    ImageView photo, video;
    VideoView videoPlay;
    EditText montant, nomAdver, nPermisAdvers, vehiculeAdvers, matriculeAdvers;
    Button addFolder;
    private int mYear, mMonth, mDay;

    DossierDAO dossierDAO;
    Dossier dossier = new Dossier();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_dossier);

        dossierDAO = new DossierDAO(this);

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

        videoPlay = (VideoView)findViewById(R.id.videoPlay);

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

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(AddDossier.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dossier.setDate("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year + "");
                                date.setText(dossier.getDate());
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                video.setVisibility(View.GONE);
                videoPlay.setVisibility(View.VISIBLE);
                VideoTaker.dispatchTakeVideoIntent(AddDossier.this,videoPlay);
            }
        });

        videoPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoPlay.start();
            }
        });

        photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageTaker.captureImage(AddDossier.this,photo);
            }
        });



        addFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dossier.getDate().isEmpty() || dossier.getImageURL().isEmpty()
                        || dossier.getVideoURL().isEmpty() || montant.getText().toString().isEmpty()){
                    Util.alert(AddDossier.this,"Veuillez remplir les champs qui manque").show();
                }else {
                    dossier.setEtat(EtatDossier.ouvert);
                    dossier.setMontant(montant.getText().toString());
                    if(adversaire.isChecked()){
                        if (nomAdver.getText().toString().isEmpty() || nPermisAdvers.getText().toString().isEmpty() ||
                                vehiculeAdvers.getText().toString().isEmpty() || matriculeAdvers.getText().toString().isEmpty()){
                            Util.alert(AddDossier.this,"Veuillez remplir les champs concernant l'adversaire").show();
                        }else {
                            dossier.setNomAdversaire(nomAdver.getText().toString());
                            dossier.setMatriculeAdversaire(matriculeAdvers.getText().toString());
                            dossier.setNumPermisAdversaire(nPermisAdvers.getText().toString());
                            dossier.setVehiculeAdversaire(vehiculeAdvers.getText().toString());

                            dossierDAO.ajouter(dossier);
                            onBackPressed();
                        }
                    }else {
                        dossier.setNomAdversaire(nomAdver.getText().toString());
                        dossier.setMatriculeAdversaire(matriculeAdvers.getText().toString());
                        dossier.setNumPermisAdversaire(nPermisAdvers.getText().toString());
                        dossier.setVehiculeAdversaire(vehiculeAdvers.getText().toString());

                        System.out.println(dossier.toString());
                        dossierDAO.ajouter(dossier);
                        dossier = dossierDAO.selectionner("2");
                        System.out.println(dossier.toString());


                        onBackPressed();
                    }
                }

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            dossier.setVideoURL(VideoTaker.onActivityResult(requestCode,resultCode,data));
            videoPlay.setVideoPath(dossier.getVideoURL());
            MediaController videoMediaController = new MediaController(AddDossier.this);
            videoMediaController.setMediaPlayer(videoPlay);
            videoPlay.setMediaController(videoMediaController);
            videoPlay.requestFocus();
        }else {
            dossier.setImageURL(ImageTaker.onActivityResult(requestCode,resultCode,data));
        }
    }


}
