package com.moto.aiolo.motoclubproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.moto.aiolo.motoclubproject.Interface.UserOperation;
import com.moto.aiolo.motoclubproject.Model.ResponseModel.APIResponse;
import com.moto.aiolo.motoclubproject.Model.User;
import com.moto.aiolo.motoclubproject.Mthods.APIMethod;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterUser extends AppCompatActivity {

    private Spinner spinner;
    private EditText userName;
    private EditText lastName;
    private EditText city;
    private EditText state;
    private EditText motoCycle;
    private EditText emailUser;
    private EditText pass;
    private EditText confirmPass;
    private Button register;

    Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        spinner = findViewById(R.id.marital_status);
        userName = findViewById(R.id.name_user);
        lastName = findViewById(R.id.last_name);
        city = findViewById(R.id.city);
        state = findViewById(R.id.state);
        motoCycle = findViewById(R.id.motocycle);
        emailUser = findViewById(R.id.email_user);
        pass = findViewById(R.id.pass_user);
        confirmPass = findViewById(R.id.confirm_pass_user);
        register = findViewById(R.id.buttom_register);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.martialState, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        final String[] martial_state = new String[1];

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                martial_state[0] = selectedItem;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attempRegister(martial_state[0]);
            }
        });

    }

    private boolean textEmpty(String txt){
        return TextUtils.isEmpty(txt);
    }

    private boolean checkEmail(String email){
        return email.contains("@");
    }

    private boolean checkEqualPass(String pass1, String pass2){
        return pass1.equals(pass2);
    }

    private void attempRegister(String s){

        String name = userName.getText().toString();
        String last_name = lastName.getText().toString();
        String mCity = city.getText().toString();
        String mState = state.getText().toString();
        String mMotocycle = motoCycle.getText().toString();
        String email = emailUser.getText().toString();
        String mPass = pass.getText().toString();
        String mConfirmPass = confirmPass.getText().toString();
        String martialState = s;

        if (textEmpty(name) || textEmpty(last_name) || textEmpty(mCity) || textEmpty(mState) || textEmpty(mMotocycle)
                || textEmpty(email) || textEmpty(mPass) || textEmpty(mConfirmPass) || martialState.equals("Estado civil")){

            Toast.makeText(getApplicationContext(), "Todos os campos devem ser preenchidos", Toast.LENGTH_SHORT).show();

        } else {
            if (!checkEmail(email)){

                Toast.makeText(getApplicationContext(), "Email invalido", Toast.LENGTH_SHORT).show();

            } else {
                if (!checkEqualPass(mPass, mConfirmPass)){

                    Toast.makeText(getApplicationContext(), "As senhas não são iguais", Toast.LENGTH_SHORT).show();

                } else {
                    User user = new User(name,last_name, mCity, mState, martialState, mMotocycle, email, mPass);
                    registerUsers(user);
                }
            }
        }

    }

    public void registerUsers(User user){

        Retrofit retrofit = APIMethod.API();

        UserOperation service = retrofit.create(UserOperation.class);
        Call<APIResponse> send = service.saveUser(user);
        send.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                APIResponse APIResponse = response.body();

                assert APIResponse != null;
                if (APIResponse.getStatusApi().equals(200)){
                    String texto = "Usuario criado com sucesso";
                    Intent intent = new Intent(context, Login.class);
                    ModalConfirm(context, texto, intent);
                } else {
                    String texto = "Usuario nao pode ser criado";
                    ModalFail(context, texto);
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                Log.d("ERROR_POST", t.getMessage());
            }
        });
    }

    private void ModalConfirm(Context context, String text, final Intent intent){

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.modal_custom_confirm);

        TextView texto = dialog.findViewById(R.id.confirm_id);
        Button close = dialog.findViewById(R.id.buttom_close_confirm);

        texto.setText(text);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                startActivity(intent);
                finish();
            }
        });

        dialog.show();
    }

    private void ModalFail(Context context, String text){
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.modal_custom_confirm);

        TextView texto = dialog.findViewById(R.id.confirm_id);
        Button close = dialog.findViewById(R.id.buttom_close_confirm);

        texto.setText(text);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}