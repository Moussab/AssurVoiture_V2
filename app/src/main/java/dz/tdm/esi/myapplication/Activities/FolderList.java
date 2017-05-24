package dz.tdm.esi.myapplication.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
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

import dz.tdm.esi.myapplication.Adapters.FolderAdapter;
import dz.tdm.esi.myapplication.Adapters.VehiculeAdapter;
import dz.tdm.esi.myapplication.Fragments.Ouvert;
import dz.tdm.esi.myapplication.Fragments.Traitement;
import dz.tdm.esi.myapplication.R;
import dz.tdm.esi.myapplication.Util.ClickListener;
import dz.tdm.esi.myapplication.Util.RecyclerTouchListener;
import dz.tdm.esi.myapplication.models.Dossier;
import dz.tdm.esi.myapplication.models.User;

public class FolderList extends AppCompatActivity {

    FloatingActionButton addVehicule;
    RecyclerView folderList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    private List<Dossier> dossiers = new ArrayList<>();
    private FolderAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicule_list);
        getSupportActionBar().setTitle("Liste des Dossiers");

        User user = null;
        SharedPreferences prefs = getSharedPreferences("user", MODE_PRIVATE);
        String restoredText = prefs.getString("user", null);
        if (restoredText != null) {
            Gson gson = new Gson();
            user = gson.fromJson(restoredText, User.class);
        }


        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);


        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);



        /*

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("AssurVoiture").child(user.getNumPermis()).child("folders");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.

                dossiers.clear();
                long cpt = dataSnapshot.getChildrenCount();
                for (int i = 0; i< cpt; i++){
                    DataSnapshot dossierDataSnapshot = dataSnapshot.child(String.valueOf(i));
                    Dossier dossier = dossierDataSnapshot.getValue(Dossier.class);
                    dossier.setId(i);
                    dossiers.add(dossier);
                }

                //mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        folderList = (RecyclerView)findViewById(R.id.vehiculeList);
        mLayoutManager = new LinearLayoutManager(this);
        folderList.setLayoutManager(mLayoutManager);
        folderList.setItemAnimator(new DefaultItemAnimator());
        folderList.addItemDecoration(new DividerItemDecoration(this,LinearLayoutManager.VERTICAL));
        mAdapter = new FolderAdapter(dossiers,this);
        folderList.setAdapter(mAdapter);
        folderList.addOnItemTouchListener(new RecyclerTouchListener(this, folderList, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Gson gson = new Gson();
                String jsonInString = gson.toJson(dossiers.get(position));
                SharedPreferences.Editor editor = getSharedPreferences("folder", MODE_PRIVATE).edit();
                editor.putString("folder", jsonInString);
                editor.commit();
                Intent intent = new Intent(FolderList.this,FolderDetail.class);
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));
*/

     /*   addVehicule = (FloatingActionButton)findViewById(R.id.addVehicule);
        addVehicule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(FolderList.this, AddDossier.class);
                startActivity(it);
            }
        });*/

    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Ouvert(), "Ouvert");
        adapter.addFrag(new Traitement(), "En Traitement");
        viewPager.setAdapter(adapter);
    }




    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

}
