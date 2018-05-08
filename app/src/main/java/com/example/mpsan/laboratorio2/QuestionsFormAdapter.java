package com.example.mpsan.laboratorio2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.mpsan.laboratorio2.databases.FormDatabase;
import com.example.mpsan.laboratorio2.models.FormQuestions;
import com.example.mpsan.laboratorio2.models.Forms;

import java.util.List;

/**
 * Created by mpsan on 10-04-2018.
 */

public class QuestionsFormAdapter extends ArrayAdapter<FormQuestions>{
    public QuestionsFormAdapter(Context context, List<FormQuestions> forms){
        super(context, 0, forms);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        FormQuestions formQuestions = getItem(position);

        // Database
        final String DATABASE_NAME = "formquestions_db";
        FormDatabase formQuestionsDatabase;
        formQuestionsDatabase = Room.databaseBuilder(convertView.getContext(),
                FormDatabase.class, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build();

        Forms form = formQuestionsDatabase.daoAccess().fetchOneFormsbyFormID(formQuestions.getFormId());
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.question_data, parent, false);
        }
        TextView formName = (TextView) convertView.findViewById(R.id.form_name_tv);
        // Lookup view for data population
        TextView questionText = (TextView) convertView.findViewById(R.id.question_adapter);
        TextView questionType = (TextView) convertView.findViewById(R.id.question_type_adapter);
        // Populate the data into the template view using the data object
        formName.setText(form.getFormName());
        questionText.setText(formQuestions.getQuestionText());
        questionType.setText(formQuestions.getQuestionType());
        // Return the completed view to render on screen
        return convertView;
    }


}
