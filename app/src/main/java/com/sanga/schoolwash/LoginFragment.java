package com.sanga.schoolwash;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginFragment extends Fragment {

    private EditText email, password;
    private Button loginButton;
    private ScrollView scrollView;
    private ProgressDialog progressDialog;
    private ApiInterface apiInterface;
    private static final String TAG = "TAG";


    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        initialize(view);
        setListener();
        return view;
    }

    private void initialize(View view) {
        email = view.findViewById(R.id.id_email);
        password = view.findViewById(R.id.id_password);
        loginButton = view.findViewById(R.id.login_button);
        apiInterface = Api.getApi().create(ApiInterface.class);
        scrollView = view.findViewById(R.id.scroll_view);
    }

    private void setListener(){
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });
    }

    private void progressDialog(){
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Login...");
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

    private void validate(){
        String valEmail = email.getText().toString();
        String valPass = password.getText().toString();

        if (valEmail.isEmpty() && valPass.isEmpty()){
            email.setError("Email address required");
            password.setError("Password required");
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
            login(valEmail, valPass);
        }
    }

    private void login(final String valEmail, String valPass) {

        Call<Users> call = apiInterface.getLogin(valEmail, valPass);
        call.enqueue(new Callback<Users>() {
            @Override
            public void onResponse(@NonNull Call<Users> call, @NonNull Response<Users> response) {
                progressDialog.dismiss();
                if (response.body() != null)
                if (response.body().getResponse().contains("success")){
                    goHomePage(valEmail);
                    Log.d(TAG, "onResponse: " + valEmail);
                }else
                    Toast.makeText(getContext(), response.body().getResponse(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(@NonNull Call<Users> call, @NonNull Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

    }

    private void goHomePage(String valEmail){
        Bundle bundle = new Bundle();
        bundle.putString("email", valEmail);
        NavController navigation = Navigation.findNavController(scrollView);
        navigation.popBackStack();
        navigation.navigate(R.id.homeFragment, bundle);
    }

}
