package com.example.mbienkowski.analizator.laczenie;

import android.accounts.Account;
import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mbienkowski.analizator.R;
import com.example.mbienkowski.analizator.database.AccountDatabase;
import com.example.mbienkowski.analizator.database.AccountDatabaseOpenHelper;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.Where;

import java.sql.SQLException;
import java.util.List;

public class MainActivity extends Activity implements View.OnClickListener{

    static {
        System.loadLibrary("native-lib");
    }

    private AccountDatabaseOpenHelper accountDatabaseOpenHelper = null;
    private int selectedRecordNumberPosition = -1;
    private EditText loginEditText, passwordEditText;
    private Button loginButton, remindButton, registerButton;
    private int controlNumber;

    private Dao<AccountDatabase, Long> accountDatabaseDao;







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginEditText = (EditText) findViewById(R.id.loginEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginButton = (Button) findViewById(R.id.loginButton);
        remindButton = (Button) findViewById(R.id.remindPasswordButton);
        registerButton = (Button) findViewById(R.id.registerButton);

        loginButton.setOnClickListener(this);
        remindButton.setOnClickListener(this);
        registerButton.setOnClickListener(this);
        try{
            accountDatabaseDao = getHelper().getDao();
            AccountDatabaseActivity();

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    private AccountDatabaseOpenHelper getHelper() {
        if (accountDatabaseOpenHelper == null) {
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
        if(v == loginButton) {
            for (AccountDatabase account : accountDatabaseDao) {
                if(account.getLogin().equals(loginEditText.getText().toString())) {
                    controlNumber = 1;
                    for (AccountDatabase account2 : accountDatabaseDao) {
                        if (account.getPassword().equals(passwordEditText.getText().toString())) {
                            Intent myIntent = new Intent(v.getContext(), MainPageActivity.class);
                            startActivityForResult(myIntent, 0);
                        } else controlNumber = 0;
                    }
                }
                else controlNumber = 0;

                }

           if(controlNumber == 0) Toast.makeText(getApplicationContext(), "Bad login or password ", Toast.LENGTH_SHORT).show();

        }
        else if(v == registerButton){
            Intent myIntent = new Intent(v.getContext(), AddAccountActivity.class);
            startActivityForResult(myIntent, 0);
        }

        else if(v == remindButton){
            Intent myIntent = new Intent(v.getContext(), RemindPasswordActivity.class);
            startActivityForResult(myIntent,0);
        }
    }
    private void AccountDatabaseActivity() throws SQLException {
        AccountDatabaseOpenHelper accountDatabaseOpenHelper = OpenHelperManager.getHelper(this, AccountDatabaseOpenHelper.class);

        Dao<AccountDatabase, Long> accountDatabaseDao = accountDatabaseOpenHelper.getDao();

        accountDatabaseDao.create(new AccountDatabase("mbienkowski", "milosz", "milosz_bienkowski@vp.pl","Imię mamy?", "Gabrysia"));
        accountDatabaseDao.create(new AccountDatabase("jkowalski", "jan", "jan_kowalski@vp.pl","Imię ojca?","Bogdan"));
        accountDatabaseDao.create(new AccountDatabase("tnowak", "tadeusz", "tadeusz_kowalski@vp.pl","Nazwa pupila?", "Kiciuś"));
        accountDatabaseDao.create(new AccountDatabase("akowalczyk", "andrzej", "andrzej_kowalczyk@vp.pl", "Ostatnia dziewczyna?", "Kaja"));

        List<AccountDatabase> accountDatabases = accountDatabaseDao.queryForAll();
    }
}


