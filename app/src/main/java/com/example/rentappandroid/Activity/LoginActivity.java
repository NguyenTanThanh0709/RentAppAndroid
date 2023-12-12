package com.example.rentappandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Request.Schema.Login;
import com.example.rentappandroid.R;
import com.example.rentappandroid.api.ApiLandrod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText emailEditText;
    private EditText passwordEditText;
    private TextView registerTextView;
    private Button loginButton;

    private  void init(){
        emailEditText = findViewById(R.id.email_login);
        passwordEditText = findViewById(R.id.password_login);
        registerTextView = findViewById(R.id.register);
        loginButton = findViewById(R.id.button_login);
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
                String email = emailEditText.getText().toString();
                String pass = passwordEditText.getText().toString();
                Login login = new Login(email,pass);

                ApiLandrod.apiLandrod.login(login).enqueue(new Callback<Owner>() {
                    @Override
                    public void onResponse(Call<Owner> call, Response<Owner> response) {
                        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();

                        // Save the access token and phone number
                        editor.putString("token", "Bearer " + response.body().getToken());
                        editor.putString("sdt", response.body().get_id());
                        editor.putString("name", response.body().getName());
                        editor.putString("role", response.body().getRole());
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));

                    }
                    @Override
                    public void onFailure(Call<Owner> call, Throwable t) {

                    }
                });

            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        SharedPreferences preferences = getSharedPreferences("Owner", Context.MODE_PRIVATE);
        if (preferences.contains("token") && preferences.contains("sdt")) {
            // Proceed to start MainActivity
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }
        init();
        event();


    }
}