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

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class FruitFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager LayoutManager;
    private Contact contacts;
    private RecyclerAdapter adapter;
    private ApiInterface apiInterface;

    public FruitFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_fruit, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        setupRecyclerView();
        callRetrofit();

        return view;
    }
    private void setupRecyclerView() {

        LayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(LayoutManager);
    }

    private void callRetrofit() {
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<Contact> call = apiInterface.getcontacts1();
        call.enqueue(new Callback<Contact>() {

            @Override
            public void onResponse(Call<Contact> call, Response<Contact> response) {
                contacts = response.body().getResponseData();
                String url= String.valueOf(response.raw().request().url());

//                Single list
                List<Contact> mContact= Collections.singletonList(contacts);
              //  adapter = new RecyclerAdapter(mContact);
               // recyclerView.setAdapter(adapter);

                Log.e("Hey",contacts.getEmail()+ " " + url);
            }

            @Override
            public void onFailure(Call<Contact> call, Throwable t) {

                Toast.makeText(getActivity(), "Error Loading", Toast.LENGTH_SHORT).show();

            }
        });
    }

}
