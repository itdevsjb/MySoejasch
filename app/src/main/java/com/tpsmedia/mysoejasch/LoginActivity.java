package com.tpsmedia.mysoejasch;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.tpsmedia.mysoejasch.api.Client;
import com.tpsmedia.mysoejasch.api.Interface;
import com.tpsmedia.mysoejasch.model.Auth.Login;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button b1;
    EditText ed1,ed2;
    Interface mApiInterface;
    long back_pressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        b1 = (Button)findViewById(R.id.btnLogin);
        ed1 = (EditText)findViewById(R.id.textUsername);
        ed2 = (EditText)findViewById(R.id.textPassword);

        mApiInterface = Client.getClient().create(Interface.class);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),
                        "Mohon tunggu...",Toast.LENGTH_SHORT).show();

                FirebaseMessaging.getInstance().getToken()
                        .addOnCompleteListener(new OnCompleteListener<String>() {
                            @Override
                            public void onComplete(@NonNull Task<String> task) {
                                if (!task.isSuccessful()) {
                                    System.out.println("Fetching FCM registration token failed");
                                    return;
                                }
                                String tokenfirebase = task.getResult();

                                Call<Login> postLoginExe = mApiInterface.postLogin(ed1.getText().toString(), ed2.getText().toString(), tokenfirebase);
                                postLoginExe.enqueue(new Callback<Login>() {
                                    @Override
                                    public void onResponse(Call<Login> call, Response<Login> response) {
                                        if(response.isSuccessful()){

                                            Toast.makeText(getApplicationContext(),
                                                    "Login berhasil",Toast.LENGTH_SHORT).show();

                                            SharedPreferences sgSharedPref = getApplicationContext().getSharedPreferences("data_mysoejasch", getApplicationContext().MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sgSharedPref.edit();
                                            String token = String.valueOf(response.body().getToken());
                                            String userlogin = String.valueOf(response.body().getNama());
                                            String ucodelogin = String.valueOf(response.body().getUcode());
                                            String prLevel = String.valueOf(response.body().getPrLevel());
                                            String poLevel = String.valueOf(response.body().getPoLevel());
                                            String warehouseLevel = String.valueOf(response.body().getWarehouseLevel());
                                            String username = String.valueOf(response.body().getUsername());
                                            String downloaddata = sgSharedPref.getString("downloaddata", "0");

                                            editor.putString("token", token);
                                            editor.putString("Konv", "1");
                                            editor.putString("lokasiscan", "");
                                            editor.putString("infouser", userlogin);
                                            editor.putString("prLevel", prLevel);
                                            editor.putString("poLevel", poLevel);
                                            editor.putString("warehouseLevel", warehouseLevel);
                                            editor.putString("ucodeuser", ucodelogin);
                                            editor.putString("username", username);
                                            editor.putString("downloaddata", downloaddata);
                                            editor.apply();


                                            startActivity(new Intent(LoginActivity.this, SplashActivity.class));
                                        }else{
                                            Toast.makeText(getApplicationContext() ,"Login gagal",Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Login> call, Throwable t) {
                                        /*Log.v("log softgain : ", String.valueOf(t));*/
                                        Toast.makeText(getApplicationContext(), String.valueOf(t), Toast.LENGTH_LONG).show();
                                    }



                                });

                            }});


            }




        });




    }
}