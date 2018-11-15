package com.moto.aiolo.motoclubproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.moto.aiolo.motoclubproject.Interface.EventOperation;
import com.moto.aiolo.motoclubproject.Model.ResponseModel.EventResponse;
import com.moto.aiolo.motoclubproject.Mthods.APIMethod;
import com.moto.aiolo.motoclubproject.adapterClass.AdapterCustomListViewEvent;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class EventList extends AppCompatActivity {

    private ListView listView;
    private AdapterCustomListViewEvent adapterCustomListViewEvent;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        CreateListView();
    }

    private void CreateListView(){
        Retrofit retrofit = APIMethod.API();
        EventOperation eventOperation = retrofit.create(EventOperation.class);
        Call<List<EventResponse>> request = eventOperation.getAllEvents();
        request.enqueue(new Callback<List<EventResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<EventResponse>> call, @NonNull Response<List<EventResponse>> response) {
                if (!response.isSuccessful()){
                    Log.e("ERROR ", response.message());
                } else {
                    populateListView(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<EventResponse>> call, @NonNull Throwable t) {

            }
        });
    }

    private void populateListView(List<EventResponse> body){
        listView = findViewById(R.id.list_event);
        adapterCustomListViewEvent = new AdapterCustomListViewEvent(getApplicationContext(), (ArrayList<EventResponse>) body);
        listView.setAdapter(adapterCustomListViewEvent);
    }

}
