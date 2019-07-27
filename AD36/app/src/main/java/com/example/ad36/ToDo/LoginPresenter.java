package com.example.ad36.ToDo;

import android.content.Context;

import com.example.ad36.Entity.User;
import com.example.ad36.Interface.ILogin;

public class LoginPresenter {
    ILogin iLogin;
    Context context;

    public LoginPresenter(ILogin iLogin, Context context) {
        this.iLogin = iLogin;
        this.context = context;
    }

    public void onLogin(String name,String pass, User user){

        if(name.isEmpty() || pass.isEmpty()){
            iLogin.onMessenger("Mời nhập đầu đủ thông tin");
        }else{
            if(user != null){
                if (name.equals(user.getUsername())&&pass.equals(user.getPassword())){
                    iLogin.onSuccessFul();
                }else iLogin.onMessenger("Mật khẩu không đúng");
            }else {
                iLogin.onMessenger("Tài khoản không tồn tại");
            }
        }

    }
}
