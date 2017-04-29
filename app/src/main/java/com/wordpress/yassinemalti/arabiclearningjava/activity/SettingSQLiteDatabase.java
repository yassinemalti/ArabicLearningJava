package com.wordpress.yassinemalti.arabiclearningjava.activity;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by yassinemalti on 07/09/2016.
 */
public class SettingSQLiteDatabase {

    DBS mySettingDatabase;
    private static final String TAG = "PrincipaleActivity";

    public SettingSQLiteDatabase(Context context) {
        mySettingDatabase = new DBS(context);
        Log.d(TAG, "This is SettingSQLiteDatabase method");
    }

    public long dataInsertParameter(String parameterKey, String parameterValue) {
        SQLiteDatabase sqLiteDatabase = mySettingDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBS.parameterKey,parameterKey);
        contentValues.put(DBS.parameterValue,parameterValue);
        long id = sqLiteDatabase.insert(DBS.settingTableName,null,contentValues);
        Log.d(TAG, "This is dataInsertParameter method");
        Log.d(TAG, "parameterKey = " + parameterKey + ", parameterValue = " + parameterValue);
        return id;
    }

    public String dataReadParameter(String parameterKey) {
        SQLiteDatabase sqLiteDatabase = mySettingDatabase.getWritableDatabase();
        String[] columns = {DBS.parameterID, DBS.parameterKey, DBS.parameterValue};
        Cursor cursor = sqLiteDatabase.query(DBS.settingTableName, columns, DBS.parameterKey +
                " = '" + parameterKey + "'",null,null,null,null);
        StringBuffer stringBuffer = new StringBuffer();
        while (cursor.moveToNext()) {
            int indexParameterValue = cursor.getColumnIndex(DBS.parameterValue);
            String parameterValue = cursor.getString(indexParameterValue);
            stringBuffer.append(parameterValue);
        }
        Log.d(TAG, "This is dataReadParameter method");
        Log.d(TAG, "parameterValue = " + stringBuffer.toString());
        return stringBuffer.toString();
    }

    public void dataUpdateParameter(String parameterKey, String parameterValue) {
        SQLiteDatabase sqLiteDatabase = mySettingDatabase.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBS.parameterValue,parameterValue);
        sqLiteDatabase.update(DBS.settingTableName, contentValues,
                DBS.parameterKey + " = ?", new String[]{parameterKey});
        Log.d(TAG, "This is dataUpdateParameter method");
        Log.d(TAG, "parameterKey = " + parameterKey + ", parameterValue = " + parameterValue);
    }

    public void dataDeleteParameter(String parameterKey) {
        SQLiteDatabase sqLiteDatabase = mySettingDatabase.getWritableDatabase();
        sqLiteDatabase.delete(DBS.settingTableName,
                DBS.parameterKey + " = ?", new String[]{parameterKey});
        Log.d(TAG, "This is dataDeleteParameter method");
        Log.d(TAG, "parameterKey = " + parameterKey );
    }

    static class DBS extends SQLiteOpenHelper {

        private static final String dataBaseName = "KoooraSQLiteDatabase";
        private static final int dataBaseVersion = 1;

        private static final String settingTableName = "settingTable";
        private static final String parameterID = "parameterID";
        private static final String parameterKey = "parameterKey";
        private static final String parameterValue = "parameterValue";

        private static final String createSettingTable = "CREATE TABLE " + settingTableName + " (" +
                parameterID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + parameterKey + " VARCHAR(25), "+
                parameterValue + " VARCHAR(50));";

        private static final String dropSettingTable = "DROP TABLE IF EXISTS " + settingTableName;

        private Context context;

        public DBS(Context context) {
            super(context, dataBaseName, null, dataBaseVersion);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {

            try{
                sqLiteDatabase.execSQL(createSettingTable);
                Log.d(TAG, "This is onCreate method");
            }catch (SQLException e) {
                Log.d(TAG, "Due to: " + e);
            }

        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

            try{
                Log.d(TAG, "This is onUpgrade method");
                sqLiteDatabase.execSQL(dropSettingTable);
                onCreate(sqLiteDatabase);
            }catch (SQLException e) {
                Log.d(TAG, "Due to: " + e);
            }

        }

    }

}
