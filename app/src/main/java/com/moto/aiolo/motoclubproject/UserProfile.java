package com.moto.aiolo.motoclubproject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.moto.aiolo.motoclubproject.SQLITE.HELPER.UserDbHelper;
import com.moto.aiolo.motoclubproject.SQLITE.UserContract;

public class UserProfile extends AppCompatActivity {

    private TextView name;
    private TextView email;
    private TextView city;
    private TextView state;
    private TextView goBack;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.profile_email);
        city = findViewById(R.id.profile_city);
        state = findViewById(R.id.profile_state);
        goBack = findViewById(R.id.profile_exit);

        goBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
            }
        });

        setItemsProfile(name, email, city, state);
    }

    private void setItemsProfile(TextView name, TextView email, TextView city, TextView state){

        String selectQuery = "SELECT " + UserContract.UserEntry.COLUMN_NAME_USUARIO +
                ", " + UserContract.UserEntry.COLUMN_NAME_EMAIL_USER + ", "+
                UserContract.UserEntry.COLUMN_NAME_CITY + ", " +
                UserContract.UserEntry.COLUMN_NAME_STATE +
                " FROM " + UserContract.UserEntry.TABLE_NAME + " LIMIT 1";

        UserDbHelper userDbHelper = new UserDbHelper(getApplicationContext());
        SQLiteDatabase db = userDbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery(selectQuery, null);
        cursor.moveToFirst();

        String userName = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.COLUMN_NAME_USUARIO));
        String userEmail = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.COLUMN_NAME_EMAIL_USER));
        String userCity = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.COLUMN_NAME_CITY));
        String userState = cursor.getString(cursor.getColumnIndex(UserContract.UserEntry.COLUMN_NAME_STATE));

        db.close();

        name.setText(userName);
        email.setText(userEmail);
        city.setText(userCity);
        state.setText(userState);
    }
}
