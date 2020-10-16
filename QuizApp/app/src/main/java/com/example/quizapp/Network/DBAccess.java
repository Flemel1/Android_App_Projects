package com.example.quizapp.Network;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.quizapp.Model.Question;

import java.util.ArrayList;
import java.util.Collections;

public class DBAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DBAccess instance;
    private final String TABLE_QUESTION = "question";

    private DBAccess(Context context){
        this.openHelper = new DBHelper(context);
    }

    public static DBAccess getInstance(Context context){
        if (instance == null){
            instance = new DBAccess(context);
        }
        return instance;
    }

    public void open(){
        this.database = openHelper.getWritableDatabase();
    }

    public void close(){
        if (database != null) {
            this.database = null;
        }
    }

    public ArrayList<Question> getQuestion(){
        ArrayList<Question> list_question = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM question", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Question question = new Question();
            question.setNumber(cursor.getInt(0));
            question.setQuestion(cursor.getString(1));
            question.setOptionA(cursor.getString(2));
            question.setOptionB(cursor.getString(3));
            question.setOptionC(cursor.getString(4));
            question.setOptionD(cursor.getString(5));
            question.setAnswer(cursor.getString(6));
            list_question.add(question);
            cursor.moveToNext();
        }
        cursor.close();
        Collections.shuffle(list_question);
        return list_question;
    }
}
