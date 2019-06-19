package com.sanga.schoolwash;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReportFragment extends Fragment {
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private ProgressBar progressBar;
    private ImageView imageView;
    private LinearLayoutManager linearLayout;
    private ReportAdapter adapter;
    private ApiInterface apiInterface;
    private List<FormData>dataList;



    public ReportFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_report, container, false);
        initialize(view);
        getReport();
        setOnClickListener();
        pullToRefresh();
        return view;
    }

    private void initialize(View view) {
        recyclerView = view.findViewById(R.id.view_report);
        refreshLayout = view.findViewById(R.id.refresh);
        progressBar = view.findViewById(R.id.loading_report);
        imageView = view.findViewById(R.id.back_from_report);
        linearLayout = new LinearLayoutManager(getContext());
        apiInterface = Api.getApi().create(ApiInterface.class);
    }

    private void setOnClickListener(){
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void onBackPressed() {
        if (getActivity() != null)
            getActivity().onBackPressed();
    }

    private void pullToRefresh(){
        refreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorAccent),
                getResources().getColor(R.color.colorBlack));
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getReport();
            }
        });
    }
    private void getReport(){
        Call<List<FormData>> call = apiInterface.getReport();
        call.enqueue(new Callback<List<FormData>>() {
            @Override
            public void onResponse(@NonNull Call<List<FormData>> call, @NonNull Response<List<FormData>> response) {
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
                if (response.isSuccessful() && response.body() != null) {
                    dataList = response.body();
                    adapter = new ReportAdapter(getContext(), dataList);
                    setViews();
                }else
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<List<FormData>> call, @NonNull Throwable t) {
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });
    }

    private void setViews(){
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);
    }

}
