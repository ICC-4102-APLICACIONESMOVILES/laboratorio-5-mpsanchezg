package com.example.mpsan.laboratorio2.databases;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.example.mpsan.laboratorio2.dao_access.DaoAccess;
import com.example.mpsan.laboratorio2.models.FormAnswers;
import com.example.mpsan.laboratorio2.models.FormQuestions;
import com.example.mpsan.laboratorio2.models.Forms;

/**
 * Created by mpsan on 03-04-2018.
 */
@Database(entities = {Forms.class, FormQuestions.class, FormAnswers.class}, version = 1, exportSchema = false)
public abstract class FormDatabase extends RoomDatabase {
    public abstract DaoAccess daoAccess();
}
