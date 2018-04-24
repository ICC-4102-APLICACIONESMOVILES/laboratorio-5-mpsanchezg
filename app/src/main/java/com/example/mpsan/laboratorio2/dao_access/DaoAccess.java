package com.example.mpsan.laboratorio2.dao_access;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.example.mpsan.laboratorio2.models.FormAnswers;
import com.example.mpsan.laboratorio2.models.FormQuestions;
import com.example.mpsan.laboratorio2.models.Forms;
import java.util.List;

/**
 * Created by mpsan on 03-04-2018.
 */

@Dao
public interface DaoAccess {

    /* Forms */
    @Insert
    void insertOnlySingleForm(Forms forms);

    @Insert
    void instertMultipleForms(List<Forms> formsList);

    @Query("SELECT * FROM Forms WHERE FormID = :FormID")
    Forms fetchOneFormsbyFormID (int FormID);

    @Query("SELECT * FROM Forms")
    List<Forms> getAllForms();

    @Update
    void updateForm (Forms forms);

    @Delete
    void deleteForm (Forms forms);

    /* Forms Questions */
    @Insert
    void instertOnlySingleFormQuestion(FormQuestions questions);

    @Insert
    void instertMultipleFormQuestions(List<FormQuestions> questionsList);

    @Query("SELECT * FROM FormQuestions WHERE QuestionID = :QuestionID")
    FormQuestions fetchOneFormQuestionsbyQuestionID (int QuestionID);

    @Query("SELECT * FROM FormQuestions")
    List<FormQuestions> getAllQuestions();

    @Update
    void updateForm (FormQuestions questions);

    @Delete
    void deleteForm (FormQuestions questions);

    /* Forms Answers */
    @Insert
    void instertOnlySingleFormAnswers(FormAnswers answers);

    @Insert
    void instertMultipleFormAnswers(List<FormAnswers> answersList);

    @Query("SELECT * FROM FormAnswers WHERE AnswID = :AnswID")
    FormAnswers fetchOneFormAnswersbyAnswID (int AnswID);

    @Query("SELECT * FROM FormAnswers")
    List<FormAnswers> getAllAnswers();

    @Update
    void updateForm (FormAnswers answers);

    @Delete
    void deleteForm (FormAnswers answers);

}
