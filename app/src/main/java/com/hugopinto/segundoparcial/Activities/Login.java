package com.hugopinto.segundoparcial.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hugopinto.segundoparcial.APIs.DecryptToken;
import com.hugopinto.segundoparcial.APIs.GameNewsAPI;
import com.hugopinto.segundoparcial.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.hugopinto.segundoparcial.APIs.user;

import java.net.SocketTimeoutException;

public class Login extends AppCompatActivity {

    private EditText name, pass;
    private Button btn;
    public String names, passw;

    private SharedPreferences preferencies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        name = findViewById(R.id.email);
        pass = findViewById(R.id.password);
        btn = findViewById(R.id.email_sign_in_button);


        preferencies = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

       btn.setOnClickListener(new View.OnClickListener(){
           @Override
           public void onClick(View v){
               if(name.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                   Toast.makeText(getApplicationContext(),"Complete los campos", Toast.LENGTH_SHORT).show();
               }else{
                   names=name.getText().toString();
                   passw = pass.getText().toString();
                   Gson gson = new GsonBuilder().registerTypeAdapter(String.class,new DecryptToken()).create();
                   Retrofit.Builder builder = new Retrofit.Builder().baseUrl(GameNewsAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create(gson));
                   Retrofit retrofit= builder.build();
                   GameNewsAPI gameNewsAPI= retrofit.create(GameNewsAPI.class);
                   final user usr= new user(name.getText().toString(), pass.getText().toString());
                   Call<String> c = gameNewsAPI.login(usr.getUsuario(), usr.getPass());
                   c.enqueue(new Callback<String>() {
                       @Override
                       public void onResponse(Call<String> call, Response<String> response) {
                           if (response.isSuccessful() && !response.body().equals("")){
                               Toast.makeText(getApplicationContext(), "Inició Sesion como: "+names, Toast.LENGTH_SHORT).show();
                               final String tok=response.body();
                               SaveSharedPrefs(names,passw,tok);
                               Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                               startActivity(intent);
                               finish();
                           }
                       }

                       @Override
                       public void onFailure(Call<String> call, Throwable t) {
                           if(t instanceof SocketTimeoutException){
                               Toast.makeText(getApplicationContext(),"Error, no puede conectarse", Toast.LENGTH_SHORT).show();
                           }
                           else{
                               Toast.makeText(getApplicationContext(),"Error, no puede conectarse", Toast.LENGTH_SHORT).show();
                           }

                       }
                   });

               }
           }
       });
    }

    public void SaveSharedPrefs(String usuariosp,String passwrd, String toke){
            SharedPreferences.Editor editor= preferencies.edit();
            editor.putString("usuario", usuariosp);
            editor.putString("password", passwrd);
            editor.putString("Token",toke);
            editor.apply();
    }

    public void iniciosesion(){
        Gson gson = new GsonBuilder().registerTypeAdapter(String.class,new DecryptToken()).create();
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(GameNewsAPI.ENDPOINT).addConverterFactory(GsonConverterFactory.create(gson));
        Retrofit retrofit= builder.build();
        GameNewsAPI gameNewsAPI= retrofit.create(GameNewsAPI.class);
        final user usr= new user(name.getText().toString(), pass.getText().toString());
        Call<String> c = gameNewsAPI.login(usr.getUsuario(), usr.getPass());
        c.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful() && !response.body().equals("")){
                    Toast.makeText(getApplicationContext(), "Inició Sesion como: "+usr.getUsuario(), Toast.LENGTH_SHORT).show();
                    usr.setToken(response.body());
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
