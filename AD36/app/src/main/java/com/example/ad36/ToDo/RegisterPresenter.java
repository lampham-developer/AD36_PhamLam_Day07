package com.example.ad36.ToDo;

import android.content.Context;

import com.example.ad36.Entity.User;
import com.example.ad36.Interface.ILogin;

import java.util.regex.Pattern;

public class RegisterPresenter {
    ILogin iLogin;
    Context context;

    public RegisterPresenter(ILogin iLogin, Context context) {
        this.iLogin = iLogin;
        this.context = context;
    }

    public void onRegister(String name,String pass,String repass, User user){

        if(user == null){
            if (pass.equals(repass)){
                String password_parten = "^(?=.*[A-Z])(?=.*[!@#$&*])(?=.*[0-9])(?=.*[a-z]).{6,}$";
                if (Pattern.matches(password_parten, pass)){
                    iLogin.onSuccessFul();
                }else iLogin.onMessenger("Mật khẩu phải bao gồm chữ thường, chữ hoa, số và kí tự đặc biệt");
            }else iLogin.onMessenger("Mật khẩu phải nhập trùng nhau");
        }else {
            iLogin.onMessenger("Tài khoản đã tồn tại");
        }

    }
}
