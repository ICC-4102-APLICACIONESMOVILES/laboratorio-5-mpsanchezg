package com.example.mpsan.laboratorio2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.mpsan.laboratorio2.dao_access.DaoAccess;
import com.example.mpsan.laboratorio2.databases.FormDatabase;
import com.example.mpsan.laboratorio2.models.Forms;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class FormSummaryFragment extends Fragment {
    private static final String DATABASE_NAME = "forms_db";
    private FormDatabase formsDatabase;
    private List<Forms> allForms = new ArrayList<Forms>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_summary, container, false);

    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        formsDatabase = Room.databaseBuilder(view.getContext(),
                FormDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                allForms  = (List<Forms>) formsDatabase.daoAccess().getAllForms();
                final ListView formsListView = (ListView) view.findViewById(R.id.forms_summary_list);

                Handler mainHandler = new Handler(getActivity().getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        FormAdapter adapter = new FormAdapter(view.getContext(), allForms);
                        formsListView.setAdapter(adapter);
                    }
                });

            }
        }).start();
    }
}
