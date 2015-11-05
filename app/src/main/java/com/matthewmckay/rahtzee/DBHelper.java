package com.matthewmckay.rahtzee;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by matthewmckay on 11/1/15.
 */
public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "Rahtzee.db";

    public static final String PARENT_CHILD_MSG = "Parent_Child_Msg_Pairs_Table";
    public static final String P_C_M_Parent = "Parent_Id";
    public static final String P_C_M_Child = "Child_Id";

    public static final String MUTED_MSGS = "Muted_Messages_Table";
    public static final String M_M_MSG_ID = "Message_Id";
    public static final String M_M_MSG_ROOT_ID = "Root_Mute_Msg_Id";

    DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                        PARENT_CHILD_MSG + " (" +
                        P_C_M_Parent + " integer, " +
                        P_C_M_Child + " integer, " +
                        "primary key (" +
                        P_C_M_Parent + ", " +
                        P_C_M_Parent + "))"
        );
        db.execSQL("create table " +
                        MUTED_MSGS + " (" +
                        M_M_MSG_ID + " integer, " +
                        M_M_MSG_ROOT_ID + " integer, " +
                        "primary key (" +
                        M_M_MSG_ID + ", " +
                        M_M_MSG_ROOT_ID + "))"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + PARENT_CHILD_MSG);
        db.execSQL("drop table if exists " + MUTED_MSGS);
    }
}
