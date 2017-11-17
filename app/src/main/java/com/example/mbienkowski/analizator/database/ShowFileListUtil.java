package com.example.mbienkowski.analizator.database;

/**
 * Created by mbienkowski on 2017-11-08.
 */

import com.example.mbienkowski.analizator.R;

import java.io.File;

public class ShowFileListUtil {
    public final static String[] quotationsTable = new String[1422];
    private static String sep = "";
    private static int i = 0;
    public static void listFile(File file) {
        ShowFileListUtil.sep +=".";
        if (file.isDirectory()) {
            File[] fileList = file.listFiles();
            //System.out.println(ShowFileListUtil.sep + file.getName() + " G");
            for (int i = 0; i < fileList.length; i++){
                ShowFileListUtil.listFile(fileList[i]);
                //quotationsTable[i] = (ShowFileListUtil.sep + file.getName() + " D").toString();
            }
        } else {
            //System.out.println(ShowFileListUtil.sep + file.getName() + " F");
            quotationsTable[i] = file.getName().substring(0, file.getName().length() - 4);
            i++;
        }
        ShowFileListUtil.sep = ShowFileListUtil.sep.substring(0, ShowFileListUtil.sep.lastIndexOf('.'));
    }
    public static void main(String[] args){
        ShowFileListUtil.listFile(new File("/app/src/main/res/raw/"));

        for(int j = 0; j<1422; j++){
            System.out.println(quotationsTable[j]);
        }
    }
}