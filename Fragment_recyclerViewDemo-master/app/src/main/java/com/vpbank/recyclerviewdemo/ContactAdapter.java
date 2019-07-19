package com.vpbank.recyclerviewdemo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.Viewhoder> {

    List<Contact> contacts;
    IonClickContact ionClickContact;

    public ContactAdapter(List<Contact> contacts) {
        this.contacts = contacts;
    }

    public void setIonClickContact(IonClickContact ionClickContact) {
        this.ionClickContact = ionClickContact;
    }

    @NonNull
    @Override
    public Viewhoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_contact, parent, false);
        Viewhoder viewhoder = new Viewhoder(view);

        return viewhoder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewhoder holder, int position) {
        final Contact contact = contacts.get(position);

        holder.tvName.setText(contact.getName());
        holder.tvPhone.setText(contact.getmPhone());

        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickContact.onClickName(contact.getName());
            }
        });

        holder.tvPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ionClickContact.onClickPhone(contact.getmPhone());
            }
        });


    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class Viewhoder extends RecyclerView.ViewHolder {
        TextView tvName, tvPhone;

        public Viewhoder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvPhone = itemView.findViewById(R.id.tvPhone);
        }
    }
}
