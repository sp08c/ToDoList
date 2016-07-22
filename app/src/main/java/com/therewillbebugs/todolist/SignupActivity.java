package com.therewillbebugs.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

public class SignupActivity extends AppCompatActivity {
    private AppCompatButton signupButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        initComponents();
    }


    private void initComponents(){
        this.signupButton = (AppCompatButton)findViewById(R.id.signup_button);

        this.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTaskActivity();
            }
        });
    }

    private void startTaskActivity(){
        this.finish();
        Intent taskIntent = new Intent(getApplicationContext(),TaskActivity.class);
        startActivity(taskIntent);
    }
}
