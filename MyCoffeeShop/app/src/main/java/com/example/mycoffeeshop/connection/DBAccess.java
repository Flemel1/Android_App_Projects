package com.example.mycoffeeshop.connection;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.mycoffeeshop.model.Barang;

import java.util.ArrayList;
import java.util.List;

public class DBAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DBAccess instance;
    private final String DB_TABLE_BARANG = "Barang";
    private final String DB_TABLE_NOTA = "Nota";

    private DBAccess(Context context) {
        this.openHelper = new DBHelper(context);
    }

    public static DBAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DBAccess(context);
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

    public List<Barang> getBarang() {
        List<Barang> list_barang = new ArrayList<Barang>();
        Cursor cursor = database.rawQuery("SELECT * FROM Barang", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Barang barang = new Barang();
            barang.setId(cursor.getInt(0));
            barang.setNama_barang(cursor.getString(1));
            barang.setHarga_barang(cursor.getInt(2));
            list_barang.add(barang);
            cursor.moveToNext();
        }
        cursor.close();
        return list_barang;
    }

    public void insert(String name, int price){
        ContentValues values = new ContentValues();
        values.put("nama_barang", name);
        values.put("harga", price);
        database.insertWithOnConflict(DB_TABLE_BARANG,null,values,SQLiteDatabase.CONFLICT_REPLACE);
    }

    public void delete(String name){
        database.delete(DB_TABLE_BARANG, "nama_barang = ?", new String[]{name});
    }

    public void insertToNota(String name, int quantity, int price){
        ContentValues values = new ContentValues();
        values.put("nama_barang", name);
        values.put("quantity", quantity);
        values.put("harga", price);
        database.insertWithOnConflict(DB_TABLE_NOTA, null, values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public List<Barang> getNota(){
        List<Barang> nota = new ArrayList<Barang>();
        Cursor cursor = database.rawQuery("SELECT * FROM Nota", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Barang barang = new Barang();
            barang.setNama_barang(cursor.getString(0));
            barang.setQuantity(cursor.getInt(1));
            barang.setHarga_barang(cursor.getInt(2));
            nota.add(barang);
            cursor.moveToNext();
        }
        cursor.close();
        return nota;
    }

    public void deleteNota(){
        database.delete(DB_TABLE_NOTA,null,null);
    }
}
