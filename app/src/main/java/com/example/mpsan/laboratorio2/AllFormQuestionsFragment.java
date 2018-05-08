package com.example.mpsan.laboratorio2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mpsan.laboratorio2.databases.FormDatabase;
import com.example.mpsan.laboratorio2.models.FormQuestions;
import com.example.mpsan.laboratorio2.models.Forms;

import java.util.ArrayList;
import java.util.List;


public class AllFormQuestionsFragment extends Fragment {

    private static final String DATABASE_NAME = "formquestions_db";
    private FormDatabase formQuestionsDatabase;
    private List<FormQuestions> allFormQuestions = new ArrayList<FormQuestions>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_form_questions, container, false);

    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        formQuestionsDatabase = Room.databaseBuilder(view.getContext(),
                FormDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        new Thread(new Runnable() {
            @Override
            public void run() {
                allFormQuestions  = (List<FormQuestions>) formQuestionsDatabase.daoAccess().getAllQuestions();
                final ListView questionsListView = (ListView) view.findViewById(R.id.all_questions_list);

                Handler mainHandler = new Handler(getActivity().getMainLooper());
                mainHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        QuestionsFormAdapter adapter = new QuestionsFormAdapter(view.getContext(), allFormQuestions);
                        questionsListView.setAdapter(adapter);
                    }
                });

            }
        }).start();
    }
}
