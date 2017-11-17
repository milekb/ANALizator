package com.example.mbienkowski.analizator.database;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by mbienkowski on 2017-11-17.
 */

//@DatabaseTable(tableName = ShowFileListUtil.quotationsTable[1])

public class QuotationsDatabase {
    @DatabaseField(generatedId=true)
    private long id;

    @DatabaseField
    private String Date;
}
