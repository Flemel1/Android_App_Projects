package com.example.calculator;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import Adapter.ListViewAdapter;
import ConnectionDB.DatabaseAccess;
import Model.Income;

public class DetailOutcomeActivity extends AppCompatActivity {

    public static String getMonth;
    ListView listViewDetailOutcome;
    FloatingActionButton fabAdd;
    TextView txtTotal;
    List<Income> listDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_outcome);
        listViewDetailOutcome = (ListView)findViewById(R.id.listDetailOutcome);
        fabAdd = (FloatingActionButton)findViewById(R.id.fab_add);
        txtTotal = (TextView)findViewById(R.id.txtTotal);
        listViewDetailOutcome.setDividerHeight(100);
        txtTotal.setTextSize(20);
        Intent intent = getIntent();
        getMonth = intent.getStringExtra("Month");
        loadDetailOutcome();
        setTotal();
    }

    private void setTotal() {
        int total = 0;
        for (int i = 0; i<listDetail.size();i++){
            Income income = listDetail.get(i);
            total += income.getMoney();
        }
        txtTotal.setText("Total: Rp." + total);
    }

    private void loadDetailOutcome() {
        DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        listDetail = databaseAccess.getDetail(getMonth);
        databaseAccess.close();
        ListViewAdapter adapter = new ListViewAdapter(this,listDetail);
        listViewDetailOutcome.setAdapter(adapter);
    }


    public void insert(View view) {
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        final EditText edMoney = new EditText(this);
        edMoney.setInputType(InputType.TYPE_CLASS_NUMBER);
        AlertDialog dialogAdd = new AlertDialog.Builder(this)
                .setTitle("Tambah Data")
                .setMessage("Masukan jumlah uang")
                .setView(edMoney)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int money = Integer.parseInt(edMoney.getText().toString());
                        Date date = Calendar.getInstance().getTime();
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        String formattedDate = simpleDateFormat.format(date);
                        databaseAccess.insertOutcome(getMonth,money,formattedDate);
                        databaseAccess.close();
                        loadDetailOutcome();
                        setTotal();
                    }
                }).setNegativeButton("Cancel",null).create();
        dialogAdd.show();
    }

    public void update(View view) {
        final DatabaseAccess databaseAccess = DatabaseAccess.getInstance(this);
        databaseAccess.open();
        View parent = (View) view.getParent();
        final TextView ID = (TextView) parent.findViewById(R.id.txtID);
        final TextView MoneyEdit = (TextView) parent.findViewById(R.id.txtMoney);
        final EditText edUpdate = new EditText(this);
        edUpdate.setText(MoneyEdit.getText().toString());
        AlertDialog updateDialog = new AlertDialog.Builder(this)
                .setTitle("Edit")
                .setMessage("Apakah anda ingin mengupdate data ini")
                .setView(edUpdate)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String id = ID.getText().toString();
                        int money = Integer.parseInt(edUpdate.getText().toString());
                        databaseAccess.updateOutcome(id,money);
                        databaseAccess.close();
                        loadDetailOutcome();
                        setTotal();
                    }
                }).setNegativeButton("Cancel", null).create();
        updateDialog.show();
    }
}
