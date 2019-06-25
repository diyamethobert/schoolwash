package com.sanga.schoolwash.UI;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sanga.schoolwash.Database.Api;
import com.sanga.schoolwash.Database.ApiInterface;
import com.sanga.schoolwash.Database.Users;
import com.sanga.schoolwash.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterFragment extends Fragment {

    private ApiInterface apiInterface;
    private EditText name, email, password;
    private Button registerButton;
    private ProgressDialog progressDialog;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        initialize(view);
        setListener();
        return view;
    }

    private void initialize(View view) {
        apiInterface = Api.getApi().create(ApiInterface.class);
        name = view.findViewById(R.id.id_full_name);
        email = view.findViewById(R.id.id_email_register);
        password = view.findViewById(R.id.id_password_register);
        registerButton = view.findViewById(R.id.register_button);
    }

    private void setListener(){
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void progressDialog(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Registering...");
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

    private void validate() {
        String valName = name.getText().toString();
        String valEmail = email.getText().toString();
        String valPass = password.getText().toString();

        if (valName.isEmpty() && valEmail.isEmpty() && valPass.isEmpty()){
            name.setError("Full name required");
            email.setError("Email address required");
            password.setError("Password required");
        }
        else if (valName.isEmpty()){
            name.setError("Full name required");
        }
        else if (valPass.isEmpty()){
            password.setError("Password required");
        }
        else if (valEmail.isEmpty()){
            email.setError("Email address required");
        }
        else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(valEmail).matches()){
            email.setError("Email address is not valid");
        }else {
            progressDialog();
            register(valName, valEmail, valPass);
        }
    }

    public void register(String name, String email, String password) {
        Call<Users> call = apiInterface.register(name, email, password);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                progressDialog.dismiss();
                if (response.isSuccessful() && response.body() != null){
                    if (response.body().getResponse().contains("Registered successful")){
                        if (getActivity() != null)
                            getActivity().onBackPressed();
                    }else Toast.makeText(getContext(),response.body().getResponse(), Toast.LENGTH_LONG).show();
                }else{
                    if (response.body() != null)
                        Toast.makeText(getContext(),  response.body().getResponse(),
                                Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


}
