package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.calculator.R;

import org.w3c.dom.Text;

import java.util.List;

import Model.Income;

public class ListViewNoteAdapter extends ArrayAdapter {
    List<Income> listIncome;

    public ListViewNoteAdapter(@NonNull Context context, @NonNull List<Income> listIncome) {
        super(context, 0, listIncome);
        this.listIncome = listIncome;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Income income = (Income) getItem(position);

        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_note_item, parent, false);
        }

        TextView tv_Income = (TextView)convertView.findViewById(R.id.tv_total_income);
        TextView tv_Outcome = (TextView)convertView.findViewById(R.id.tv_total_outcome);

        tv_Income.setText("" + income);

        return super.getView(position, convertView, parent);
    }
}
