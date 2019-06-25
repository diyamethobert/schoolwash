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
        helpArrayList.add(new Help("On home page, tap : at the top right corner then click log out"));

        HelpClass No = new HelpClass("Do want to log out?", helpArrayList);
        helpClassArrayList.add(No);

        ArrayList<Help> formHelp = new ArrayList<>();
        formHelp.add(new Help("On home page, click Form and form page will appear then fill all fields " +
                "because all fields required and then click submit button and make sure success massage display"));

        HelpClass form = new HelpClass("Do want to fill data of students?", formHelp);
        helpClassArrayList.add(form);

        ArrayList<Help> report = new ArrayList<>();
        report.add(new Help("On home page, click report to view list of added schools and the total number of schools registered"));

        HelpClass view = new HelpClass("Do want to view report?", report);
        helpClassArrayList.add(view);
    }

    private void setDisplay(){
        HelpAdapter adapter = new HelpAdapter(helpClassArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

    }
}
