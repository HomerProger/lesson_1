package com.example.lesson1;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ShowWeatherFragment extends Fragment {

    public static final String PARCEL="parcel";
    public static ShowWeatherFragment create(Parcel parcel){
        ShowWeatherFragment showWeatherFragment = new ShowWeatherFragment();

        Bundle args=new Bundle();
        args.putSerializable(PARCEL,parcel);
        showWeatherFragment.setArguments(args);
        return showWeatherFragment;
    }

    public Parcel getParcel(){

            return (Parcel)getArguments().getSerializable(PARCEL);

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_whether_fragment,container,false);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("KEY", getParcel());
        super.onSaveInstanceState(outState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Parcel parcel;

        if(savedInstanceState!=null){
           parcel = (Parcel)savedInstanceState.getSerializable("KEY");
        }else {
            parcel = getParcel();
        }
        TextView cityName = view.findViewById(R.id.cityName);
        cityName.setText(parcel.getCityName());
        cityName.setTextSize(30);
    
    initDataSource();
    }

    private void initDataSource() {
        // строим источник данных
        DataSource sourceData = new CardDataSourceBuilder()
                .setResources(getResources())
                .build();
        // Декорируем источник данных, теперь он будет изменяем.
   //     final SocialChangableSource sourceChangableData = new SocChangableSource(sourceData);
    //  final CardDataApadter adapter = initRecyclerView(sourceData);
        initRecyclerView(sourceData);
//        Button add = findViewById(R.id.buttonAdd);
//        add.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sourceChangableData.add();
//                adapter.notifyItemInserted(sourceChangableData.size());
//            }
//        });
//        Button delete = findViewById(R.id.buttonDel);
//        delete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                sourceChangableData.delete();
//                adapter.notifyItemRemoved(sourceChangableData.size());
//            }
//        });
    }

    private void initRecyclerView(DataSource sourceData) {
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);
        // Эта установка служит для повышения производительности системы
        recyclerView.setHasFixedSize(true);

        // Будем работать со встроенным менеджером
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);

        // Добавим разделитель карточек
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.HORIZONTAL);
        itemDecoration.setDrawable(getActivity().getDrawable(R.drawable.separator));
        recyclerView.addItemDecoration(itemDecoration);
//
//        // Установим анимацию. А чтобы было хорошо заметно, сделаем анимацию долгой
//        DefaultItemAnimator animator = new DefaultItemAnimator();
//        animator.setAddDuration(500);
//        animator.setRemoveDuration(500);
//        recyclerView.setItemAnimator(animator);

        // Установим адаптер
        CardDataApadter adapter = new CardDataApadter(sourceData);
        recyclerView.setAdapter(adapter);

//        // Установим слушателя
//        adapter.SetOnItemClickListener(new SocnetAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//                Toast.makeText(MainActivity.this, String.format("Позиция - %d", position), Toast.LENGTH_SHORT).show();
//            }
//        });
//        return adapter;
    }


}
