package com.example.mpsan.laboratorio2;

import android.app.Application;
import android.arch.persistence.room.Room;

import com.example.mpsan.laboratorio2.databases.FormDatabase;


/**
 * Created by mpsan on 03-04-2018.
 */

public class MainApplication extends Application {
    private static final String DATABASE_NAME = "forms_db";
    public FormDatabase formsDatabase;

    @Override
    public  void onCreate(){
        super.onCreate();

        formsDatabase = Room.databaseBuilder(getApplicationContext(),
                FormDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

    }
}
