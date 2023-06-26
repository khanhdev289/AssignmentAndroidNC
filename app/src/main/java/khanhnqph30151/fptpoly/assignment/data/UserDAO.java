package khanhnqph30151.fptpoly.assignment.data;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import khanhnqph30151.fptpoly.assignment.model.Music;
import khanhnqph30151.fptpoly.assignment.model.User;

public class UserDAO {
    DBHelper dbHelper;
    private static SQLiteDatabase sqliteDa;

    public UserDAO(Context context) {
        dbHelper = new DBHelper(context);
        sqliteDa = dbHelper.getWritableDatabase();
    }
    public ArrayList<User> GetDSS(){
        ArrayList<User> list = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_user",null);
        if(cursor.getCount() > 0){
            cursor.moveToFirst();
            do{
                User user = new User();
                user.setUser(cursor.getString(0));
                user.setPass(cursor.getString(1));
                user.setAvatar(cursor.getString(2));
                list.add(user);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public long Themuser(User user){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id_user", user.getUser());
        contentValues.put("pass_user", user.getPass());

        return sqLiteDatabase.insert("tbl_user",null,contentValues);
    }
    public boolean updateAvatar(User user) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("avatar",user.getAvatar());
        int rowsAffected = db.update("tbl_user", values, "id_user = ?", new String[]{String.valueOf(user.getUser())});
        return rowsAffected > 0;
    }


    public boolean checkLogin(String tentv,String mk){

        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM tbl_user WHERE id_user = ? AND pass_user = ? ",
                new String[]{tentv,mk});
        if(cursor.getCount() > 0){
            return  true;
        }
        else {
            return false;
        }
    }
}
