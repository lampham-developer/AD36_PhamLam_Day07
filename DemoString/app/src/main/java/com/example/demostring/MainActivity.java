package com.example.demostring;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Contact contact1,contact2,contact3,contact4,contact5;
    List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView= findViewById(R.id.rvList);
        contacts=new ArrayList<>();

        contact1=new Contact("Mr A","09876555");
        contact2=new Contact("Mr B","0983333");
        contact3=new Contact("Mr C","09874444");
        contact4=new Contact("Mr D","09877777");
        contact5=new Contact("Mr E","09879999");

        contacts.add(contact1);
        contacts.add(contact2);
        contacts.add(contact3);
        contacts.add(contact4);
        contacts.add(contact5);
        AdapterContact adapterContact = new AdapterContact(contacts);
        recyclerView.setAdapter(adapterContact);

    }
}
