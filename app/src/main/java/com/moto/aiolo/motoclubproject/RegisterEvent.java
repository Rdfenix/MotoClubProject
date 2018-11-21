package com.moto.aiolo.motoclubproject;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Geocoder;
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

import com.google.android.gms.maps.model.LatLng;
import com.moto.aiolo.motoclubproject.Interface.EventOperation;
import com.moto.aiolo.motoclubproject.Model.Event;
import com.moto.aiolo.motoclubproject.Model.ResponseModel.APIResponse;
import com.moto.aiolo.motoclubproject.Mthods.APIMethod;
import com.moto.aiolo.motoclubproject.SQLITE.HELPER.UserDbHelper;
import com.moto.aiolo.motoclubproject.SQLITE.UserContract;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterEvent extends AppCompatActivity {

    private EditText localEvent;
    private EditText title;
    private EditText hour;
    private Button registerEvent;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_event);

        localEvent = findViewById(R.id.local_event);
        title = findViewById(R.id.title_event);
        hour = findViewById(R.id.horario);
        registerEvent = findViewById(R.id.buttom_register_event);

        registerEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String address = localEvent.getText().toString();
                String titleEvent = title.getText().toString();
                String hourEvent = hour.getText().toString();
                LatLng latLong =  getLocation(getApplicationContext(), address);

                if (textEmpty(address) || textEmpty(titleEvent) || textEmpty(hourEvent)){
                    Toast.makeText(getApplicationContext(), "Todos os campos devem ser preenchidos",
                            Toast.LENGTH_SHORT).show();
                } else {

                    String name = getAllUserName();
                    Event event = new Event(titleEvent, address, hourEvent, name,
                            latLong.latitude, latLong.longitude );
                    saveEvent(event);
                }
            }
        });
    }



    public String getAllUserName() {

        String selectQuery = "SELECT " + UserContract.UserEntry.COLUMN_NAME_USUARIO +
                " FROM " + UserContract.UserEntry.TABLE_NAME + " LIMIT 1";
        UserDbHelper userDbHelper = new UserDbHelper(getApplicationContext());
        SQLiteDatabase db = userDbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);

        cursor.moveToFirst();

        String nameUser = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.COLUMN_NAME_USUARIO));
        StringBuilder converse = new StringBuilder();
        converse.append(nameUser);
        db.close();

        return converse.toString();
    }


    public LatLng getLocation(Context context, String addres){
        Geocoder geocoder = new Geocoder(context);
        List<Address> addressList;
        LatLng item = null;

        try {
            addressList = geocoder.getFromLocationName(addres, 5);
            if (addressList == null){
                return null;
            }
            Address location = addressList.get(0);
            item = new LatLng(location.getLatitude(), location.getLongitude());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return item;
    }

    private boolean textEmpty(String txt){
        return TextUtils.isEmpty(txt);
    }

    public void saveEvent(Event event){

        Retrofit retrofit = APIMethod.API();
        EventOperation eventOperation = retrofit.create(EventOperation.class);
        Call<APIResponse> send = eventOperation.saveEvent(event);
        send.enqueue(new Callback<APIResponse>() {
            @Override
            public void onResponse(@NonNull Call<APIResponse> call, @NonNull Response<APIResponse> response) {
                APIResponse apiResponse = response.body();

                assert apiResponse != null;
                if (apiResponse.getStatusApi().equals(200)){
                    String texto = "Evento criado com sucesso";
                    Intent intent = new Intent(context, EventList.class);
                    ModalConfirm(context, texto, intent);
                } else {
                    String texto = "Falha ao criar evento";
                    ModalFail(context, texto);
                }
            }

            @Override
            public void onFailure(@NonNull Call<APIResponse> call, @NonNull Throwable t) {
                Log.e("ERROR ", t.getMessage());
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