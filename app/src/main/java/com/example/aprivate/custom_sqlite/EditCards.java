package com.example.aprivate.custom_sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aprivate.custom_sqlite.data.BaseHelper;
import com.example.aprivate.custom_sqlite.data.BaseShema;

public class EditCards extends AppCompatActivity {
    private static final String TAG = "EditCards ======/:";

    private EditText mTypeCardEditText;
    private EditText mBankCardEditText;
    private EditText mNumberCardEditText;
    private BaseHelper mDbHelper;
    private Button mAddButton;
    private Button mDeleteAllButton;
    private SQLiteDatabase mSQL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_cards);

        mDbHelper = new BaseHelper(this);
        mTypeCardEditText = (EditText) findViewById(R.id.type_id);
        mBankCardEditText = (EditText) findViewById(R.id.bank_id);
        mNumberCardEditText = (EditText) findViewById(R.id.number_id);

        mAddButton = (Button) findViewById(R.id.add_button);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                writeInBd();
                readBd(mSQL);
            }
        });

        //TODO
        mDeleteAllButton = (Button) findViewById(R.id.delete_all);
        mDeleteAllButton.setEnabled(false);
        mDeleteAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deleteDatabase(BaseShema.CardTable.TABLE_NAME);
                deleteAll(mSQL);
            }
        });
    }

    void writeInBd(){
        mSQL = mDbHelper.getWritableDatabase();

        Cards cards = new Cards();
        cards.setmType(mTypeCardEditText.getText().toString());
        cards.setmBank(mBankCardEditText.getText().toString());
        cards.setmNumber(mNumberCardEditText.getText().toString());

        ContentValues values = new ContentValues();
        values.put(BaseShema.Cols.UUID, cards.getmID().toString());
        values.put(BaseShema.Cols.TYPE, cards.getmType());
        values.put(BaseShema.Cols.BANK, cards.getmBank());
        values.put(BaseShema.Cols.NUMBER, cards.getmNumber());

        mSQL.insert(BaseShema.CardTable.TABLE_NAME, null, values);
    }

    void readBd(SQLiteDatabase db){
        db = mDbHelper.getReadableDatabase();

        String projection [] = {
                BaseShema.Cols.UUID,
                BaseShema.Cols.TYPE,
                BaseShema.Cols.BANK,
                BaseShema.Cols.NUMBER };

        Cursor cursor = db.query(BaseShema.CardTable.TABLE_NAME,
                        projection,
                        null,
                        null,
                        null,
                        null,
                        null);

        try {
            int targetUUID = cursor.getColumnIndex(BaseShema.Cols.UUID);
            int targetType = cursor.getColumnIndex(BaseShema.Cols.TYPE);
            int targetBank = cursor.getColumnIndex(BaseShema.Cols.BANK);
            int targetNumber = cursor.getColumnIndex(BaseShema.Cols.NUMBER);

            while (cursor.moveToNext()) {
                String uuid = cursor.getString(targetUUID);
                String type = cursor.getString(targetType);
                String bank = cursor.getString(targetBank);
                String number = cursor.getString(targetNumber);

                Log.d(TAG, uuid);
                Log.d(TAG, type);
                Log.d(TAG, bank + "");
                Log.d(TAG, number);
                Log.d(TAG, cursor.getCount()+ "");
            }
        } finally {
            cursor.close();
        }
    }

    private void deleteAll(SQLiteDatabase mSQL) {
        String target = BaseShema.Cols.UUID;
        mSQL.delete(BaseShema.CardTable.TABLE_NAME, target, null);
    }
}
