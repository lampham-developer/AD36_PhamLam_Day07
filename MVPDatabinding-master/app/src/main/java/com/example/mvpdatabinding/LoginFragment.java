package com.example.mvpdatabinding;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.mvpdatabinding.databinding.LoginFragmentBinding;

public class LoginFragment extends Fragment implements ILogin {

    LoginFragmentBinding binding;
    LoginPresenter presenter;

    public static LoginFragment newInstance() {
        Bundle args = new Bundle();
        LoginFragment fragment = new LoginFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false);

        presenter = new LoginPresenter(this, getContext());
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateLogin()) {
                    String name = binding.etUserName.getText().toString();
                    String pass = binding.etPassword.getText().toString();

                    presenter.onLogin(name, pass);
                }
            }
        });

        return binding.getRoot();
    }

    @Override
    public void onSuccessFul() {
        Toast.makeText(getContext(), "Done", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onMessenger(String mes) {
        Toast.makeText(getContext(), mes, Toast.LENGTH_LONG).show();
    }

    private boolean validateLogin() {
        if (TextUtils.isEmpty(binding.etUserName.getText().toString())) {
            binding.etUserName.setError(getResources().getString(R.string.change_user_error));
            binding.etUserName.requestFocus();
            return false;
        }

        if (TextUtils.isEmpty(binding.etPassword.getText().toString())) {
            binding.etPassword.setError(getResources().getString(R.string.change_password_error));
            binding.etPassword.requestFocus();
            return false;
        }

        return true;
    }
}
