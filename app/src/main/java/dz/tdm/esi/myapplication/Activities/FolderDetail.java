package dz.tdm.esi.myapplication.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import dz.tdm.esi.myapplication.R;

public class FolderDetail extends AppCompatActivity {

    TextView date, montant, nomAdver, nPermisAdvers, vehiculeAdvers, matriculeAdvers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_folder_detail);
    }
}
