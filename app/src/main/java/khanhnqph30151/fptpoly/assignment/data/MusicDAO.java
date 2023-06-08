package khanhnqph30151.fptpoly.assignment.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.assignment.model.Music;

public class MusicDAO {
    DBHelper dbHelper;
    private static SQLiteDatabase sqliteDa;

    public MusicDAO(Context context) {
        dbHelper = new DBHelper(context);
        sqliteDa = dbHelper.getWritableDatabase();
    }
    public ArrayList<Music> GetDSS(){
        ArrayList<Music> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM nhac",null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Music music = new Music();
                music.setIdMusic(Integer.parseInt(cursor.getString(0)));
                music.setTenMusic(cursor.getString(1));
                music.setLink(cursor.getString(2));
                list.add(music);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public long Themsong(Music music){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tennhac", music.getTenMusic());
        contentValues.put("linknhac", music.getLink());

        return sqLiteDatabase.insert("nhac",null,contentValues);
    }
    public static int delete(int id){
        return sqliteDa.delete("nhac", "id = ?", new String[]{String.valueOf(id)});
    }
}
