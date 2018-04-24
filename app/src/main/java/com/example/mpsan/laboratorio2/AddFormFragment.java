package com.example.mpsan.laboratorio2;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mpsan.laboratorio2.databases.FormDatabase;
import com.example.mpsan.laboratorio2.models.Forms;

public class AddFormFragment extends Fragment {
    private static final String DATABASE_NAME = "forms_db";
    private FormDatabase formsDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_form, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        formsDatabase = Room.databaseBuilder(view.getContext(),
                FormDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        final EditText newFormName = (EditText) view.findViewById(R.id.name_form);
        final EditText newFormDateTime = (EditText) view.findViewById(R.id.date_form);
        final Spinner newFormCategory = (Spinner) view.findViewById(R.id.category_form);
        final EditText newFormsComments = (EditText) view.findViewById(R.id.comments_form);
        Button createFormButton = (Button) view.findViewById(R.id.new_form_button);
        createFormButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void run() {
                        Forms form = new Forms();
                        form.setFormName(newFormName.getText().toString());
                        form.setDateTime(newFormDateTime.getText().toString());
                        form.setFormSomethingElse(newFormsComments.getText().toString());
                        form.setFormCategory(newFormCategory.getSelectedItem().toString());
                        formsDatabase.daoAccess().insertOnlySingleForm(form);

                    }
                }).start();
                Toast.makeText(view.getContext(), "Haz creado un nuevo formulario :D", Toast.LENGTH_SHORT).show();
            }

        });

    }
}
