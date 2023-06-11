package khanhnqph30151.fptpoly.assignment.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import khanhnqph30151.fptpoly.assignment.data.UserDAO;
import khanhnqph30151.fptpoly.assignment.model.User;


public class Service_Login_Register extends Service {
    UserDAO thanhVienDAO;
    User thanhVien;
    private MyBinder myBinder = new MyBinder();

    @Override
    public void onCreate() {

        super.onCreate();
        thanhVienDAO = new UserDAO(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return myBinder;
    }
    public boolean DangKy(UserDAO thanhVienDAO,String tendk,String mkdk){
        thanhVien = new User();
        User thanhVienthem = new User(tendk,mkdk);
        if(thanhVienDAO.Themuser(thanhVienthem) > 0){
            return true;
        }
        else {
            return false;
        }
    }
    public boolean DangNhap(UserDAO thanhVienDAO,String tendn,String mk){
        if(thanhVienDAO.checkLogin(tendn,mk)){
            return true;
        }
        else {
            return false;
        }
    }


    public class MyBinder extends Binder {

        public Service_Login_Register getService_login() {

            return Service_Login_Register.this;

        }
    }

}
