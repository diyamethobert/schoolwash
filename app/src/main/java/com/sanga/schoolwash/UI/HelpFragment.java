package com.sanga.schoolwash.UI;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sanga.schoolwash.Adapters.HelpAdapter;
import com.sanga.schoolwash.ExpandableListView.Help;
import com.sanga.schoolwash.ExpandableListView.HelpClass;
import com.sanga.schoolwash.R;

import java.util.ArrayList;


public class HelpFragment extends Fragment {

    private RecyclerView recyclerView;
    private ImageView imageView;
    private ArrayList<HelpClass> helpClassArrayList;
    private ArrayList<Help> helpArrayList;
    private LinearLayoutManager manager;

    public HelpFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);
        initialize(view);
        populateArray();
        setDisplay();
        setOnClickListener();
        return view;
    }

    private void initialize(View view) {
       recyclerView = view.findViewById(R.id.help_view);
       imageView = view.findViewById(R.id.back_from_help);
       helpClassArrayList = new ArrayList<>();
       helpArrayList = new ArrayList<>();
       manager = new LinearLayoutManager(getContext());
    }

    private void setOnClickListener(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goHome();
            }
        });
    }

    private void goHome() {
        if (getActivity() != null)
            getActivity().onBackPressed();
    }

    private void populateArray(){
        helpArrayList.add(new Help("One"));
        helpArrayList.add(new Help("Two"));
        helpArrayList.add(new Help("Three"));
        helpArrayList.add(new Help("Four"));

        HelpClass No = new HelpClass("Numbers", helpArrayList);
        helpClassArrayList.add(No);
    }

    private void setDisplay(){
        HelpAdapter adapter = new HelpAdapter(helpClassArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

    }
}
