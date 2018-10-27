package com.example.aiolo.testemapa;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aiolo.testemapa.Interface.GroupOperation;
import com.example.aiolo.testemapa.Model.APIResponse;
import com.example.aiolo.testemapa.Model.Group;
import com.example.aiolo.testemapa.Mthods.APIMethod;
import com.example.aiolo.testemapa.Mthods.ValidateItems;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterGroup extends AppCompatActivity {

    int minimumInteger = 0;

    private EditText title;
    private EditText description;
    private EditText motoCategory;
    private EditText city;
    private EditText state;
    private TextView count;
    private Button registerGroup;

    ValidateItems validateItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_group);

        title = findViewById(R.id.title_group);
        description = findViewById(R.id.group_description);
        motoCategory = findViewById(R.id.moto_category);
        city = findViewById(R.id.city_register_group);
        state = findViewById(R.id.state_register_group);
        count = findViewById(R.id.contador);
        registerGroup = findViewById(R.id.buttom_register_group);

        registerGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempRegister();
            }
        });
    }

    private void attempRegister(){
        String titleGroug = title.getText().toString();
        String descGroup = description.getText().toString();
        String motoCat = motoCategory.getText().toString();
        String cityGroup = city.getText().toString();
        String stateGroup = state.getText().toString();
        String countGroup = count.getText().toString();

        if (textEmpty(titleGroug) || textEmpty(descGroup) || textEmpty(motoCat)
                || textEmpty(cityGroup) || textEmpty(stateGroup)){

            Toast.makeText(getApplicationContext(), "Todos os campos devem ser preenchidos",
                    Toast.LENGTH_SHORT).show();
        } else {
            if (countGroup.equals("0")){
                Toast.makeText(getApplicationContext(), "Quantidade deve ser maios que zero",
                        Toast.LENGTH_SHORT).show();
            } else {
                Group group = new Group(titleGroug, descGroup, motoCat, cityGroup, stateGroup, countGroup, 1 );
                register(group);
            }
        }

    }

    private boolean textEmpty(String txt){
        return TextUtils.isEmpty(txt);
    }

    public void increaseNumber(View view){
        minimumInteger = minimumInteger + 1;
        display(minimumInteger);
    }

    public void decreaseNumber(View view){
        if (minimumInteger != 0){
            minimumInteger = minimumInteger - 1;
            display(minimumInteger);
        }
    }

    @SuppressLint("SetTextI18n")
    private void display(int number){
        TextView numberCount = findViewById(R.id.contador);
        numberCount.setText("" + number);
    }

    private void register(Group group){

        Retrofit retrofit = APIMethod.API();

        GroupOperation service = retrofit.create(GroupOperation.class);
        Call<APIResponse> send = service.registerGroup(group);
        send.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                APIResponse apiResponse = response.body();

                assert apiResponse != null;
                if (apiResponse.getStatusApi().equals(200)){
                    Toast.makeText(getApplicationContext(),
                            apiResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                Log.e("ERROR ", t.getMessage());
            }
        });

    }
}
