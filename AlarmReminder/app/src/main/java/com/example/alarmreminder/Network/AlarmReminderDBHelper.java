package com.example.alarmreminder.Network;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.alarmreminder.Data.AlarmReminderModel;

public class AlarmReminderDBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "alarmreminder1.db";

    private static final int DATABASE_VERSION = 1;


    public AlarmReminderDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_ALARM_TABLE =  "CREATE TABLE " + AlarmReminderModel.AlarmReminderEntry.TABLE_NAME + " ("
                + AlarmReminderModel.AlarmReminderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + AlarmReminderModel.AlarmReminderEntry.KEY_TITLE + " TEXT, "
                + AlarmReminderModel.AlarmReminderEntry.KEY_DATE + " TEXT, "
                + AlarmReminderModel.AlarmReminderEntry.KEY_TIME + " TEXT, "
                + AlarmReminderModel.AlarmReminderEntry.KEY_REPEAT + " TEXT, "
                + AlarmReminderModel.AlarmReminderEntry.KEY_REPEAT_NO + " TEXT, "
                + AlarmReminderModel.AlarmReminderEntry.KEY_REPEAT_TYPE + " TEXT, "
                + AlarmReminderModel.AlarmReminderEntry.KEY_ACTIVE + " TEXT" + " );";

        db.execSQL(SQL_CREATE_ALARM_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("msg", getDatabaseName());
    }
}
