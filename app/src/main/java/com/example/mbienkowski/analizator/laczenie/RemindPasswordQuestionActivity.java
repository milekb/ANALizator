package com.example.mbienkowski.analizator.laczenie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class RemindPasswordQuestionActivity extends Activity implements View.OnClickListener {

    private AccountDatabaseOpenHelper accountDatabaseOpenHelper = null;
    private TextView questionTextView;
    private EditText answerEditText;
    private Button remindButton;

    private Dao<AccountDatabase, Long> accountDatabaseDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remindpassword_question);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        answerEditText = (EditText) findViewById(R.id.answerEditText);
        remindButton = (Button) findViewById(R.id.remindButton);

        remindButton.setOnClickListener(this);
        try {
            accountDatabaseDao = getHelper().getDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        for (AccountDatabase account : accountDatabaseDao) {
            if (account.getEmail().equals(RemindPasswordActivity.emailEditText.getText().toString())) {
                questionTextView.setText(account.getQuestion());
            }

        }
    }

    private AccountDatabaseOpenHelper getHelper() {
        if (accountDatabaseOpenHelper == null) {
            accountDatabaseOpenHelper = OpenHelperManager.getHelper(this, AccountDatabaseOpenHelper.class);
        }
        return accountDatabaseOpenHelper;
    }

    protected void onDestroy() {
        super.onDestroy();

        if (accountDatabaseOpenHelper != null) {
            OpenHelperManager.releaseHelper();
            accountDatabaseOpenHelper = null;
        }
    }

    public void onClick(View v) {
        if (v == remindButton) {
            for (AccountDatabase account : accountDatabaseDao) {
                if (account.getAnswer().equals(answerEditText.getText().toString())) {
                    Intent myIntent = new Intent(v.getContext(), RemindPasswordMessageActivity.class);
                    startActivityForResult(myIntent, 0);
                } else {
                    Toast.makeText(getApplicationContext(), "Wrong answer!", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
