package com.example.mpsan.laboratorio2.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mpsan on 10-04-2018.
 */

@Entity
public class FormAnswers {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int AnswID;
    private String answToQuestion;
    @ForeignKey(entity = FormQuestions.class, parentColumns = "QuestionID", childColumns = "QuestionID")
    private int QuestionID;

    public FormAnswers() {

    }

    @NonNull
    public int getAnswID() {
        return AnswID;
    }

    public String getAnswToQuestion() {
        return answToQuestion;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setAnswID(@NonNull int answID) {
        AnswID = answID;
    }

    public void setAnswToQuestion(String answToQuestion) {
        this.answToQuestion = answToQuestion;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }
}
