package com.example.greendao.allsignin;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.greendao.allsignin.adapter.RecyclerAdapter;
import com.example.greendao.allsignin.model.ResponseDatum;
import com.example.greendao.allsignin.room.AppDatabase;
import com.example.greendao.allsignin.room.GreenDao;

import java.util.List;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager LayoutManager;

    private RecyclerAdapter adapter;
    private GreenDao mGreenDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportActionBar().setTitle("Shopping Cart");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = findViewById(R.id.recyclerViewCart);
        AppDatabase db = AppDatabase.getDatabase(this);
        mGreenDao = db.greenDao();
        setupRecyclerView();
        setData();
        swipeToDelete();

    }

    private void swipeToDelete() {

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

                ResponseDatum response= (ResponseDatum) viewHolder.itemView.getTag();
                mGreenDao.delete(response);
                setData();
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void setData() {
        List<ResponseDatum> data;

        data = mGreenDao.getAllFav();
        adapter = new RecyclerAdapter(data);
        //adapter.setOnItemClickListner(this);
        recyclerView.setAdapter(adapter);
    }

    private void setupRecyclerView() {

        LayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(LayoutManager);
    }
}
