package com.moto.aiolo.motoclubproject;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import com.moto.aiolo.motoclubproject.Interface.UserOperation;
import com.moto.aiolo.motoclubproject.Model.ResponseModel.UserResponse;
import com.moto.aiolo.motoclubproject.Model.User;
import com.moto.aiolo.motoclubproject.Mthods.APIMethod;
import com.moto.aiolo.motoclubproject.SQLITE.HELPER.UserDbHelper;
import com.moto.aiolo.motoclubproject.SQLITE.UserContract;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Login extends AppCompatActivity {

    private EditText login;
    private EditText pass;
    private Button loginUser;
    private TextView registerUser;
    Context context = this;

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
                Intent intent = new Intent(context, RegisterUser.class);
                startActivity(intent);
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

        retrofit = APIMethod.API();
        UserOperation userOperation = retrofit.create(UserOperation.class);
        Call<UserResponse> response = userOperation.loginUser(user);
        response.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(@NonNull Call<UserResponse> call, @NonNull Response<UserResponse> response) {
                UserResponse userResponse = response.body();

                assert userResponse != null;
                if (userResponse.getStatus().equals(302)){
                    Toast.makeText(getApplicationContext(), "Usuario " + userResponse.getNameUser() +
                            " encontrado", Toast.LENGTH_SHORT).show();

                    saveInDB(userResponse);
                } else {
                    Toast.makeText(getApplicationContext(), userResponse.getMsg(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<UserResponse> call, @NonNull Throwable t) {
                Log.d("ERROR_POST", t.getMessage());
            }
        });
    }

    public void saveInDB(UserResponse userResponse){

        UserDbHelper userDbHelper = new UserDbHelper(getApplicationContext());
        SQLiteDatabase db = userDbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(UserContract.UserEntry.COLUMN_NAME_USUARIO, userResponse.getNameUser());
        values.put(UserContract.UserEntry.COLUMN_NAME_LAST_NAME, userResponse.getLastName());
        values.put(UserContract.UserEntry.COLUMN_NAME_CITY, userResponse.getUserCity());
        values.put(UserContract.UserEntry.COLUMN_NAME_STATE, userResponse.getUserState());
        values.put(UserContract.UserEntry.COLUMN_NAME_MOTOCYCLE, userResponse.getUserMotocycle());
        values.put(UserContract.UserEntry.COLUMN_NAME_MARITIAL_STATE, userResponse.getMaritialState());
        values.put(UserContract.UserEntry.COLUMN_NAME_EMAIL_USER, userResponse.getEmailUser());

        long newRowId = db.insert(UserContract.UserEntry.TABLE_NAME, null, values);

        if (newRowId != -1){
            Intent intent = new Intent(context, MainActivity.class);
            startActivity(intent);
            finish();
        }

        Log.d("RUD", String.valueOf(newRowId));

    }

}
