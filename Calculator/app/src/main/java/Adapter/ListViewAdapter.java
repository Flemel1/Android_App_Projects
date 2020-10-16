package Adapter;

import com.example.calculator.DetailOutcomeActivity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.calculator.R;

import java.util.ArrayList;
import java.util.List;

import Model.Income;

public class ListViewAdapter extends ArrayAdapter {


    public ListViewAdapter(@NonNull Context context, @NonNull List<Income> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Income income = (Income) getItem(position);


        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_list, parent, false);
        }


        TextView ID = (TextView) convertView.findViewById(R.id.txtID);
        TextView bulan = (TextView) convertView.findViewById(R.id.txtBulan);
        TextView money = (TextView) convertView.findViewById(R.id.txtMoney);
        TextView date = (TextView) convertView.findViewById(R.id.txtDate);

        ID.setText(String.valueOf(income.getID()));
        bulan.setText("Bulan: " + income.getMonth());
        money.setText("Money: Rp." + income.getMoney());
        date.setText("Tanggal: " + income.getDate());
        return convertView;
    }
}
