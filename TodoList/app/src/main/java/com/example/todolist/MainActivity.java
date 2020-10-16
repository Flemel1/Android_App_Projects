package com.example.todolist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Connection.DBHelper;
import Adapter.AdapterListView;
import Model.ModelList;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    AdapterListView adapter;
    ListView listTask;
    FloatingActionButton fabAdd;
    View view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listTask = (ListView)findViewById(R.id.listTask);
        fabAdd = (FloatingActionButton)findViewById(R.id.fab_add);
        dbHelper = new DBHelper(this);
        loadListTask();
    }

    private void choose() {
        if (view.getId() == R.id.fab_add){
            Log.e("FloatingActionButton", "Ini Floating Action Button");
        }
    }


    private void loadListTask(){
        ArrayList<ModelList> taskList = dbHelper.getAllTask();
        if (adapter == null){
            adapter = new AdapterListView(this,taskList);
            listTask.setAdapter(adapter);
        }
        else {
            adapter.clear();
            adapter.addAll(taskList);
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        //Change menu icon color
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.black), PorterDuff.Mode.SRC_IN);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add_task:
                AlertDialog deleteAllDialog = new AlertDialog.Builder(this)
                        .setTitle("Hapus Semua Task")
                        .setMessage("Apakah Anda Yakin Ingin Menghapus Semua Task?")
                        .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbHelper.deleteAll();
                                loadListTask();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                deleteAllDialog.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void insertTask(View view){
        final EditText taskEditText = new EditText(this);
        final EditText taskEditText2 = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Tambah Tugas Baru")
                .setMessage("Apa yang ingin anda lakukan?")
                .setView(taskEditText)
                .setPositiveButton("Next", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AlertDialog dialogInformation = new AlertDialog.Builder(MainActivity.this)
                                .setTitle("Tambah Keterangan Tugas")
                                .setMessage("Isi keterangan tugas")
                                .setView(taskEditText2)
                                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String task = taskEditText.getText().toString();
                                        String information = taskEditText2.getText().toString();
                                        dbHelper.insertNewTask(task,information);
                                        loadListTask();
                                        Log.e("onClick", "Berhasil");
                                    }
                                })
                                .setNegativeButton("Cancel", null)
                                .create();
                        dialogInformation.show();
                    }
                })
                .setNegativeButton("Cancel",null)
                .create();
        dialog.show();
    }

    public void deleteTask(View view){
        View parent = (View)view.getParent();
        final TextView taskTextView = (TextView)parent.findViewById(R.id.titleTask);

        int id = taskTextView.getId();;
        Resources res = parent.getResources();
        final String idString = res.getResourceEntryName(id);
        Log.e("String", (String) taskTextView.getText());
        AlertDialog deleteConfirm = new AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Apakah anda yakin ingin menghapus tugas ini?")
                .setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("Resource ID", idString);
                        String task = String.valueOf(taskTextView.getText());
                        dbHelper.deleteTask(task);
                        loadListTask();
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        deleteConfirm.show();
    }

    public void editTask(View view){
        View parent = (View)view.getParent();
        final TextView taskTextView = (TextView)parent.findViewById(R.id.titleTask);
        final EditText taskEditText = new EditText(this);
        Log.e("msg", (String)taskTextView.getText());
        taskEditText.setText(taskTextView.getText().toString());
        AlertDialog dialogEdit = new AlertDialog.Builder(this)
                .setTitle("Edit")
                .setView(taskEditText)
                .setMessage("Apakah anda ingin mengganti tugas ini?")
                .setPositiveButton("Edit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.e("onClick", taskTextView.getText().toString());
                        String oldTask = taskTextView.getText().toString();
                        String newTask = taskEditText.getText().toString();
                        dbHelper.updateTask(oldTask,newTask);
                        Log.e("msg","Sukses update data");
                        loadListTask();
                    }
                }).setNegativeButton("Cancel", null)
                .create();
        dialogEdit.show();
    }

    @Override
    protected void onDestroy() {
        Log.e("onDestroy", "Berhasil dihancurkan");
        super.onDestroy();
    }
}
