package com.moto.aiolo.motoclubproject.SQLITE;

import android.provider.BaseColumns;

public final class UserContract {
    private UserContract(){}

    public static class UserEntry implements BaseColumns{
        public static final String TABLE_NAME = "usuario";
        public static final String COLUMN_NAME_USUARIO = "name_user";
        public static final String COLUMN_NAME_LAST_NAME = "last_name";
        public static final String COLUMN_NAME_CITY = "city";
        public static final String COLUMN_NAME_STATE = "state";
        public static final String COLUMN_NAME_MARITIAL_STATE = "maritial_state";
        public static final String COLUMN_NAME_MOTOCYCLE = "motocycle";
        public static final String COLUMN_NAME_EMAIL_USER = "email_user";
    }
}
