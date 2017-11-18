package com.example.mbienkowski.analizator.laczenie;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mbienkowski.analizator.R;
import com.example.mbienkowski.analizator.database.AccountDatabaseOpenHelper;
import com.example.mbienkowski.analizator.database.AccountDatabase;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

/**
 * Created by mbienkowski on 2017-11-16.
 */

public class AddAccountActivity extends Activity implements OnClickListener{
    private AccountDatabaseOpenHelper accountDatabaseOpenHelper = null;
    private EditText loginEditText, passwordEditText, emailEditText, questionEditText, answerEditText;
    private Button resetButton, submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_account);

        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        questionEditText = (EditText) findViewById(R.id.questionEditText);
        answerEditText = (EditText) findViewById(R.id.answerEditText);


        resetButton = (Button) findViewById(R.id.resetButton);
        submitButton = (Button) findViewById(R.id.submitButton);

        resetButton.setOnClickListener(this);
        submitButton.setOnClickListener(this);
    }

    private AccountDatabaseOpenHelper getHelper(){
        if(accountDatabaseOpenHelper == null){
            accountDatabaseOpenHelper = OpenHelperManager.getHelper(this, AccountDatabaseOpenHelper.class);
        }
        return accountDatabaseOpenHelper;
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();

        if(accountDatabaseOpenHelper != null){
            OpenHelperManager.releaseHelper();
            accountDatabaseOpenHelper = null;
        }
    }

    @Override
    public void onClick(View v){
        if(v == submitButton){
            if(loginEditText.getText().toString().trim().length() > 0 &&
                    passwordEditText.getText().toString().trim().length() > 0 &&
                    emailEditText.getText().toString().trim().length() > 0 &&
                    questionEditText.getText().toString().trim().length() > 0 &&
                    answerEditText.getText().toString().trim().length() > 0 ){

                final AccountDatabase accountBase = new AccountDatabase();

                accountBase.setLogin(loginEditText.getText().toString());
                accountBase.setPassword(passwordEditText.getText().toString());
                accountBase.setEmail(emailEditText.getText().toString());
                accountBase.setQuestion(questionEditText.getText().toString());
                accountBase.setAnswer(answerEditText.getText().toString());

                try{
                   final Dao<AccountDatabase, Long> accountDatabaseDao = getHelper().getDao();
                   accountDatabaseDao.create(accountBase);
                   reset();
                   Toast.makeText(getApplicationContext(), "Your account added successfully! ", Toast.LENGTH_SHORT).show();

                } catch (SQLException e){
                    e.printStackTrace();
                }

            }
            else{
                Toast.makeText(getApplicationContext(), "All fields are mandatory! ", Toast.LENGTH_SHORT).show();
            }
        }
        else if(v == resetButton){
            reset();
        }
    }

    private void showMessageDialog(final String message){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(message);
        final AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    private void reset(){
        loginEditText.setText("");
        passwordEditText.setText("");
        emailEditText.setText("");
        questionEditText.setText("");
        answerEditText.setText("");
    }



}
