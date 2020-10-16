package ConnectionDB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;


public class DBHelper extends SQLiteAssetHelper {
    private static final String DB_NAME = "income2.db";
    private static final int DB_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

}
