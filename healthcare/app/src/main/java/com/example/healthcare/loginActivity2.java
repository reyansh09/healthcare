package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class loginActivity2 extends AppCompatActivity {

    EditText edUsername, edPassword;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        edUsername = findViewById(R.id.editTextLoginUsername);
        edPassword = findViewById(R.id.editTextLoginPassword);
        btn = findViewById(R.id.buttonLogin);
        tv = findViewById(R.id.textViewNewUser);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent( loginActivity2.this,HomeActivity2.class));
                String username = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"healthcare",null,1);
                if( username.length()==0 || password.length()==0){
                    Toast.makeText(getApplicationContext() ,"Please Fill all the Detail", Toast.LENGTH_SHORT).show();
                }else {
                    if(db.login(username,password)==1){
                        Toast.makeText(getApplicationContext() ,"Login Success", Toast.LENGTH_SHORT).show();
                        SharedPreferences SharedPreferences= getSharedPreferences("shared prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor= SharedPreferences.edit();
                        editor.putString("username ",username);
                        //to save our data with key and value
                        editor.apply();
                        startActivity(new Intent( loginActivity2.this,HomeActivity2.class));
                    }else{
                        Toast.makeText(getApplicationContext() ,"Invalid username and password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent( loginActivity2.this,RegisterActivity2.class));
            }
        });
    }
}