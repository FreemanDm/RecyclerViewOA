package com.freeman.recyclerviewoa.recyclerviewoa;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, MyAdapter.ItemClickListener {

    private MyAdapter adapter;
    private MySecondAdapter secondAdapter;
    private RecyclerView recyclerView;
    private Button addBtn, removeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new MyAdapter();
        secondAdapter = new MySecondAdapter();
        adapter.setItemClickListener(this);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        addBtn = (Button) findViewById(R.id.add_btn);
        removeBtn = (Button) findViewById(R.id.remove_btn);
        filladapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
//        recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(secondAdapter);

        recyclerView.addOnItemTouchListener(new MyOnItemTouchListener(this, recyclerView, new MyOnItemTouchListener.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Contact contact = secondAdapter.getContact(position);
                Toast.makeText(MainActivity.this, "Was click on " + contact.getName(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(View view, int position) {
                Contact contact = secondAdapter.getContact(position);
                Toast.makeText(MainActivity.this, "Was long click on " + contact.getName(), Toast.LENGTH_SHORT).show();
            }
        }));

        addBtn.setOnClickListener(this);
        removeBtn.setOnClickListener(this);

    }

    private void filladapter(){
        for (int i = 0; i < 20; i++){
//            adapter.add(new Contact("Vasya " + i, "vasya@gmail.com"));
            secondAdapter.addContact(new Contact("Vasya " + i, "vasya@gmail.com"));
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add_btn){
            adapter.addThirdRow();
        }else if (v.getId() == R.id.remove_btn){
            adapter.removeFourth();
        }
    }

    @Override
    public void onItemClick(View view, int position) {
        Contact contact = adapter.getContact(position);
        Toast.makeText(this, "Was clicked " + contact.getName(), Toast.LENGTH_SHORT).show();
    }
}
