package com.sanga.schoolwash.UI;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.sanga.schoolwash.Database.Api;
import com.sanga.schoolwash.Database.ApiInterface;
import com.sanga.schoolwash.Database.Users;
import com.sanga.schoolwash.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class FormFragment extends Fragment {
    private ImageView goBack;
    private TextView school, schoolHead, numFemaleStudents, numMaleStudents, numStaff,
            numMaleToilets, numFemaleToilets, numStaffToilets, numTaps, numDustBin;
    private Button submit;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    private ScrollView scrollView;

    public FormFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_form, container, false);
        initialize(view);
        setOnClickListener();
        return view;
    }

    private void initialize(View view) {
        goBack = view.findViewById(R.id.back_from_form);
        school = view.findViewById(R.id.school_name);
        schoolHead = view.findViewById(R.id.head_of_school);
        numFemaleStudents = view.findViewById(R.id.number_female_students);
        numMaleStudents = view.findViewById(R.id.number_male_students);
        numStaff = view.findViewById(R.id.number_staffs);
        numFemaleToilets = view.findViewById(R.id.number_female_toilets);
        numMaleToilets = view.findViewById(R.id.number_male_toilets);
        numStaffToilets = view.findViewById(R.id.number_staff_toilets);
        numTaps = view.findViewById(R.id.number_taps);
        numDustBin = view.findViewById(R.id.number_dust_bins);
        submit = view.findViewById(R.id.submit_button);
        apiInterface = Api.getApi().create(ApiInterface.class);
        scrollView = view.findViewById(R.id.goTop);
    }

    private void setOnClickListener(){
        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void onBackPressed() {
        if (getActivity() != null)
            getActivity().onBackPressed();
    }

    private void validate(){
        String val_school = school.getText().toString();
        String val_schoolHead = schoolHead.getText().toString();
        String val_numFemaleStudents = numFemaleStudents.getText().toString();
        String val_numMaleStudents = numMaleStudents.getText().toString();
        String val_numStaff = numStaff.getText().toString();
        String val_numFemaleToilets = numFemaleToilets.getText().toString();
        String val_numMaleToilets = numMaleToilets.getText().toString();
        String val_numStaffToilets = numStaffToilets.getText().toString();
        String val_numTaps = numTaps.getText().toString();
        String val_numDustBin = numDustBin.getText().toString();

        if (val_school.isEmpty() && val_schoolHead.isEmpty() && val_numFemaleStudents.isEmpty()
        && val_numMaleStudents.isEmpty() && val_numStaff.isEmpty() && val_numFemaleToilets.isEmpty()
        && val_numMaleToilets.isEmpty() && val_numStaffToilets.isEmpty() && val_numTaps.isEmpty()
        && val_numDustBin.isEmpty()){
            school.setError("Name of school required");
            schoolHead.setError("Name of school head required");
            numFemaleStudents.setError("Number of female students required");
            numMaleStudents.setError("Number of male students required");
            numStaff.setError("Number of staffs required");
            numFemaleToilets.setError("Number of female toilets required");
            numMaleToilets.setError("Number of male toilets required");
            numStaffToilets.setError("Number of staff toilets required");
            numTaps.setError("Number of taps required");
            numDustBin.setError("Number of dust bins required");
        }else if (val_school.isEmpty())
            school.setError("Name of school required");
        else if (val_schoolHead.isEmpty())
            schoolHead.setError("Name of school head required");
        else if (val_numFemaleStudents.isEmpty())
            numFemaleStudents.setError("Number of female students required");
        else if (val_numMaleStudents.isEmpty())
            numMaleStudents.setError("Number of male students required");
        else if (val_numStaff.isEmpty())
            numStaff.setError("Number of staffs required");
        else if (val_numFemaleToilets.isEmpty())
            numFemaleToilets.setError("Number of female toilets required");
        else if (val_numMaleToilets.isEmpty())
            numMaleToilets.setError("Number of male toilets required");
        else if (val_numStaffToilets.isEmpty())
            numStaffToilets.setError("Number of staff toilets required");
        else if (val_numTaps.isEmpty())
            numTaps.setError("Number of taps required");
        else if (val_numDustBin.isEmpty())
            numDustBin.setError("Number of dust bins required");
        else {

            setProgressDialog();
            submitData(val_school, val_schoolHead, val_numFemaleStudents, val_numMaleStudents, val_numStaff,
                    val_numFemaleToilets, val_numMaleToilets, val_numStaffToilets, val_numTaps, val_numDustBin);
        }
    }

    private void submitData(String val_school, String val_schoolHead, String val_numFemaleStudents,
        String val_numMaleStudents, String val_numStaff, String val_numFemaleToilets, String
    val_numMaleToilets, String val_numStaffToilets, String val_numTaps, String val_numDustBin) {

        Call<Users> call = apiInterface.saveToDatabase(val_school, val_schoolHead, val_numFemaleStudents,
                val_numMaleStudents, val_numStaff, val_numFemaleToilets, val_numMaleToilets,val_numStaffToilets,
                val_numTaps, val_numDustBin);

      call.enqueue(new Callback<Users>() {
          @Override
          public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
              progressDialog.dismiss();
              if (response.body() != null && response.isSuccessful()) {
                  Toast.makeText(getContext(), response.body().getResponse(), Toast.LENGTH_LONG).show();
                  clearTextFields();
              }
              else if (response.body() != null)
                  Toast.makeText(getContext(), response.body().getResponse(), Toast.LENGTH_LONG).show();
          }

          @Override
          public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {
              progressDialog.dismiss();
              Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
          }
      });
    }

    private void setProgressDialog(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Sending data...");
        progressDialog.setTitle("Please wait");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
        progressDialog.setCancelable(false);
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                progressDialog.dismiss();
            }
        }).start();
    }

    private void clearTextFields(){
        school.setText("");
        schoolHead.setText("");
        numFemaleStudents.setText("");
        numMaleStudents.setText("");
        numStaff.setText("");
        numMaleToilets.setText("");
        numFemaleToilets.setText("");
        numStaffToilets.setText("");
        numTaps.setText("");
        numDustBin.setText("");
        scrollView.fullScroll(0);
    }
}
