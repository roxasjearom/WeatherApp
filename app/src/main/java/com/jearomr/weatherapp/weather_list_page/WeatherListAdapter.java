package com.jearomr.weatherapp.weather_list_page;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jearomr.weatherapp.R;
import com.jearomr.weatherapp.local_repository.entity.WeatherData;

import java.util.ArrayList;
import java.util.List;

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ViewHolder>{

    private Context context;
    private List<WeatherData> weatherDataList = new ArrayList<>();
    private WeatherListListener weatherListListener;

    public WeatherListAdapter(WeatherListListener weatherListListener) {
        this.weatherListListener = weatherListListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.row_weather, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        WeatherData weatherData = weatherDataList.get(i);
        viewHolder.tvLocationName.setText(weatherData.getName());
        viewHolder.tvWeather.setText(weatherData.getWeather().get(0).getMain());
        viewHolder.tvTemperature.setText(context.getString(R.string.temperature_format, ""+weatherData.getMain().getTemp() ));
    }

    @Override
    public int getItemCount() {
        if(weatherDataList == null){
            return 0;
        }
        return weatherDataList.size();
    }

    public void setWeatherDataList(List<WeatherData> weatherDataList){
        this.weatherDataList = weatherDataList;
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvLocationName, tvWeather, tvTemperature;

        ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvLocationName = itemView.findViewById(R.id.tvLocationName);
            tvWeather = itemView.findViewById(R.id.tvWeather);
            tvTemperature = itemView.findViewById(R.id.tvTemperature);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        weatherListListener.onItemSelected(weatherDataList.get(position));
                    }
                }
            });
        }
    }

}
