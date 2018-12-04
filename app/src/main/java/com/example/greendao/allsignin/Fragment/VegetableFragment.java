package com.example.greendao.allsignin.Fragment;


import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.greendao.allsignin.Contact;
import com.example.greendao.allsignin.R;
import com.example.greendao.allsignin.adapter.RecyclerAdapter;
import com.example.greendao.allsignin.Service.ApiClient;
import com.example.greendao.allsignin.Service.ApiInterface;
import com.example.greendao.allsignin.model.Example;
import com.example.greendao.allsignin.model.ResponseDatum;
import com.example.greendao.allsignin.room.AppDatabase;
import com.example.greendao.allsignin.room.GreenDao;
import com.facebook.stetho.Stetho;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */

public class VegetableFragment extends Fragment implements RecyclerAdapter.ListItemClickListner {


    //TODO : implement on click in recyclerAdapter : Test : on Press on click Toast Details
    //TODO : Room integration 3 files DAO Entity Databse : Test : run the application allow query on main thread
    //TODO : onClick pe call insert Room quey : Test : integrate setho by facebook to check in the background
    //TODO : create new activty and place an intent on the cart to this activty and place a recycler view in this acitvity also create an adapter for this room databse getQuery , display in the recycler view , on swipe to delete : TEST : full data shoud get displayed on the recycler view and also can be deleted and put a back arrow


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager LayoutManager;
    private Contact contacts;
    private RecyclerAdapter adapter;
    private ApiInterface apiInterface;

    private GreenDao mGreenDao;

    public VegetableFragment() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_vegetable, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        Stetho.initializeWithDefaults(getActivity());
        setupRecyclerView();
        callRetrofit();
        AppDatabase db = AppDatabase.getDatabase(getActivity());
        mGreenDao = db.greenDao();

        return view;
    }

    private void setupRecyclerView() {

        LayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(LayoutManager);
    }

    private void callRetrofit() {

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Example> call = apiInterface.getcontacts();
        call.enqueue(new Callback<Example>() {

            @Override
            public void onResponse(Call<Example> call, Response<Example> response) {
                List<ResponseDatum> results = response.body().getResponseData();

                String url = String.valueOf(response.raw().request().url());
                setRecyclerView(results);


                Log.d("Hey", url);
            }

            @Override
            public void onFailure(Call<Example> call, Throwable t) {

                Toast.makeText(getActivity(), "Error Loading", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void setRecyclerView(List<ResponseDatum> results) {
        adapter = new RecyclerAdapter(results);
        adapter.setOnItemClickListner(this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onButtonClick(ResponseDatum response) {
        mGreenDao.insert(response);
        Toast.makeText(getActivity(), "Added !!", Toast.LENGTH_SHORT).show();

    }
}
