package com.example.create;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CalendarApp extends RecyclerView.Adapter<CalendarViewHolder>{
    private final ArrayList<String> daysOfMonth;
    private List<LocalDate> markedDates ;

    private final OnItemListener onItemListener;

    public CalendarApp(ArrayList<String> daysOfMonth, List<LocalDate> markedDates, OnItemListener onItemListener) {
        this.daysOfMonth = daysOfMonth;
        this.markedDates = markedDates;
        this.onItemListener = onItemListener;
    }


    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.calendar, parent, false);
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        layoutParams.height = (int) (parent.getHeight() * 0.166666666);
        return new CalendarViewHolder(view, onItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        String dayString = daysOfMonth.get(position);
        if (!dayString.isEmpty()) {
            LocalDate currentDate = LocalDate.parse(dayString);
            holder.dayOfMonth.setText(String.valueOf(currentDate.getDayOfMonth()));

            if (markedDates.contains(currentDate)) {
                holder.dayOfMonth.setBackgroundColor(Color.WHITE);
            } else {
                holder.dayOfMonth.setBackgroundColor(Color.TRANSPARENT);
            }
        } else {
            holder.dayOfMonth.setText("");
            holder.dayOfMonth.setBackgroundColor(Color.TRANSPARENT);
        }
    }



    @Override
    public int getItemCount()
    {
        return daysOfMonth.size();
    }

    public interface  OnItemListener
    {
        void onItemClick(int position, String dayText);
    }
}
