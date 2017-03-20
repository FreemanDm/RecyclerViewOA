package com.freeman.recyclerviewoa.recyclerviewoa;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Freeman on 23.01.2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> implements View.OnClickListener {
    private ArrayList<Contact> contacts = new ArrayList<>();
    private ItemClickListener listener;

    public void setItemClickListener(ItemClickListener listener){
        this.listener = listener;
    }

    public Contact getContact(int position){
        return contacts.get(position);
    }

    public void add(Contact contact){
        contacts.add(contact);
        notifyItemInserted(contacts.size()-1);
    }

    public void addThirdRow(){
        Random random = new Random();
        contacts.add(2, new Contact("Vasy" + random.nextInt(255), "vasy@gmail.com"));
        notifyItemInserted(2);
    }

    public void removeFourth(){
        contacts.remove(3);
        notifyItemRemoved(3);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_view, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Contact contact = contacts.get(position);
        holder.nameTxt.setText(contact.getName());
        holder.emailTxt.setText(contact.getEmail());
        holder.view.setId(position);
    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v, v.getId());
    }

    public interface ItemClickListener{
        void onItemClick(View view, int position);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView nameTxt, emailTxt;
        View view;

        public MyViewHolder(View itemView) {
            super(itemView);
            nameTxt = (TextView) itemView.findViewById(R.id.name_txt);
            emailTxt = (TextView) itemView.findViewById(R.id.email_txt);
            itemView.setOnClickListener(MyAdapter.this);
            view = itemView;
        }
    }
}
