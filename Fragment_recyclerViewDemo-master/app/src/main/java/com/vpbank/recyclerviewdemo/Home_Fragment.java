package com.vpbank.recyclerviewdemo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Home_Fragment extends Fragment {

    RecyclerView recyclerView;
    ContactAdapter contactAdapter;

    Contact contact1, contact2, contact3, contact4, contact5, contact6;
    List<Contact> contacts;

    public static Home_Fragment newInstance(Contact contact) {

        //truyền object qua Bundle
        Bundle args = new Bundle();
        args.putSerializable("contactPut", contact);
        Home_Fragment fragment = new Home_Fragment();
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        //getObject qua Bundle
        contact6 = (Contact) getArguments().getSerializable("contactPut");

        recyclerView = view.findViewById(R.id.rvList);
        contacts = new ArrayList<>();


        contact1 = new Contact("Mr A", "0912333333");
        contact2 = new Contact("Mr B", "0912334444");
        contact3 = new Contact("Mr C", "0912355555");
        contact4 = new Contact("Mr D", "0912336666");
        contact5 = new Contact("Mr E", "0912339999");

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        contacts.add(contact4);
        contacts.add(contact5);
        contacts.add(contact6);

        //set list hiển thị chế độ LinearLayoutManager Vertical
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);

        //hiên thị GridLayoutManager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getContext(), 2, RecyclerView.VERTICAL, false);

        contactAdapter = new ContactAdapter(contacts);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(layoutManager);

        contactAdapter.setIonClickContact(new IonClickContact() {
            @Override
            public void onClickName(String name) {
                Toast.makeText(getContext(), name, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onClickPhone(String phone) {
                Toast.makeText(getContext(), phone, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
