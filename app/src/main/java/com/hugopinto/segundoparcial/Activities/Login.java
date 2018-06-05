package com.hugopinto.segundoparcial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hugopinto.segundoparcial.Classes.DecryptToken;
import com.hugopinto.segundoparcial.Classes.Game;
import com.hugopinto.segundoparcial.Interfaces.GameNewsAPI;
import com.hugopinto.segundoparcial.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.hugopinto.segundoparcial.Interfaces.user;

import java.net.SocketTimeoutException;

public class Login extends AppCompatActivity {

    EditText name, pass;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        name = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        btn = findViewById(R.id.email_sign_in_button);

       btn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               if(name.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                   Toast.makeText(getApplicationContext(),"Complete los campos", Toast.LENGTH_SHORT).show();
               }else{
                   iniciosesion();
               }
           }
       });
    }
    public void iniciosesion(){
        Gson gson = new GsonBuilder().registerTypeAdapter(String.class,new DecryptToken()).create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(GameNewsAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit= builder.build();
        GameNewsAPI gameNewsAPI= retrofit.create(GameNewsAPI.class);
        user usr= new user(name.getText().toString(), pass.getText().toString());
        Call<String> c = gameNewsAPI.login(usr.getUsuario(), usr.getPass());
        c.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && !response.body().equals("")){
                    Toast.makeText(getApplicationContext(), response.body().toString(), Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Sin Respuesta", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                if(t instanceof SocketTimeoutException){
                    Toast.makeText(getApplicationContext(), "false", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(getApplicationContext(), "si puso pero no existe", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
