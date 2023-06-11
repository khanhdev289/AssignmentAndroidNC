package khanhnqph30151.fptpoly.assignment.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.assignment.model.Favorite;
import khanhnqph30151.fptpoly.assignment.model.Music;

public class FavoriteDAO {
    DBHelper dbHelper;
    private static SQLiteDatabase sqliteDa;

    public FavoriteDAO(Context context) {
        dbHelper = new DBHelper(context);
        sqliteDa = dbHelper.getWritableDatabase();
    }
    public ArrayList<Favorite> GetDSS(){
        ArrayList<Favorite> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_fav",null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                Favorite fav = new Favorite();
                fav.setIdMusic(Integer.parseInt(cursor.getString(0)));
                fav.setTenMusic(cursor.getString(1));
                list.add(fav);
            }while (cursor.moveToNext());
        }
        return list;
    }


    public long ThemFav(Favorite favoriteMusic) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("tennhac", favoriteMusic.getTenMusic());

        return sqLiteDatabase.insert("tbl_fav", null, contentValues);
    }

    public long XoaFav(int id) {
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        return sqLiteDatabase.delete("tbl_fav", "id = ?", new String[]{String.valueOf(id)});
    }

}

