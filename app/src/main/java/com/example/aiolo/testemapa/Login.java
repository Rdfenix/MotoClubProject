package com.example.aiolo.testemapa;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.aiolo.testemapa.Interface.UserOperation;
import com.example.aiolo.testemapa.Model.ResponseModel.APIResponse;
import com.example.aiolo.testemapa.Model.User;
import com.example.aiolo.testemapa.Mthods.APIMethod;

import io.realm.Realm;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    private EditText login;
    private EditText pass;
    private Button loginUser;
    private TextView registerUser;

    Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login = findViewById(R.id.login);
        pass = findViewById(R.id.pass);
        loginUser = findViewById(R.id.login_buttom);
        registerUser = findViewById(R.id.register_user);

        loginUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AccessUser();
            }
        });

        registerUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Testando aqui", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean textEmpty(String text){
        return TextUtils.isEmpty(text);
    }

    private boolean checckEmail(String email){
        return email.contains("@");
    }

    private void AccessUser(){
        String login_user = login.getText().toString();
        String pass_user = pass.getText().toString();

        if (textEmpty(login_user) || textEmpty(pass_user)){
            Toast.makeText(getApplicationContext(), "Os Campos nao podem estar vazios", Toast.LENGTH_SHORT).show();
        } else {
            if (!checckEmail(login_user)){
                Toast.makeText(getApplicationContext(), "Email invalido", Toast.LENGTH_SHORT).show();
            } else {
                User user = new User(login_user, pass_user);
                attempLogin(user);
            }
        }
    }

    public void attempLogin(User user){

        final String email = user.getEmailUser();

        retrofit = APIMethod.API();
        UserOperation userOperation = retrofit.create(UserOperation.class);
        Call<APIResponse> send = userOperation.loginUser(user);
        send.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                APIResponse apiResponse = response.body();

                assert apiResponse != null;
                if (apiResponse.getStatusApi().equals(302)){
                    Toast.makeText(getApplicationContext(), apiResponse.getMsg(), Toast.LENGTH_SHORT).show();
                } else {
                    if (apiResponse.getStatusApi().equals(404)){
                        Toast.makeText(getApplicationContext(), apiResponse.getMsg(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                Log.d("ERROR_POST", t.getMessage());
            }
        });
    }

    private void saveInDB(){

    }



}
