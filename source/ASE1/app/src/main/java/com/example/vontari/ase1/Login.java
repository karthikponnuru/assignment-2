package com.example.vontari.ase1;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Button LoginBut;
    EditText editText,editText2;
    TextView CredError;
    String username;
    String password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        LoginBut=(Button)findViewById(R.id.LoginBut);
        LoginBut.setOnClickListener(this);
        CredError = (TextView)findViewById(R.id.CredError);
        editText = (EditText)findViewById(R.id.editText);
        editText2 = (EditText)findViewById(R.id.editText2);
        username = editText.getText().toString();
        password = editText2.getText().toString();
        //Log.d("up",username + " " + password + " " + edi.getText().toString());
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.LoginBut:
                editText = (EditText)findViewById(R.id.editText);
                editText2 = (EditText)findViewById(R.id.editText2);
                username = editText.getText().toString();
                password = editText2.getText().toString();
                if(username.equals("123456")&& password.equals("123456")) {
                    Intent redirect = new Intent(Login.this, Weather.class);
                    startActivity(redirect);
                }
                else
                {
                    CredError.setVisibility(View.VISIBLE);
                }

                break;
                    }

        }
    }


