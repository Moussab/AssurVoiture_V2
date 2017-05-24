package dz.tdm.esi.myapplication.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import dz.tdm.esi.myapplication.Adapters.FolderAdapter;
import dz.tdm.esi.myapplication.R;
import dz.tdm.esi.myapplication.Util.ClickListener;
import dz.tdm.esi.myapplication.Util.DividerItemDecoration;
import dz.tdm.esi.myapplication.Util.RecyclerTouchListener;
import dz.tdm.esi.myapplication.models.Dossier;

/**
 * Created by Amine on 21/05/2017.
 */

public class Traitement extends Fragment {


    private List<Dossier> agendaList = new ArrayList<>();
    private RecyclerView recyclerView;
    private FolderAdapter mAdapter;
    RecyclerView.LayoutManager mLayoutManager;

    public Traitement() {
        // Required empty public constructor
    }

    public Traitement(List<Dossier> agendas) {
        // Required empty public constructor
        agendaList = agendas;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_traitement, container, false);


        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_traitement);
        mLayoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));


        mAdapter = new FolderAdapter(agendaList,getContext());

        recyclerView.setAdapter(mAdapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Dossier dossier = agendaList.get(position);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));


        // Inflate the layout for this fragment
        return view;
    }





}
