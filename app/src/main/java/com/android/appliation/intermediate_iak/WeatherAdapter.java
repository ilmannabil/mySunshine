package com.android.appliation.intermediate_iak;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.GsonBuilder;

import org.w3c.dom.Text;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by adesanto on 11/4/17.
 */

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.WeatherViewHolder> {
    List<Weather> weatherList;

    public WeatherAdapter(List<Weather> weatherListData) {
        weatherList = weatherListData;
    }

    private static final String TAG = "WeatherAdapter";

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View viewContent = LayoutInflater.from(parent.getContext()).inflate(R.layout.weather_item, parent, false);
        return new WeatherViewHolder(viewContent);
    }

    @Override
    public void onBindViewHolder(WeatherViewHolder holder, int position) {
        holder.itemTitle.setText("Cuaca Hari ini tanggal" + position);
        holder.weatherItemImage.setImageResource(weatherList.get(position).getWeatherImage());
        holder.weatherItemDate.setText(weatherList.get(position).getWeatherDate());
        holder.weatherItemDesc.setText(weatherList.get(position).getWeatherDesc());
        holder.weatherItemTemperature.setText(weatherList.get(position).getWeatherTemperature());

    }

    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    class WeatherViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.item_title)
        TextView itemTitle;

        @BindView(R.id.weather_item_image)
        ImageView  weatherItemImage;

        @BindView(R.id.weather_item_date)
        TextView  weatherItemDate;

        @BindView(R.id.weather_item_dsc)
        TextView  weatherItemDesc;

        @BindView(R.id.weather_item_temperature)
        TextView  weatherItemTemperature;

        public WeatherViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(v.getContext(), WeatherDetailActivity.class);

            Weather weather = weatherList.get(getAdapterPosition());
            String weatherJson = new GsonBuilder().create().toJson(weather);
            Log.d(TAG, "onClick:" + weatherJson);
            intent.putExtra("weather", weatherJson);

            v.getContext().startActivity(intent);
        }
    }
}
