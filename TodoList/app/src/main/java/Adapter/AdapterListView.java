package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todolist.R;

import java.util.ArrayList;
import java.util.List;

import Model.ModelList;

public class AdapterListView extends ArrayAdapter {
    public AdapterListView(@NonNull Context context, @NonNull ArrayList<ModelList> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ModelList modelList = (ModelList) getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
        }

        TextView titleTask = (TextView) convertView.findViewById(R.id.titleTask);
        TextView informationTask = (TextView) convertView.findViewById(R.id.informationTask);

        titleTask.setText(modelList.getTaskName());
        informationTask.setText("Deadline: " + modelList.getTaskInformation());

        return convertView;
    }
}
