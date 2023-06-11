package khanhnqph30151.fptpoly.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import khanhnqph30151.fptpoly.assignment.data.UserDAO;
import khanhnqph30151.fptpoly.assignment.service.Service_Login_Register;

public class SignUpActivity extends AppCompatActivity {
    EditText edUser, edPass, edPass2;
    UserDAO thanhVienDAO;
    AppCompatButton btnDangKy;
    Service_Login_Register service_loginRegister;
    boolean checkconnected;
    ServiceConnection sv_register = new ServiceConnection() {

        @Override

        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {

            Service_Login_Register.MyBinder myBinder = (Service_Login_Register.MyBinder) iBinder;
            service_loginRegister = myBinder.getService_login();
            checkconnected = true;
        }

        @Override

        public void onServiceDisconnected(ComponentName componentName) {
            checkconnected = false;

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        TextView tvDangNhap = findViewById(R.id.tv_signup_dangnhap);

        edUser = findViewById(R.id.ed_signup_ten);
        edPass = findViewById(R.id.ed_signup_matkhau);
        edPass2 = findViewById(R.id.ed_signup_nhaplaimatkhau);
        btnDangKy = findViewById(R.id.btn_signup_DangKy);

        thanhVienDAO = new UserDAO(getApplicationContext());
        service_loginRegister = new Service_Login_Register();



        tvDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendangky = edUser.getText().toString();
                String matkhaudk = edPass.getText().toString();
                Intent intentdangky = new Intent(SignUpActivity.this, Service_Login_Register.class);
                bindService(intentdangky,sv_register, Context.BIND_AUTO_CREATE);
                if(edUser.length() == 0){
                    edUser.requestFocus();
                    edUser.setError("Không để trống tên đăng ký");
                }
                else if(edPass.length() == 0){
                    edPass.requestFocus();
                    edPass.setError("Không bỏ trống mật khẩu");
                }
                else if(edPass2.length() == 0){
                    edPass2.requestFocus();
                    edPass2.setError("Không bỏ trống nhập lại mật khẩu");
                }
                else if(!edPass.getText().toString().equals(edPass2.getText().toString())){
                    edPass2.requestFocus();
                    edPass2.setError("Mật khẩu không khớp với nhau");
                }
                else {
                    if(service_loginRegister.DangKy(thanhVienDAO,tendangky,matkhaudk) ==true){
                        Toast.makeText(SignUpActivity.this, "Đăng Ký Thành Công", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(SignUpActivity.this, "Đăng Ký Thất Bại", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}