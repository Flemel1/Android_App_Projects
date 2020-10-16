package com.example.mycoffeeshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mycoffeeshop.R;
import com.example.mycoffeeshop.model.Barang;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class NotaAdapter extends ArrayAdapter {
    public NotaAdapter(@NonNull Context context, @NonNull List<Barang> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Barang barang = (Barang) getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_item_order, parent, false);
        }

        TextView name = (TextView) convertView.findViewById(R.id.tv_item_name_order);
        TextView quantity = (TextView) convertView.findViewById(R.id.tv_item_quantity_order);
        TextView price = (TextView) convertView.findViewById(R.id.tv_item_price_order);

        name.setText(barang.getNama_barang());
        quantity.setText(barang.getQuantity() + " Pcs");
        price.setText("@" + barang.getHarga_barang());

        return convertView;
    }
}
