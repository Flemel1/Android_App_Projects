package Connection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import Model.ModelList;

public class DBHelper extends SQLiteOpenHelper {
    private final static String DB_NAME = "TODOLIST";
    private final static int DB_VER = 2;
    private final static String DB_TABLE = "MySchedule";
    private final static String DB_COLUMN = "TaskName";
    private final static String DB_COLUMN2 = "InformationTask";

    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String creteDB = String.format("CREATE TABLE %s (ID INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT NOT NULL, %s TEXT NOT NULL)",
                DB_TABLE,DB_COLUMN, DB_COLUMN2);
        db.execSQL(creteDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("oldVersion", String.valueOf(oldVersion));
            String upgradeDB = String.format("DROP TABLE IF EXISTS %s", DB_TABLE);
            db.execSQL(upgradeDB);
            onCreate(db);
        Log.e("upgradeDB", "Berhasil");
    }

    public void insertNewTask(String task, String information){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN,task);
        values.put(DB_COLUMN2, information);
        db.insertWithOnConflict(DB_TABLE,null,values,SQLiteDatabase.CONFLICT_REPLACE);
        db.close();
    }

    public void deleteTask(String task){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE, DB_COLUMN + " = ?", new String[]{task});
        db.close();
    }

    public void deleteAll(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(DB_TABLE,null,null);
        db.close();
    }

    public void updateTask(String oldTask, String newTask){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DB_COLUMN, newTask);
        String selection = DB_COLUMN + " LIKE ?";
        db.update(DB_TABLE,values,selection, new String[]{oldTask});
        db.close();
    }

    public ArrayList<ModelList> getAllTask(){
        ArrayList<ModelList> taskList = new ArrayList<ModelList>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(DB_TABLE, new String[]{DB_COLUMN,DB_COLUMN2},null,
                null,null,null,DB_COLUMN);
        while (cursor.moveToNext()){
            ModelList modelList = new ModelList();
            int index = cursor.getColumnIndex(DB_COLUMN);
            int index2 = cursor.getColumnIndex(DB_COLUMN2);
            modelList.setTaskName(cursor.getString(index));
            modelList.setTaskInformation(cursor.getString(index2));
            taskList.add(modelList);
        }
        cursor.close();
        db.close();
        return taskList;
    }
}
