package com.freeman.recyclerviewoa.recyclerviewoa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Freeman on 24.01.2017.
 */

public class MySecondAdapter extends RecyclerView.Adapter<MySecondAdapter.MyViewHolder> {

    private ArrayList<Contact> contacts = new ArrayList<>();

    public void addContact(Contact contact){
        contacts.add(contact);
        notifyItemInserted(contacts.size()-1);
    }

    public Contact getContact(int position){
        return contacts.get(position);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_view, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nameTxt.setText(contact.getName());
        holder.emailTxt.setText(contact.getEmail());
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameTxt, emailTxt;
        public MyViewHolder(View itemView){
            super(itemView);
            nameTxt = (TextView) itemView.findViewById(R.id.name_txt);
            emailTxt = (TextView) itemView.findViewById(R.id.email_txt);
        }
    }
}
