package khanhnqph30151.fptpoly.assignment.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(@Nullable Context context) {
        super(context, "APP_MUSIC", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String bangdsnhac = "CREATE TABLE nhac(id integer primary key autoincrement,tennhac text ," +
                "linknhac text UNIQUE)";
        db.execSQL(bangdsnhac);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deletebangdsnhac = "DROP TABLE IF EXISTS nhac";
        db.execSQL(deletebangdsnhac);
        onCreate(db);
    }
}
