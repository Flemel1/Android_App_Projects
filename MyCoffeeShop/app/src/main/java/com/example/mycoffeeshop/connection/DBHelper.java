package com.example.mycoffeeshop.connection;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DBHelper extends SQLiteAssetHelper {
    private static final String DB_NAME = "my_coffee_shop.db";
    private static final int DB_VERSION = 2;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        if (DB_VERSION > 1){
            setForcedUpgrade();
        }
    }
}
