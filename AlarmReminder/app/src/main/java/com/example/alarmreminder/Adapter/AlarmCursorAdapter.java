package com.example.alarmreminder.Adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.example.alarmreminder.Data.AlarmReminderModel;
import com.example.alarmreminder.R;

public class AlarmCursorAdapter extends CursorAdapter {
    TextView tv_title;
    TextView tv_date_time;
    TextView tv_repeat;
    ImageView img_thumbnail;
    ImageView img_active;
    private ColorGenerator mColorGenerator = ColorGenerator.DEFAULT;
    private TextDrawable mDrawableBuilder;

    public AlarmCursorAdapter(Context context, Cursor c) {
        super(context, c, 0);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.alarm_items, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        tv_date_time = (TextView) view.findViewById(R.id.tv_date_time);
        tv_repeat = (TextView) view.findViewById(R.id.tv_repeat);
        img_thumbnail = (ImageView) view.findViewById(R.id.thumbnail_image);
        img_active = (ImageView) view.findViewById(R.id.icon_active);

        int titleColumnIndex = cursor.getColumnIndex(AlarmReminderModel.AlarmReminderEntry.KEY_TITLE);
        int dateColumnIndex = cursor.getColumnIndex(AlarmReminderModel.AlarmReminderEntry.KEY_DATE);
        int timeColumnIndex = cursor.getColumnIndex(AlarmReminderModel.AlarmReminderEntry.KEY_TIME);
        int repeatColumnIndex = cursor.getColumnIndex(AlarmReminderModel.AlarmReminderEntry.KEY_REPEAT);
        int repeatNoColumnIndex = cursor.getColumnIndex(AlarmReminderModel.AlarmReminderEntry.KEY_REPEAT_NO);
        int repeatTypeColumnIndex = cursor.getColumnIndex(AlarmReminderModel.AlarmReminderEntry.KEY_REPEAT_TYPE);
        int activeColumnIndex = cursor.getColumnIndex(AlarmReminderModel.AlarmReminderEntry.KEY_ACTIVE);

        String title = cursor.getString(titleColumnIndex);
        String date = cursor.getString(dateColumnIndex);
        String time = cursor.getString(timeColumnIndex);
        String repeat = cursor.getString(repeatColumnIndex);
        String repeatNo = cursor.getString(repeatNoColumnIndex);
        String repeatType = cursor.getString(repeatTypeColumnIndex);
        String active = cursor.getString(activeColumnIndex);


        setReminderTitle(title);
        if (date != null){
            String dateTime = date + " " + time;
            setReminderDateTime(dateTime);
        }else{
            tv_date_time.setText("Date not set");
        }

        if(repeat != null){
            setReminderRepeatInfo(repeat, repeatNo, repeatType);
        }else{
            tv_repeat.setText("Repeat Not Set");
        }

        if (active != null){
            setActiveImage(active);
        }else{
            img_active.setImageResource(R.drawable.ic_baseline_notifications_off_40_black);
        }

    }

    private void setActiveImage(String active) {
        if(active.equals("true")){
           img_active.setImageResource(R.drawable.ic_baseline_notifications_active_40_black);
        }else if (active.equals("false")) {
            img_active.setImageResource(R.drawable.ic_baseline_notifications_off_40_black);
        }
    }

    private void setReminderRepeatInfo(String repeat, String repeatNo, String repeatType) {
        if(repeat.equals("true")){
            tv_repeat.setText("Every " + repeatNo + " " + repeatType + "(s)");
        }else if (repeat.equals("false")) {
            tv_repeat.setText("Repeat Off");
        }
    }

    private void setReminderDateTime(String dateTime) {
        tv_date_time.setText(dateTime);
    }

    private void setReminderTitle(String title) {
        tv_title.setText(title);
        String letter = "A";

        if(title != null && !title.isEmpty()) {
            letter = title.substring(0, 1);
        }

        int color = mColorGenerator.getRandomColor();

        // Create a circular icon consisting of  a random background colour and first letter of title
        mDrawableBuilder = TextDrawable.builder()
                .buildRound(letter, color);
        img_thumbnail.setImageDrawable(mDrawableBuilder);
    }
}
