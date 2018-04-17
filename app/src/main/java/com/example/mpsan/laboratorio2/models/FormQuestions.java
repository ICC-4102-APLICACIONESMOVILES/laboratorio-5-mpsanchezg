package com.example.mpsan.laboratorio2.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by mpsan on 10-04-2018.
 */

@Entity
public class FormQuestions {
    @PrimaryKey(autoGenerate = true)
    private int QuestionID;
    private String questionText;
    private String questionType;
    @ForeignKey(entity = Forms.class, parentColumns = "FormID", childColumns = "FormID")
    private int FormId;

    public FormQuestions(){

    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public void setFormId(int formId) {
        FormId = formId;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getQuestionType() {
        return questionType;
    }

    public int getFormId() {
        return FormId;
    }

}
