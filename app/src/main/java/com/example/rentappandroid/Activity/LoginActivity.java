package com.example.rentappandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Request.Add.Login;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiLandrod;
import com.example.rentappandroid.api.ApiTenant;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView registerTextView;
    private Button loginButton;
    private String role = "";
    private RadioGroup radioGroup;
    private RadioButton radioButtonTenant;
    private RadioButton radioButtonLandlord;

    private  void init(){
        emailEditText = findViewById(R.id.email_login);
        passwordEditText = findViewById(R.id.password_login);
        registerTextView = findViewById(R.id.register);
        loginButton = findViewById(R.id.button_login);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonTenant = findViewById(R.id.radioButtonTenant);
        radioButtonLandlord = findViewById(R.id.radioButtonLandlord);
    }

    private void event(){
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = emailEditText.getText().toString();
                String pass = passwordEditText.getText().toString();
                Login login = new Login(email,pass);
                if (radioButtonTenant.isChecked()) {
                    role = "USER";
                } else if (radioButtonLandlord.isChecked()) {
                    role = "ADMIN";
                }

                if(role.equals("ADMIN")){
                    ApiLandrod.apiLandrod.login(login).enqueue(new Callback<Owner>() {
                        @Override
                        public void onResponse(Call<Owner> call, Response<Owner> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();

                                // Save the access token and phone number
                                editor.putString("token", "Bearer " + response.body().getToken());
                                editor.putString("sdt", response.body().get_id());
                                editor.putString("name", response.body().getName());
                                editor.putString("role", response.body().getRole());
                                editor.apply();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }


                        }
                        @Override
                        public void onFailure(Call<Owner> call, Throwable t) {
                            Log.e("API Request", "Failed", t);
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else {
                    ApiTenant.apiTenant.login(login).enqueue(new Callback<Owner>() {
                        @Override
                        public void onResponse(Call<Owner> call, Response<Owner> response) {
                            if (response.isSuccessful() && response.body() != null) {
                                SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = preferences.edit();

                                // Save the access token and phone number
                                editor.putString("token", "Bearer " + response.body().getToken());
                                editor.putString("sdt", response.body().get_id());
                                editor.putString("name", response.body().getName());
                                editor.putString("role", response.body().getRole());

                                editor.putString("address", response.body().getAddress());
                                editor.apply();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                // Handle the unsuccessful response
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(LoginActivity.this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Owner> call, Throwable t) {
                            Log.e("API Request", "Failed", t);

                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(LoginActivity.this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }





            }
        });
    }

    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = findViewById(R.id.progressBar);

        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);
        if (preferences.contains("token") && preferences.contains("sdt")) {
            // Proceed to start MainActivity
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        init();
        event();

    }



}