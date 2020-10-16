package com.example.mycoffeeshop.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mycoffeeshop.R;
import com.example.mycoffeeshop.list_order;
import com.example.mycoffeeshop.model.Barang;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Activity context;
    private List<Barang> order_list;

    public OrderAdapter(Activity context, List<Barang> order_list){
        this.context = context;
        this.order_list = order_list;
    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.order_item, null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, final int position) {
        holder.coffee_name.setText(order_list.get(position).getNama_barang());
        holder.coffee_price.setText("" + order_list.get(position).getHarga_barang());
        holder.quantity.setText("" + order_list.get(position).getQuantity());
        holder.image_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final EditText input_quantity = new EditText(context);
                input_quantity.setInputType(InputType.TYPE_CLASS_NUMBER);
                input_quantity.setText("" + order_list.get(position).getQuantity());
                AlertDialog dialog = new AlertDialog.Builder(context)
                        .setView(input_quantity)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Barang barang = order_list.get(position);
                                int quantity = Integer.parseInt(input_quantity.getText().toString().trim());
                                barang.setQuantity(quantity);
                                order_list.set(position, barang);
                                notifyDataSetChanged();
                                list_order.totalPrice(order_list);
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return order_list.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        ImageView image_add;
        TextView coffee_name;
        TextView coffee_price;
        TextView quantity;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.img_coffee);
            image_add = (ImageView) itemView.findViewById(R.id.img_add);
            coffee_name = (TextView) itemView.findViewById(R.id.tv_name_coffee);
            coffee_price = (TextView) itemView.findViewById(R.id.tv_price_coffee);
            quantity = (TextView) itemView.findViewById(R.id.tv_quantity_order);
        }
    }
}
