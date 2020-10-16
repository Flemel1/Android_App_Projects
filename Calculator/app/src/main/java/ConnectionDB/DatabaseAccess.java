package ConnectionDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Model.Income;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    private static final String DB_TABLE = "income";

    private DatabaseAccess(Context context) {
        this.openHelper = new DBHelper(context);
    }

    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public void insertOutcome(String month, int money, String date){
        ContentValues values = new ContentValues();
        values.put("Month", month);
        values.put("Money", money);
        values.put("Date", date);
        database.insertWithOnConflict(DB_TABLE,null,values,SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void updateOutcome(String id, int money){
        ContentValues values = new ContentValues();
        values.put("Money", money);
        String selection = "ID LIKE ?";
        database.update(DB_TABLE, values, selection, new String[] {id});
    }

    public List<String> getMounth() {
        List<String> list = new ArrayList<String>();
        Cursor cursor = database.rawQuery("SELECT DISTINCT Month FROM income", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            list.add(cursor.getString(0));
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

    public List<Income> getDetail(String month){
        List<Income> listDetail = new ArrayList<Income>();
        Cursor cursor = database.rawQuery("SELECT * FROM income WHERE Month Like ? ", new String[] {month});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Income income = new Income();
            income.setID(cursor.getInt(0));
            income.setMonth(cursor.getString(1));
            income.setMoney(cursor.getInt(2));
            income.setDate(cursor.getString(3));
            listDetail.add(income);
            cursor.moveToNext();
        }
        cursor.close();
        return listDetail;
    }
}
