package com.sanga.schoolwash.UI;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.sanga.schoolwash.Adapters.HomeAdapter;
import com.sanga.schoolwash.R;


public class HomeFragment extends Fragment implements HomeAdapter.AdapterListener {
    private RecyclerView recyclerView;
    private GridLayoutManager manager;
    private RelativeLayout layout;


    public HomeFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        initialize(view);
        setAdapter();
        setImages();
        return view;
    }

    private void initialize(View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        if (getActivity() != null)
            ((MainActivity)getActivity()).setSupportActionBar(toolbar);
        manager = new GridLayoutManager(getContext(), 2);
        recyclerView = view.findViewById(R.id.view_menu);
        layout = view.findViewById(R.id.home_layout);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_sign_out){
            NavController navigation = Navigation.findNavController(layout);
            navigation.popBackStack();
            navigation.navigate(R.id.loginFragment);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setImages(){
    }

    private void setAdapter(){
        String C = "Form, Report, Help, Share";
        int [] img = {R.drawable.form, R.drawable.report, R.drawable.help, R.drawable.share};
        String[] categories = C.split(",");
        HomeAdapter adapter = new HomeAdapter(categories, img, getContext(), this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void passValue(String value) {
        if (value.contains("Share")){
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT,
                    "Hey check out my app at: https://play.google.com/store/apps/details?" +
                            "id=com.google.android.apps.plus");
            sendIntent.setType("text/plain");
            startActivity(Intent.createChooser(sendIntent, "Share SchoolWash via"));
        }
        if (value.contains("Report")){
            Navigation.findNavController(recyclerView).navigate(R.id.action_homeFragment_to_reportFragment);
        }
        if (value.contains("Help")){
            Toast.makeText(getContext(), value, Toast.LENGTH_SHORT).show();
        }
        if (value.contains("Form")){
           Navigation.findNavController(recyclerView).navigate(R.id.action_homeFragment_to_formFragment);
        }
    }
}
