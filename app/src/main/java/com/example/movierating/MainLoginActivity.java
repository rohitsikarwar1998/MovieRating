package com.example.movierating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainLoginActivity extends AppCompatActivity {


    private EditText username;
    private EditText password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();

        checkSession();

    }

    private void checkSession(){
        SessionManagement sessionManagement=new SessionManagement(this);
        String userName=sessionManagement.getSession();

        if(!userName.equals("")){
            //It means user already loged in
            moveToNextActivity();
        }
        else {
            username=findViewById(R.id.username_edit_text);
            password=findViewById(R.id.password_edit_text);
        }
    }

    public void Login(View view) {
        // 1.after clicking this button we have to save session
        // 2.move to next activity

        //1.create user

        String userName=username.getText().toString().trim();
        String userPassword=password.getText().toString().trim();

        User user =new User(userName,userPassword);

        SessionManagement sessionManagement=new SessionManagement(this);
        sessionManagement.saveSession(user);

        moveToNextActivity();

    }

    public void moveToNextActivity(){
        Intent intent =new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
