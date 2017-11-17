package com.example.mbienkowski.analizator.database.laczenie;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mbienkowski.analizator.database.ShowFileListUtil;
import com.example.mbienkowski.analizator.R;

import java.io.File;

/**
 * Created by mbienkowski on 2017-11-08.
 */


public class QuotationsListActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotationslist);
        ShowFileListUtil.listFile(new File("C:/Users/mbienkowski/Downloads/mstcgl"));
        ListView quotationsList = (ListView) findViewById(R.id.quotationsList);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.activity_quotationslist, ShowFileListUtil.quotationsTable);

        quotationsList.setAdapter(adapter);



    }
}
