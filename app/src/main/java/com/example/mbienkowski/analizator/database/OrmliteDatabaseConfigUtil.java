package com.example.mbienkowski.analizator.database;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import static com.j256.ormlite.android.apptools.OrmLiteConfigUtil.writeConfigFile;

/**
 * Created by mbienkowski on 2017-11-15.
 */

public class OrmliteDatabaseConfigUtil extends OrmLiteConfigUtil {
    private static final Class<?>[] classes = new Class[] {AccountDatabase.class};


    public static void main(String[] args) throws IOException, SQLException{

        String currDirectory = "user.dir";
        String configPath = "/app/src/main/res/raw/ormlite_config.txt";
        String projectRoot = System.getProperty(currDirectory);
        String fullConfigPath = projectRoot + configPath;
        File configFile = new File(fullConfigPath);

        if(configFile.exists()){
            configFile.delete();
            configFile = new File(fullConfigPath);
        }

        writeConfigFile(configFile,classes);
    }

}
