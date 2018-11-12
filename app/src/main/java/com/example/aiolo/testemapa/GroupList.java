package com.example.aiolo.testemapa;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.aiolo.testemapa.Interface.GroupOperation;
import com.example.aiolo.testemapa.Model.ResponseModel.GroupResponse;
import com.example.aiolo.testemapa.Mthods.APIMethod;
import com.example.aiolo.testemapa.adapterClass.AdapterCustomListViewGroup;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GroupList extends AppCompatActivity {

    private ListView listView;
    private AdapterCustomListViewGroup adapterCustomListViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        createListView();
    }

    private void createListView(){

        Retrofit retrofit = APIMethod.API();

        GroupOperation response  = retrofit.create(GroupOperation.class);
        Call<List<GroupResponse>> request = response.getAllGroups();
        request.enqueue(new Callback<List<GroupResponse>>() {
            @Override
            public void onResponse(@NonNull Call<List<GroupResponse>> call,
                                   @NonNull Response<List<GroupResponse>> response) {
                if (!response.isSuccessful()){
                    Log.e("ERROR ", response.message());
                } else {
                    populateListView(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<GroupResponse>> call, Throwable t) {

            }
        });
    }

    private void populateListView(List<GroupResponse> body){
        //listView = findViewById(R.id.list_group);
        adapterCustomListViewGroup = new AdapterCustomListViewGroup(getApplicationContext(), (ArrayList<GroupResponse>) body);
        listView.setAdapter(adapterCustomListViewGroup);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GroupResponse groupResponse = (GroupResponse) parent.getItemAtPosition(position);

                Log.d("RUD", groupResponse.getTitleGroup());
            }
        });
    }

    
}
