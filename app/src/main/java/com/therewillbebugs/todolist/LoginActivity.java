package com.therewillbebugs.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.view.View;

/**
 * Created by Michael on 7/20/2016.
 */
public class LoginActivity extends AppCompatActivity {
    private AppCompatButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);

        initComponents();
    }


    private void initComponents(){
        this.loginButton = (AppCompatButton)findViewById(R.id.login_btn_login);

        this.loginButton.setOnClickListener(new View.OnClickListener() {
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
