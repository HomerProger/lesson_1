package com.example.lesson1.requestHistory;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson1.R;

import java.util.List;

public class RequestHistoryAdapter extends RecyclerView.Adapter<RequestHistoryAdapter.ViewHolder> {

    private List<CityParcel> data;
    private Context activity;

    public RequestHistoryAdapter(List<CityParcel> data, Context activity) {
        this.data = data;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cities, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CityParcel cityParcel = data.get(position);
        holder.setData(cityParcel.getDate(), cityParcel.getTime(), cityParcel.getCity(), cityParcel.getTemp());
    }

    @Override
    public int getItemCount() {
        return data==null ? 0:data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView date;
        private TextView time;
        private TextView city;
        private TextView temp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            city = itemView.findViewById(R.id.city);
            temp = itemView.findViewById(R.id.temp);

        }

        public void setData(String date, String time, String city, String temp) {
            getDate().setText(date);
            getTime().setText(time);
            getCity().setText(city);
            getTemp().setText(temp);

        }

        public TextView getDate() {
            return date;
        }

        public TextView getTime() {
            return time;
        }

        public TextView getCity() {
            return city;
        }

        public TextView getTemp() {
            return temp;
        }
    }
}
