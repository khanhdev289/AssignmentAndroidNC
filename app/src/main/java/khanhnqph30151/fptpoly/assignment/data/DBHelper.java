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
                "linknhac text UNIQUE, traitim text)";
        db.execSQL(bangdsnhac);
        String user = "CREATE TABLE tbl_user(id_user text primary key, pass_user text not null, avatar text)";
        db.execSQL(user);
        String favorite =  "CREATE TABLE tbl_fav(id integer primary key autoincrement, tennhac text UNIQUE REFERENCES nhac(tennhac))";
        db.execSQL(favorite);

//        db.execSQL("INSERT INTO tbl_user VALUES('admin','123')");




    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String deletebangdsnhac = "DROP TABLE IF EXISTS nhac";
        db.execSQL(deletebangdsnhac);
        String deletebangdsthanhvien = "DROP TABLE IF EXISTS tbl_user";
        db.execSQL(deletebangdsthanhvien);
        String deletebangfav = "DROP TABLE IF EXISTS tbl_fav";
        db.execSQL(deletebangfav);
        onCreate(db);
    }
}
