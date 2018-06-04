package com.hugopinto.segundoparcial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hugopinto.segundoparcial.R;

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
                   Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                   startActivity(intent);
                   finish();
               }
           }
       });
    }
}
