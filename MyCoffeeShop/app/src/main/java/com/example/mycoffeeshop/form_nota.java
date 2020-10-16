package com.example.mycoffeeshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycoffeeshop.adapter.NotaAdapter;
import com.example.mycoffeeshop.connection.DBAccess;
import com.example.mycoffeeshop.model.Barang;

import java.util.List;

public class form_nota extends AppCompatActivity {
    TextView tv_total_bayar;
    ListView list_nota_konsumen;
    Button btn_done;
    Bundle bundle;
    private List<Barang> list_order;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_nota);
        tv_total_bayar = (TextView) findViewById(R.id.tv_total_bayar);
        list_nota_konsumen = (ListView) findViewById(R.id.list_barang_nota);
        btn_done = (Button) findViewById(R.id.btn_done);
        if (bundle == null) {
            bundle = getIntent().getExtras();
            int total = bundle.getInt("total_bayar");
            tv_total_bayar.setText("Total Bayar: Rp." + total);
        }
        loadData();
    }

    private void loadData() {
        DBAccess db = DBAccess.getInstance(this);
        db.open();
        list_order = db.getNota();
        NotaAdapter adapter = new NotaAdapter(this, list_order);
        list_nota_konsumen.setAdapter(adapter);
        db.close();
    }

    public void deleteNota(View view) {
        DBAccess db = DBAccess.getInstance(this);
        db.open();
        db.deleteNota();
        db.close();
        Toast.makeText(getApplicationContext(), "Transaksi Selesai", Toast.LENGTH_SHORT).show();
    }
}