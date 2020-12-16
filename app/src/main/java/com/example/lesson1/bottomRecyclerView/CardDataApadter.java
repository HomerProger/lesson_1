package com.example.lesson1.bottomRecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lesson1.R;

public class CardDataApadter extends RecyclerView.Adapter<CardDataApadter.ViewHolder> {

    private DataSource dataSource;

    public CardDataApadter(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public CardDataApadter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent,false);
       ViewHolder viewHolder=new ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CardDataApadter.ViewHolder holder, int position) {

        CardData cardData=dataSource.getCardData(position);
        holder.setData(cardData.getDate(),cardData.getPicture(),cardData.getDayOfWeek(),cardData
                         .getTemperature(),cardData.getPrecipitation());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView date;
        private ImageView image;
        private TextView day;
        private TextView temperature;
        private TextView precipitation;

    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        date = itemView.findViewById(R.id.cwdate);
        image = itemView.findViewById(R.id.imageView);
        day = itemView.findViewById(R.id.cwday);
        temperature=itemView.findViewById(R.id.cwtemp);
        precipitation=itemView.findViewById(R.id.cwprecip);
    }

//    public void setOnClickListener(final AdapterView.OnItemClickListener listener){
//        image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Получаем позицию адаптера
//                int adapterPosition = getAdapterPosition();
//                // Проверяем ее на корректность
//                if (adapterPosition == RecyclerView.NO_POSITION) return;
//                listener.onItemClick(v, adapterPosition);
//            }
//        });
//    }

    public void setData(String date, int picture, String day, String temperature, String precipitation){
        getDate().setText(date);
        getImage().setImageResource(picture);
        getDay().setText(day);
        getTemperature().setText(temperature);
        getPrecipitation().setText(precipitation);
    }

        public TextView getDate() {
            return date;
        }

        public ImageView getImage() {
            return image;
        }

        public TextView getDay() {
            return day;
        }

        public TextView getTemperature() {
            return temperature;
        }

        public TextView getPrecipitation() {
            return precipitation;
        }
    }

}
