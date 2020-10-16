package com.example.mycoffeeshop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mycoffeeshop.adapter.OrderAdapter;
import com.example.mycoffeeshop.connection.DBAccess;
import com.example.mycoffeeshop.model.Barang;

import java.util.ArrayList;
import java.util.List;

public class list_order extends AppCompatActivity {
    private RecyclerView recyclerView_order;
    private static TextView tv_total;
    private Button btn_order_now;
    private LinearLayoutManager linearLayoutManager;
    private OrderAdapter adapter;
    private static int total;
    private static List<Barang> list_nota;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_order);
        recyclerView_order = (RecyclerView) findViewById(R.id.rc_order);
        tv_total = (TextView) findViewById(R.id.tv_total);
        btn_order_now = (Button) findViewById(R.id.btn_order);
        linearLayoutManager = new LinearLayoutManager(list_order.this);
        recyclerView_order.setLayoutManager(linearLayoutManager);
        loadData();
        btn_order_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (total != 0){
                    DBAccess db = DBAccess.getInstance(getApplicationContext());
                    db.open();
                    for (Barang barang : list_nota) {
                        String name = barang.getNama_barang();
                        int quantity = barang.getQuantity();
                        int price = barang.getHarga_barang();
                        db.insertToNota(name,quantity,price);
                    }
                    db.close();
                    Intent intent = new Intent(getApplicationContext(), form_nota.class);
                    intent.putExtra("total_bayar", total);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Silakan Order Terlebih Dahulu", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public static void totalPrice(List<Barang> list_order) {
        total = 0;
        list_nota = new ArrayList<Barang>();
        for (Barang barang : list_order){
            if (barang.getQuantity() != 0){
                Log.e("msg", "Nama barang " + barang.getNama_barang() +
                        "Harga Barang " + barang.getHarga_barang() +
                        "Quantity Barang " + barang.getQuantity());
                total += barang.getHarga_barang() * barang.getQuantity();
                list_nota.add(barang);
            }
        }
        tv_total.setText("Total: Rp." + total);
    }

    private void loadData() {
        DBAccess db = DBAccess.getInstance(this);
        db.open();
        List<Barang> order_list = db.getBarang();
        adapter = new OrderAdapter(this, order_list);
        recyclerView_order.setAdapter(adapter);
        db.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
        tv_total.setText("Total: Rp.0");
    }
}