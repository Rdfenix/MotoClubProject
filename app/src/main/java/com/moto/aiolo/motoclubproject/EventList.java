package com.moto.aiolo.motoclubproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
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

import static com.moto.aiolo.motoclubproject.Mthods.ModalMethod.ModalEvent;

public class EventList extends AppCompatActivity {

    private ListView listView;
    private AdapterCustomListViewEvent adapterCustomListViewEvent;
    final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_list);
        CreateListView();

        TextView back = findViewById(R.id.goBackGroup);
        TextView registerEvent = findViewById(R.id.cadastrar_evento);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        registerEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RegisterEvent.class);
                startActivity(intent);
                finish();
            }
        });

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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                EventResponse eventResponse = (EventResponse) parent.getItemAtPosition(position);
                ModalEvent(context, eventResponse);
            }
        });
    }

}
