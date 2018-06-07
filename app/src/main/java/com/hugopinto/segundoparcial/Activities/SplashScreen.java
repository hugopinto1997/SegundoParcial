package com.hugopinto.segundoparcial.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.service.autofill.SaveRequest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.hugopinto.segundoparcial.R;

import org.w3c.dom.Text;

public class SplashScreen extends AppCompatActivity {

    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();


        pref = getSharedPreferences("Preferencias", Context.MODE_PRIVATE);

        Intent loginredirect = new Intent(this,Login.class);
        Intent mainredirect = new Intent(this, MainActivity.class);

        if(TextUtils.isEmpty(pref.getString("usuario","")) &&
                TextUtils.isEmpty(pref.getString("password",""))
        && TextUtils.isEmpty(pref.getString("Token",""))){
            loginredirect.setFlags(loginredirect.FLAG_ACTIVITY_NEW_TASK | loginredirect.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(loginredirect);
        }else {
            mainredirect.setFlags(mainredirect.FLAG_ACTIVITY_NEW_TASK | mainredirect.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(mainredirect);
        }



    }
}
