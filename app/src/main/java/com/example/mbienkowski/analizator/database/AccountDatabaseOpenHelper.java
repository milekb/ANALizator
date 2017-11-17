package com.example.mbienkowski.analizator.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.mbienkowski.analizator.R;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by mbienkowski on 2017-11-14.
 */

public class AccountDatabaseOpenHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "accountDatabase";
    private static final int DATABASE_VERSION = 1;


    private Dao<AccountDatabase, Long> accountDatabaseDao;

    public AccountDatabaseOpenHelper(Context context){
        super(context, DATABASE_NAME,null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource){
        try{
            TableUtils.createTable(connectionSource, AccountDatabase.class);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion){
        try{
            TableUtils.dropTable(connectionSource, AccountDatabase.class, false);
            onCreate(database, connectionSource);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Dao<AccountDatabase, Long> getDao() throws SQLException{
        if(accountDatabaseDao == null){
            accountDatabaseDao = getDao(AccountDatabase.class);
        }
        return accountDatabaseDao;
    }



}
