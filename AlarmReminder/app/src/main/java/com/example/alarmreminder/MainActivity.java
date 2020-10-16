package com.example.alarmreminder;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.AsyncTaskLoader;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.app.ProgressDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alarmreminder.Adapter.AlarmCursorAdapter;
import com.example.alarmreminder.Data.AlarmReminderModel;
import com.example.alarmreminder.Network.AlarmReminderDBHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private ListView list;
    private FloatingActionButton btn_add;
    private AlarmCursorAdapter cursorAdapter;
    AlarmReminderDBHelper alarmReminderDbHelper = new AlarmReminderDBHelper(this);
    ProgressDialog prgDialog;
    TextView reminderText;

    private String alarmTitle = "";

    private static final int VEHICLE_LOADER = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reminderText = (TextView) findViewById(R.id.reminderText);
        list = (ListView) findViewById(R.id.list);
        btn_add = (FloatingActionButton) findViewById(R.id.btn_add);
        cursorAdapter = new AlarmCursorAdapter(this,null);
        View emptyView = findViewById(R.id.empty_layout);
        list.setEmptyView(emptyView);
        list.setAdapter(cursorAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, ActivityAddReminder.class);

                Uri currentVehicleUri = ContentUris.withAppendedId(AlarmReminderModel.AlarmReminderEntry.CONTENT_URI, id);

                // Set the URI on the data field of the intent
                intent.setData(currentVehicleUri);

                startActivity(intent);
            }
        });
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(v.getContext(), ActivityAddReminder.class);
//                startActivity(intent);
                addReminderTitle();
            }
        });
        getSupportLoaderManager().initLoader(VEHICLE_LOADER, null, this);
    }

    private void addReminderTitle() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Set Reminder Title");

        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT);
        builder.setView(input);

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (input.getText().toString().isEmpty()){
                    return;
                }

                alarmTitle = input.getText().toString();
                ContentValues values = new ContentValues();

                values.put(AlarmReminderModel.AlarmReminderEntry.KEY_TITLE, alarmTitle);

                Uri newUri = getContentResolver().insert(AlarmReminderModel.AlarmReminderEntry.CONTENT_URI, values);

                restartLoader();

                if (newUri == null) {
                    Toast.makeText(getApplicationContext(), "Setting Reminder Title failed", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Title set successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }


    public void restartLoader(){
        getSupportLoaderManager().restartLoader(VEHICLE_LOADER, null, this);
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = {
                AlarmReminderModel.AlarmReminderEntry._ID,
                AlarmReminderModel.AlarmReminderEntry.KEY_TITLE,
                AlarmReminderModel.AlarmReminderEntry.KEY_DATE,
                AlarmReminderModel.AlarmReminderEntry.KEY_TIME,
                AlarmReminderModel.AlarmReminderEntry.KEY_REPEAT,
                AlarmReminderModel.AlarmReminderEntry.KEY_REPEAT_NO,
                AlarmReminderModel.AlarmReminderEntry.KEY_REPEAT_TYPE,
                AlarmReminderModel.AlarmReminderEntry.KEY_ACTIVE

        };

        CursorLoader cursorLoader = new CursorLoader(this,   // Parent activity context
                AlarmReminderModel.AlarmReminderEntry.CONTENT_URI,   // Provider content URI to query
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);
        return cursorLoader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        cursorAdapter.swapCursor(data);
        if (data.getCount() > 0) {
            reminderText.setVisibility(View.VISIBLE);
        }
        else {
            reminderText.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        cursorAdapter.swapCursor(null);
    }
}