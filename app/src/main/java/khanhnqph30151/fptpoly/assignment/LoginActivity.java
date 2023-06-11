package khanhnqph30151.fptpoly.assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import khanhnqph30151.fptpoly.assignment.data.UserDAO;
import khanhnqph30151.fptpoly.assignment.service.Service_Login_Register;

public class LoginActivity extends AppCompatActivity {

    EditText edUser, edPass;
    UserDAO thanhVienDAO;
    AppCompatButton btnDangNhap;
    SharedPreferences sharedPreferences;

    SharedPreferences.Editor editor;
    Service_Login_Register service_loginRegister;
    boolean checkconnected;
    ServiceConnection sv_slogin = new ServiceConnection() {

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
        setContentView(R.layout.activity_login);

        btnDangNhap = findViewById(R.id.btn_Login_DangNhap);
        TextView tvDangKy = findViewById(R.id.tv_Login_dangky);

        edUser = findViewById(R.id.ed_Login_ten);
        edPass = findViewById(R.id.ed_Login_matkhau);

        thanhVienDAO = new UserDAO(getApplicationContext());
        service_loginRegister = new Service_Login_Register();
        initPreferences();
        SharedPreferences sharedPreferences1 = getSharedPreferences("DATALOGIN", MODE_PRIVATE);
        edUser.setText(sharedPreferences1.getString("TENDN", ""));
        edPass.setText(sharedPreferences1.getString("MKDN", ""));

        tvDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String tendn = edUser.getText().toString();
                String mk = edPass.getText().toString();
                Intent intentlogin = new Intent(LoginActivity.this, MainActivity.class);
                bindService(intentlogin, sv_slogin, Context.BIND_AUTO_CREATE);
                startService(intentlogin);
                if (edUser.length() == 0) {
                    edUser.requestFocus();
                    edUser.setError("Không bỏ trống tên đăng nhập");
                } else if (edPass.length() == 0) {
                    edPass.requestFocus();
                    edPass.setError("Không bỏ trống mật khẩu đăng nhập");
                } else {
                    if (service_loginRegister.DangNhap(thanhVienDAO,tendn,mk) == true) {
                        Toast.makeText(LoginActivity.this, "Đăng Nhập Thành Công", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(LoginActivity.this, "Đăng Nhập Thất Bại", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

    private void initPreferences() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        editor = sharedPreferences.edit();
    }
}