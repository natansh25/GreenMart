package com.example.greendao.allsignin.Service;

import com.example.greendao.allsignin.Contact;
import com.example.greendao.allsignin.model.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("api/Login/Get")
    Call<Example> getcontacts();

    @GET("api/Login/Get")
    Call<Contact> getcontacts1();


}
