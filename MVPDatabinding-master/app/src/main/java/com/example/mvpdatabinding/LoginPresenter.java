package com.example.mvpdatabinding;

import android.content.Context;

public class LoginPresenter {
    ILogin iLogin;
    Context context;

    public LoginPresenter(ILogin iLogin, Context context) {
        this.iLogin = iLogin;
        this.context = context;
    }

    public void onLogin(String user,String pass){

        if (user.equals("haint")&&pass.equals("12345")){
            iLogin.onSuccessFul();
        }else iLogin.onMessenger("User or pass không đúng");

    }
}
