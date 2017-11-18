package com.example.mbienkowski.analizator.laczenie;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mbienkowski.analizator.R;
import com.example.mbienkowski.analizator.database.AccountDatabase;
import com.example.mbienkowski.analizator.database.AccountDatabaseOpenHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by mbienkowski on 2017-11-18.
 */

public class RemindPasswordActivity extends Activity implements View.OnClickListener{
    private AccountDatabaseOpenHelper accountDatabaseOpenHelper = null;
    public static EditText emailEditText;
    private Button nextButton;
    private int controlNumber = 0;

    private Dao<AccountDatabase, Long> accountDatabaseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remindpassword);

        emailEditText = (EditText) findViewById(R.id.emailEditText);
        nextButton = (Button) findViewById(R.id.nextButton);

        nextButton.setOnClickListener(this);
        try {
            accountDatabaseDao = getHelper().getDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
        private AccountDatabaseOpenHelper getHelper() {
            if (accountDatabaseOpenHelper == null) {
                accountDatabaseOpenHelper = OpenHelperManager.getHelper(this, AccountDatabaseOpenHelper.class);
            }
            return accountDatabaseOpenHelper;
        }
    protected void onDestroy(){
        super.onDestroy();

        if(accountDatabaseOpenHelper != null){
            OpenHelperManager.releaseHelper();
            accountDatabaseOpenHelper = null;
        }
    }

    public void onClick(View v){
        if(v == nextButton){
            for(AccountDatabase account : accountDatabaseDao) {
                if (account.getEmail().equals(emailEditText.getText().toString())) {
                    controlNumber = 1;
                    Intent myIntent = new Intent(v.getContext(), RemindPasswordQuestionActivity.class);
                    startActivityForResult(myIntent, 0);
                }
            }
           if(controlNumber == 0) Toast.makeText(getApplicationContext(), "Wrong E-mail", Toast.LENGTH_SHORT).show();


        }
    }

    }

