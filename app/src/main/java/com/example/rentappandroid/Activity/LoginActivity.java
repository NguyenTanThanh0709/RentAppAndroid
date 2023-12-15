package com.example.rentappandroid.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rentappandroid.Dto.Reponse.Owner;
import com.example.rentappandroid.Dto.Request.Schema.Login;
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
                                // Handle the unsuccessful response
                            }


                        }
                        @Override
                        public void onFailure(Call<Owner> call, Throwable t) {
                            Log.e("API Request", "Failed", t);
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
                                editor.apply();
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            } else {
                                // Handle the unsuccessful response
                                Toast.makeText(LoginActivity.this, "Login failed. Please check your credentials.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        @Override
                        public void onFailure(Call<Owner> call, Throwable t) {
                            Log.e("API Request", "Failed", t);
                        }
                    });
                }





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
        showRoleSelectionDialog();
    }

    private void showRoleSelectionDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Bạn Là Ai?")
                .setItems(new CharSequence[]{"Người Cho Thuê", "Người Đi Tìm Phòng"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle the selected role
                        switch (which) {
                            case 0:
                                role = "ADMIN";
                                Toast.makeText(LoginActivity.this, "Người Cho Thuê", Toast.LENGTH_SHORT).show();
                                break;
                            case 1:
                                // Người Đi Tìm Phòng
                                // Implement your logic here
                                role = "USER";
                                Toast.makeText(LoginActivity.this, "Người Đi Tìm Phòng", Toast.LENGTH_SHORT).show();
                                break;
                        }
                        // Dismiss the dialog only if a role is selected
                        dialog.dismiss();
                    }
                });

        // Set a listener to handle the dialog cancellation
        builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                showRoleSelectionDialog();
                // Handle the case when the user cancels the dialog without selecting a role
                // You can display a message or take appropriate action
                Toast.makeText(LoginActivity.this, "Vui lòng chọn một vai trò", Toast.LENGTH_SHORT).show();
            }
        });

        builder.create().show();
    }
}