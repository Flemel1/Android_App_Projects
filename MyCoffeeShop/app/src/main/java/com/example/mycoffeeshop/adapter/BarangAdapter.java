package com.example.mycoffeeshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycoffeeshop.R;
import com.example.mycoffeeshop.connection.DBAccess;
import com.example.mycoffeeshop.model.Barang;


import java.util.List;

public class BarangAdapter extends RecyclerView.Adapter<BarangAdapter.ViewHolder> {
    private Context context;
    private List<Barang> list_barang;

    public BarangAdapter(Context context, List<Barang> list_barang) {
        this.context = context;
        this.list_barang = list_barang;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.coffee, null);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name_coffee.setText(list_barang.get(position).getNama_barang());
        holder.price_coffee.setText("" + list_barang.get(position).getHarga_barang());
    }

    @Override
    public int getItemCount() {
        return list_barang.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView image;
        ImageView img_delete;
        TextView name_coffee;
        TextView price_coffee;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_coffee);
            img_delete = (ImageView) itemView.findViewById(R.id.img_delete);
            name_coffee = (TextView) itemView.findViewById(R.id.tv_name_coffee);
            price_coffee = (TextView) itemView.findViewById(R.id.tv_price_coffee);
            img_delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            DBAccess db = DBAccess.getInstance(v.getContext());
            String name = list_barang.get(getAdapterPosition()).getNama_barang();
            db.open();
            db.delete(name);
            Toast.makeText(v.getContext(), "Berhasil Dihapus", Toast.LENGTH_SHORT).show();
            List<Barang> list = db.getBarang();
            list_barang = list;
            notifyDataSetChanged();
            db.close();
        }
    }
}
